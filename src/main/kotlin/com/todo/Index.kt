package com.todo

import kotlin.js.Date

external val exports: dynamic
external val require: dynamic

fun main(args: Array<String>) {

    val express = require("express")
    val functions = require("firebase-functions")
    val admin = require("firebase-admin")

    val app = express()
    admin.initializeApp(functions.config().firebase)
    val db = admin.firestore()

    app.get("/todo/:id?", { req, res ->
        if (req.params.id != undefined) {
            db.collection("todo").doc(req.params.id).get().then({ doc ->
                if (!doc.exists) {
                    res.status(404).send(jsObject {
                        msg = "ToDo not found"
                    })
                } else {
                    res.status(200).json(jsObject {
                        id = doc.id
                        todo = doc.data()
                    })
                }
            })
                    .catch({ err ->
                        res.status(500).json(err)
                    })
        } else {
            db.collection("todo").get().then({ snapshot ->
                val result = mutableListOf<dynamic>()
                snapshot.forEach({ doc ->
                    result.add(jsObject {
                        id = doc.id
                        todo = doc.data()
                    })
                })
                res.status(200).json(result)
            })
                    .catch({ err ->
                        res.status(500).json(err)
                    })
        }
    });

    app.put("/todo", { req, res ->
        val inputTodo = jsObject {
            label = req.body.label
            time = Date().getTime()
        }
        console.log("Todo", inputTodo)
        db.collection("todo").add(inputTodo).then({ ref ->
            res.status(201).json(jsObject {
                id = ref.id
                todo = inputTodo
            })
        }).catch({ error ->
            res.status(500).json(error)
        })
    })

    app.delete("/todo/:id", { req, res ->
        db.collection("todo").doc(req.params.id).delete().then({
            res.status(200).send("OK")
        })
    })

    exports.v1 = functions.https.onRequest(app);
}

inline fun jsObject(init: dynamic.() -> Unit): dynamic {
    val o = js("{}")
    init(o)
    return o
}