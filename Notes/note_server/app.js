import express from 'express';
import mysql from 'mysql';
import cors from 'cors';


const app = express();
app.use(cors());
app.use(express.json());

const db = mysql.createConnection({
    host: 'localhost',
    user: 'demo',
    password: '123456',
    database: 'notes'
});
app.post("/create_user", (req, res) => {
    const name = req.body.name;
    const password = req.body.password;

    db.query(
      "INSERT INTO users (name, password) VALUES (?,?)",
      [name, password],
      (err, result) => {
        if (err) {
          console.log(err);
        } else {
          res.send("Values Inserted");
        }
      }
    );
  });


app.post("/create_note", (req, res) => {
  const TITLE = req.body.TITLE
  const CONTENT = req.body.CONTENT
  const COLOR = req.body.COLOR
  const PRIORITY = req.body.PRIORITY
  const USER_ID = req.body.USER_ID

  db.query(
    "INSERT INTO notes (TITLE, CONTENT, PRIORITY, COLOR, USER_ID) "
    + "VALUES (?,?,?,?,?)",
    [TITLE, CONTENT, PRIORITY, COLOR, USER_ID],
    (err, result) => {
      if (err) { console.log(err) }
      else {
        res.send("Values inserted")
      }
    }
  )
})

app.put("/update_note", (req, res) => {
  const TITLE = req.body.TITLE
  const CONTENT = req.body.CONTENT
  const COLOR = req.body.COLOR
  const PRIORITY = req.body.PRIORITY
  const USER_ID = req.body.USER_ID
  const id = req.body.id

  db.query("UPDATE notes " +
    "set title = ?, " +
    "content= ?, " +
    "priority = ?, " +
    "color = ?, " +
    "USER_ID = ? " +
    "WHERE id like ?",
    [TITLE, CONTENT, PRIORITY, COLOR,  USER_ID, id],
    (err, result) => {
      if (err) { console.log(err) }
      else {
        res.send("Values updated")
      }
    }
  )
})


app.get('/get_notes', (req, res) => {

  const name = req.query.name;
  const password = req.query.password;

  db.query('SELECT notes.id, notes.TITLE, notes.CONTENT, notes.COLOR, notes.PRIORITY, notes.id, notes.USER_ID FROM notes.notes ' +
    'inner join users on users.ID = notes.USER_ID ' +
    'where users.name = ? ' +
    'AND users.password = ? ',
    [name, password],
    (err, result) => {
      if (err) {
        console.log(err)
      } else {
        res.send(result)
      }
    })
})



app.get('/get_note_by_id', (req, res) => {

  const user_id = req.query.user_id;
  const note_id = req.query.note_id;

  db.query('SELECT notes.id, notes.TITLE, notes.CONTENT, notes.COLOR, notes.PRIORITY, notes.id, notes.USER_ID ' +
    'FROM notes.notes ' +
    'inner join users on users.ID = notes.USER_ID ' +
    'where users.id = ?' +
    'AND notes.id = ?; ',
    [user_id, note_id],
    (err, result) => {
      if (err) { console.log(err) }
      else{res.send(result)}
    })
})





app.delete("/delete", (req, res) => {
  const noteId = req.query.noteId;
  const userId = req.query.userId;
  db.query("DELETE FROM notes.notes WHERE notes.ID = ? AND notes.USER_ID",// 
  [noteId,userId], (err, result) => {
    if (err) {
      console.log(err);
    } else {
      res.send(result);
    }
  });
});

app.get("/users", (req, res) => {
  db.query("SELECT * FROM users",
(err, result) => {
    if (err) { throw new err }
    else{
        res.send(result)
    }
})
})

app.get("/message", (req, res) => {

  res.send({Status:"200"})
})

app.get('/get_user', (req, res) => {

    const name = req.query.name;
    const password = req.query.password;
  
  db.query("SELECT * FROM users where name = ? and password = ?",
      [name, password],
    (err, result) => {
        if (err) { throw new err }
        else{
            res.send(result)
        }
    })
})



app.listen('8001', () => {
  console.log('Listening on port 8000');
});
