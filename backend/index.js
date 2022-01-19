/*
  RESTFul Services by NodeJs
  Author: Fabio
  Update: 18/11/2021
 */


var crypto = require('crypto');
var uuid = require('uuid');
var express = require('express');
var mysql = require('mysql');
var bodyParser = require('body-parser');

//hello

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
    database:'gem'
});

var app = express();
app.use(bodyParser.json()); //Accept JSON Params
app.use(bodyParser.urlencoded({ extended: true })); // Accept URL Encoded params

app.post('/register/', (req, res, next) => {
    var post_data = req.body; // Get POST params
    var login = post_data.login;
    var pwd = post_data.pwd;
    var prenom = post_data.prenom;
    var nom = post_data.nom;
    var email = post_data.email;
    var actif = post_data.actif;
    var valsync = post_data.valsync;
    con.query('SELECT * FROM employes where email=?', [email], function (err, result, fields) {
        con.on('error', function (err) {
            console.log('[MySQL ERROR]');
        });
        if (result && result.length)
            res.json('User already exists!!!');
        else {
            con.query('INSERT INTO `employes`(`login`, `pwd`, `prenom`, `nom`, `email`, `actif`, `valsync`) VALUES(?, ?, ?, ?, ?, ?, ?)',
           [login, pwd, prenom, nom, email, actif, valsync], function(err, result, fields) {
                    con.on('error', function (err) {
                        console.log('[MySQL ERROR]', err);
                        res.json('Register Error: ', err);

                    });
                    res.json('Register Successful!');
        })
        }
    });
   
})


