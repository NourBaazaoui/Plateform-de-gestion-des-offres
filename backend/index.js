/*
  RESTFul Services by NodeJs
  Author: Nour Baazaoui

 */


var crypto = require('crypto');
var uuid = require('uuid');
var express = require('express');
var mysql = require('mysql');
var bodyParser = require('body-parser');

//hello
// bib bech nestaamlouhom
var app = express();
var multer, storage, path, crypto;
multer = require('multer')
path = require('path');
var ima = "";

// Connect to MySQL
var con = mysql.createConnection({
    host: 'localhost', // Replace your HOST IP
    user: 'root',
    password: '',
    database:'application'
});

var app = express();
app.use(bodyParser.json()); //Accept JSON Params
app.use(bodyParser.urlencoded({ extended: true })); // Accept URL Encoded params
//create account
app.post('/signup/', (req, res, next) => {
    var post_data = req.body; // Get POST params
    var nom = post_data.nom;
    var prenom = post_data.prenom;
    var email = post_data.email;
    var numero = post_data.numero;
    var password = post_data.password;
    //con heya el cnx m3a el base de donnes
//query pour lancer une requete
    con.query('SELECT * FROM user where numero=?', [numero], function (err, result, fields) {
        con.on('error', function (err) {
            console.log('[MySQL ERROR]');
        });
        if (result && result.length)
            res.json('User already exists!!!');
        else {
            con.query('INSERT INTO `user`(`nom`, `prenom`, `email`, `password`, `numero`) VALUES(?, ?, ?, ?, ?)',
           [nom, prenom, email,password , numero], function(err, result, fields) {
                    con.on('error', function (err) {
                        console.log('[MySQL ERROR]', err);
                        res.json('Register Error: ', err);

                    });
                    res.json('Register Successful!');
        })
        }
    });
   
})

//authentification
app.post('/login/', (req, res, next) => {
    var post_data = req.body;

    // Extract login and password from request
    var email = post_data.email;
    var password = post_data.password;
    con.query('SELECT * FROM user where email=? and password=?', [email,password], function (err, result, fields) {
        con.on('error', function (err) {
            console.log('[MySQL ERROR]');
        });
        if (result && result.length) {
            res.end(JSON.stringify(result[0]));
        }
        else {
            res.json('User Not Found!!!');
        }
    });

})

//GET list Client
app.get('/Getusers/', (req, res, next) => {
    con.query('SELECT * FROM user', function (err, result, fields) {
        con.on('error', function (err) {
            console.log('[MYSQL ERROR]', err);
        });
        if (result && result.length) {
            res.end(JSON.stringify(result));
        }
        else {
            res.end(JSON.stringify("Nothing was found!"));
        }

    });


})



        //Update useer
app.put('/user/update/:id', function(req,res,next){
    
    var post_data = req.body;  //Get POST params
    var nom= post_data.nom;
    var prenom = post_data.prenom;
    var email= post_data.email;
    var password = post_data.password;
    var numero = post_data.numero;
    
    con.query('UPDATE user SET nom = ?,prenom = ?,email = ?,password = ?, numero = ? WHERE id = ?', [nom,prenom,email,password,numero,req.params.id],function (err,result,fields) {
                if (err) throw err;
                res.json('User updated');
                console.log('User updated');

            });
        })



	//Delete user
    app.delete('/user/delete/:id', (req, res) => {
        con.query('DELETE FROM user WHERE id = ?', [req.params.id], (err, rows, fields) => {
            if (!err)
                res.send('Deleted successfully.');
            else
                console.log(err);
        })
    });





//GET list Client
app.get('/GetJobs/', (req, res, next) => {
    con.query('SELECT * FROM job', function (err, result, fields) {
        con.on('error', function (err) {
            console.log('[MYSQL ERROR]', err);
        });
        if (result && result.length) {
            res.end(JSON.stringify(result));
        }
        else {
            res.end(JSON.stringify("Nothing was found!"));
        }

    });


})
    


// Start Server
app.listen(3000, () => {
    console.log('Fabio Restful running on port 3000');

})
