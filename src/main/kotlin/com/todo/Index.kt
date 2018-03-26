package com.todo

import com.expressjs.wrapper.Express
import com.firebase.wrappers.admin.Admin
import com.firebase.wrappers.admin.firestore.DocumentData
import com.firebase.wrappers.admin.firestore.get
import com.firebase.wrappers.functions.Functions
import kotlin.js.Date

external val exports: dynamic

fun main(args: Array<String>) {

    val app = Express()
    Admin.initializeApp(Functions.config().firebase)
    val db = Admin.firestore()

    app.get("/task/:id?", { req, res ->
        val params = req.params.unsafeCast<Params>()
        if (params.id != undefined) {
            db.collection("task").doc(params.id).get().then({ doc ->
                if (!doc.exists) {
                    res.status(404).json(Message("Task not found!"))
                } else {
                    val data = doc.data().unsafeCast<Task>()
                    res.status(200).json(Task(doc.id, data.label, data.time))
                }
            })
                    .catch({ err ->
                        res.status(500).json(err)
                    })
        } else {
            db.collection("todo").get().then({ snapshot ->
                val result = snapshot.docs.toList().map {
                    Task(it.id, it.data()!!["label"] as String, it.data()!!["time"] as Double)
                }
                res.status(200).json(result)
            })
                    .catch({ err ->
                        res.status(500).json(err)
                    })
        }
    })

    app.put("/task", { req, res ->
        val input = req.body.unsafeCast<TaskInput>()
        val inputTask = Task(label = input.label, time = Date().getTime())

        // Hack because firestore check object prototype and KotlinJS has his own
        db.collection("task").add(jsObject {
            label = inputTask.label
            time = inputTask.time
        }).then({ ref ->
            res.status(201).json(Task(ref.id, inputTask.label, inputTask.time))
        }).catch({ error ->
            res.status(500).json(error)
        })
    })

    app.delete("/task/:id", { req, res ->
        val params = req.params.unsafeCast<Params>()
        db.collection("task").doc(params.id).delete().then({
            res.status(200).json(Message("Task has been deleted"))
        })
    })

    exports.v1 = Functions.https.onRequest(app)
}

data class TaskInput(val label: String)
data class Task(val id: String? = undefined, val label: String, val time: Double) : DocumentData
data class Params(val id: String? = undefined)
data class Message(val msg: String)

inline fun jsObject(init: dynamic.() -> Unit): dynamic {
    val o = js("{}")
    init(o)
    return o
}
