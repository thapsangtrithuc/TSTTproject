const express = require('express')
const app = express()
const http = require('http')
const bodyParser = require('body-parser')
//const net = require('net')
const userRoute = require('./route/userRoute')

app.use(bodyParser.json())
app.use(bodyParser.urlencoded({extended:false}))
app.get('/', function(req, res) {
    console.log('oko');
    res.end('abc')
});

app.use('/api/users', userRoute);

http.createServer(app).listen(8080, function() {
    console.log('khoi chay thanh cong')
})
