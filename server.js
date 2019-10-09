const express = require('express')
const app = express()
const http = require('http')
const bodyParser = require('body-parser')


const userRoute = require('./route/userRoute')
// const questionRoute = require('./route/questionRoute');
const questionDao = require('./dao/questionDao');

app.use(bodyParser.json())
app.use(bodyParser.urlencoded({ extended: false }))

// Sử dụng bộ nhớ của browser
app.use('/static', express.static('./static')).
        use('/images', express.static('./images')).
        use('/stylesheets', express.static('./views/styles'));


// Viết client dưới dạng template ejs
app.set('views', './views');
app.set('view engine', 'ejs');

// Trang chủ là trang đăng nhập
app.get('/', function (req, res) {
    res.render("login.ejs"); 
});


app.use('/api/users', userRoute);
app.get('/api/questions/:id', async (req, res)=>{
    console.log("Đang đọc câu hỏi");
    console.log(req.body.id);
    let question = await questionDao.getQuestion(req.body.id);
    res.json(question);
});

// Khởi chạy Server
http.createServer(app).listen(3000, function () {
    console.log('khoi chay 3000 thanh cong');
})
