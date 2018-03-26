(function (_, Kotlin) {
  'use strict';
  var $$importsForInline$$ = _.$$importsForInline$$ || (_.$$importsForInline$$ = {});
  var defineInlineFunction = Kotlin.defineInlineFunction;
  function main$lambda$lambda(closure$res) {
    return function (doc) {
      if (!doc.exists) {
        var tmp$ = closure$res.status(404);
        var o = {};
        o.msg = 'ToDo not found';
        return tmp$.send(o);
      }
       else {
        var tmp$_0 = closure$res.status(200);
        var o_0 = {};
        o_0.id = doc.id;
        o_0.todo = doc.data();
        return tmp$_0.json(o_0);
      }
    };
  }
  function main$lambda$lambda_0(closure$res) {
    return function (err) {
      return closure$res.status(500).json(err);
    };
  }
  function main$lambda$lambda$lambda(closure$result) {
    return function (doc) {
      var tmp$ = closure$result;
      var o = {};
      o.id = doc.id;
      o.todo = doc.data();
      return tmp$.add_11rb$(o);
    };
  }
  var ArrayList_init = Kotlin.kotlin.collections.ArrayList_init_ww73n8$;
  function main$lambda$lambda_1(closure$res) {
    return function (snapshot) {
      var result = ArrayList_init();
      snapshot.forEach(main$lambda$lambda$lambda(result));
      return closure$res.status(200).json(result);
    };
  }
  function main$lambda$lambda_2(closure$res) {
    return function (err) {
      return closure$res.status(500).json(err);
    };
  }
  function main$lambda(closure$db) {
    return function (req, res) {
      if (req.params.id != undefined) {
        return closure$db.collection('todo').doc(req.params.id).get().then(main$lambda$lambda(res)).catch(main$lambda$lambda_0(res));
      }
       else {
        return closure$db.collection('todo').get().then(main$lambda$lambda_1(res)).catch(main$lambda$lambda_2(res));
      }
    };
  }
  function main$lambda$lambda_3(closure$res, closure$inputTodo) {
    return function (ref) {
      var tmp$ = closure$res.status(201);
      var o = {};
      var closure$inputTodo_0 = closure$inputTodo;
      o.id = ref.id;
      o.todo = closure$inputTodo_0;
      return tmp$.json(o);
    };
  }
  function main$lambda$lambda_4(closure$res) {
    return function (error) {
      return closure$res.status(500).json(error);
    };
  }
  function main$lambda_0(closure$db) {
    return function (req, res) {
      var o = {};
      o.label = req.body.label;
      o.time = (new Date()).getTime();
      var inputTodo = o;
      console.log('Todo', inputTodo);
      return closure$db.collection('todo').add(inputTodo).then(main$lambda$lambda_3(res, inputTodo)).catch(main$lambda$lambda_4(res));
    };
  }
  function main$lambda$lambda_5(closure$res) {
    return function (result) {
      return closure$res.status(200).send('OK');
    };
  }
  function main$lambda_1(closure$db) {
    return function (req, res) {
      return closure$db.collection('todo').doc(req.params.id).delete().then(main$lambda$lambda_5(res));
    };
  }
  function main(args) {
    var express = require('express');
    var functions = require('firebase-functions');
    var admin = require('firebase-admin');
    var app = express();
    admin.initializeApp(functions.config().firebase);
    var db = admin.firestore();
    app.get('/todo/:id?', main$lambda(db));
    app.put('/todo', main$lambda_0(db));
    app.delete('/todo/:id', main$lambda_1(db));
    exports.v1 = functions.https.onRequest(app);
  }
  var jsObject = defineInlineFunction('index.com.todo.jsObject_5ij4lk$', function (init) {
    var o = {};
    init(o);
    return o;
  });
  $$importsForInline$$.index = _;
  var package$com = _.com || (_.com = {});
  var package$todo = package$com.todo || (package$com.todo = {});
  package$todo.main_kand9s$ = main;
  package$todo.jsObject_5ij4lk$ = jsObject;
  main([]);
  Kotlin.defineModule('index', _);
  return _;
}(module.exports, require('kotlin')));

//# sourceMappingURL=index.js.map
