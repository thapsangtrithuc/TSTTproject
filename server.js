const express = require('express')
const app = express()
const http = require('http')
const bodyParser = require('body-parser')

const userRoute = require('./route/userRoute')

app.use(bodyParser.json())
app.use(bodyParser.urlencoded({ extended: false }))


// Viết client dưới dạng template ejs
app.set('views', './views');
app.set('view engine', 'ejs');

// Trang chủ là trang đăng nhập
app.get('/', function (req, res) {
    res.render("login.ejs");
});


app.use('/api/users', userRoute);


// Trang thí sinh dành cho các thí sinh đọc câu hỏi
// và xem điểm của các bạn cùng chơi
app.get('/thisinh', function (req, res) {
    // TODO Viết giao diện trang thí sinh
    res.render('thisinh.ejs');
});




// Khởi chạy Server
http.createServer(app).listen(8080, function () {
    console.log('khoi chay thanh cong');
})
