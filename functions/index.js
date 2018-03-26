const functions = require('firebase-functions');
const express = require("express");
const admin = require("firebase-admin");

const app = express();

admin.initializeApp(functions.config().firebase);

const db = admin.firestore();

app.get("/todo/:id?", (req, res) => {
  if (req.params.id !== undefined) {
    db.collection('todo').doc(req.params.id).get().then(doc => {
      if (!doc.exists) {
        res.status(404).send({msg: "ToDo not found"});
      } else {
        res.status(200).json({
          id: doc.id,
          data: doc.data()
        });
      }
    })
      .catch(err => {
        res.status(500).json(err)
      });
  } else {
    db.collection('todo').get().then(snapshot => {
      const result = [];
      snapshot.forEach(doc => {
        result.push({
          id: doc.id,
          todo: doc.data()
        });
      });
      res.status(200).json(result);
    })
      .catch(err => {
        res.status(500).json(err)
      });
  }
});

app.put("/todo", (req, res) => {
  const todo = {
    label: req.body.label,
    time: new Date().getTime()
  };
  db.collection('todo').add(todo).then(ref => {
    res.status(201).json({
      id: ref.id,
      todo
    });
  }).catch(error => {
    res.status(500).json(error)
  });
});

app.delete("/todo/:id", (req, res) => {
  db.collection('todo').doc(req.params.id).delete().then(result => {
    console.log(result);
    res.status(200).send("OK");
  });
});

exports.v1 = functions.https.onRequest(app);