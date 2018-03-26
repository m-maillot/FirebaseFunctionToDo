# ToDo sample

This project contains a ToDo sample based on firebase cloud functions.

Source code starts from JavaScript sample to KotlinJS sample.


## Overview

Project is composed of 3 WS :

* GET /todo/id

Get list of todo if no id set, get the todo information if id set

```
curl -X GET \
  http://localhost:5000/todomedium/us-central1/v1/todo/OPTIONAL_TODO_ID
```

* PUT /todo

Create a new todo with a label.

```
curl -X DELETE \
  http://localhost:5000/todomedium/us-central1/v1/todo/TODO_ID
```

* DELETE /todo/id

Delete todo with id

```
curl -X PUT \
  http://localhost:5000/todomedium/us-central1/v1/todo \
  -H 'Content-Type: application/x-www-form-urlencoded' \
  -d 'label=New%20Todo!'
```

## Database

Firestore is used to store data, don't forget to enable it.

## Test it

You can test project locally with the command (from functions folder) :

`npm run serve`