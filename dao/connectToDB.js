const mysql = require('mysql');

var connection = mysql.createPool({
    connectionLimit: 2,
    database: "tstt",
    host: "localhost",
    user: "root",
    password: "nghialuffy",
});

module.exports.getInstance = function(callback) {
    return connection.getConnection(function(err, tempCont) {
        if(err) {
          //  tempCont.release();
            console.log(err);
        }
        else {
            callback(tempCont);
            tempCont.release();
        }
    })
}