app.post('/login/', (req, res, next) => {
    var post_data = req.body;

    // Extract login and password from request
    var pwd = post_data.pwd;
    var login = post_data.login;
    con.query('SELECT * FROM employes where login=? and pwd=?', [login,pwd], function (err, result, fields) {
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




//GET Events User
app.get('/GetinterventionEmploye/:employe_id', (req, res, next) => {
    con.query('SELECT * FROM employes_interventions e JOIN interventions x ON e.intervention_id=x.id WHERE e.employe_id=?', [req.params.employe_id], function (err, result, fields) {
        con.on('error', function (err) {
            console.log('[MYSQL ERROR]', err);
        });
        if (result && result.length) {
            res.end(JSON.stringify(result));
        }
        else {
            res.end(JSON.stringify("error"));
        }

})});

//GET Employee Data
app.get('/Employes/:login', (req, res, next) => {
    con.query('SELECT * FROM employes where login=?', [req.params.login], function (err, result, fields) {
        con.on('error', function (err) {
            console.log('[MYSQL ERROR]', err);
        });
        if (result && result.length) {
            res.end(JSON.stringify(result[0]));
        }
        else {
            res.end(JSON.stringify("error"));
        }

    });
})

//Get Tasks
app.get('/GetTasks/:intervention_id', (req, res, next) => {
    con.query('SELECT * FROM taches where intervention_id=?', [req.params.intervention_id], function (err, result, fields) {
        con.on('error', function (err) {
            console.log('[MYSQL ERROR]', err);
        });
        if (result && result.length) {
            res.end(JSON.stringify(result));
        }
        else {
            res.end(JSON.stringify("error"));
        }

    });
})



//Delete Intervention
app.delete('/intervention/delete/:id', (req, res) => {
    con.query('DELETE FROM interventions WHERE id = ?', [req.params.id], (err, rows, fields) => {
        if (!err)
            res.send('Deleted successfully.');
        else
            console.log(err);
    })
});

//Update Intervention
app.put('/intervention/update/:id', function(req,res,next){
    
    var post_data = req.body;  //Get POST params
    var titre = post_data.titre;
    var commentaires = post_data.commentaires;
    var datedebut= post_data.datedebut;
    var datefin = post_data.datefin;
    con.query('UPDATE interventions SET titre = ?,commentaires = ?,datedebut = ?,datefin = ? WHERE id = ?', [titre,commentaires,datedebut,datefin,req.params.id],function (err,result,fields) {
                if (err) throw err;
                res.json('User updated');
                console.log('User updated');

            });
        })

        



//GET list Client
app.get('/Getclient/', (req, res, next) => {
    con.query('SELECT * FROM clients', function (err, result, fields) {
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

app.get('/GetComs/', (req, res, next) => {
    con.query('SELECT * FROM commentaire', function (err, result, fields) {
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

app.get('/GetShop/:id_user', (req, res, next) => {
    con.query('SELECT * FROM shop where id_user=?', [req.params.id_user], function (err, result, fields) {
        con.on('error', function (err) {
            console.log('[MYSQL ERROR]', err);
        });
        if (result && result.length) {
            res.end(JSON.stringify(result[0]));
        }
        else {
            res.end(JSON.stringify("error"));
        }

    });
})


//GET list articles
app.get('/GetArticle/', (req, res, next) => {
    con.query('SELECT * FROM article', function (err, result, fields) {
        con.on('error', function (err) {
            console.log('[MYSQL ERROR]', err);
        });
        if (result && result.length) {
            res.end(JSON.stringify(result));
        }
        else {
            res.end(JSON.stringify("nothing was found"));
        }

    });


})



app.post('/evenement/add', function (req, res, next) {
    var post_data = req.body;  //Get POST params
    var nom = post_data.nom;
    var type = post_data.type;
    var dateDebut = post_data.dateDebut;
    var dateFin = post_data.dateFin;
    var distance = post_data.distance;
    var lieu = post_data.lieu;
    var infoline = post_data.infoline;
   var description = post_data.description;
    var nbPlace = post_data.nbPlace;
 var prix = post_data.prix;
    var id_user = post_data.id_user;



    con.query('INSERT INTO `evenement`(`nom`, `type`, `dateDebut`, `dateFin`, `distance`, `lieu`, `infoline`, `description`, `nbPlace`, `prix`, `id_user`) ' +
        'VALUES (?,?,?,?,?,?,?,?,?,?,?)', [nom, type, dateDebut, dateFin, distance, lieu, infoline, description, nbPlace, prix, id_user], function (err, result, fields) {
            if (err) throw err;

            res.json('Add Evenement Successful!');

        });

})



//add Participant

app.post('/participant/add/', function(req,res,next){
    var post_data = req.body;  //Get POST params
    
	var id_user = post_data.id_user;
var id_evenement= post_data.id_evenement;


 
    con.query('INSERT INTO `participants`(`id_user`,`id_evenement`) ' +
        'VALUES (?,?)',[id_user,id_evenement],function (err,result,fields) {
                if (err) throw err;

                res.json('participant ajouté avec succés');

            });

    })

app.get('/uploads/:upload', function (req, res) {
    file = req.params.upload;
    console.log(req.params.upload);
    var img = fs.readFileSync(__dirname + "/uploads/" + file);
    res.writeHead(200, { 'Content-Type': 'image/png' });
    res.end(img, 'binary');

});






//update event
app.put('/evenement/update/:id', function(req,res,next){
    var post_data = req.body;  //Get POST params
//var nbplace_evenement= post_data.nbplace_evenement;
//var id_evenement= post_data.id_evenement;
    con.query('UPDATE evenement SET nbPlace = nbPlace - 1 WHERE id = ?', [req.params.id],function (err,result,fields) {
                if (err) throw err;

                res.json('Evenement updated');

            });
        })


	//Delete an event
    app.delete('/particip/delete/:id', (req, res) => {
        con.query('DELETE FROM participants WHERE id_evenement = ?', [req.params.id], (err, rows, fields) => {
            if (!err)
                res.send('Deleted successfully.');
            else
                console.log(err);
        })
    });
    


// Start Server
app.listen(3000, () => {
    console.log('Fabio Restful running on port 3000');

})
