const userDao = require('../dao/userDao');

module.exports.login = function (req, res) {
    userDao.login(req.body.username, req.body.password).then(user => {
        console.log(JSON.stringify(user));
        res
        .status(201)
        .cookie('test', 'test')
        .cookie('user', JSON.stringify(user), {
            expires: new Date(Date.now() + 8 * 3600000)
        }).render('thisinh.ejs', { user: user });
    }).catch((err) => {
        console.log(err);
        res.json({ 'message': 'login failed' });
    })
}


module.exports.getUser = async function (req, res) {
    console.log('getUser');
    let user = await userDao.getUser(req.params.id);
    res.json(user);
}

// TODO Hàm update điểm số của một thí sinh
module.exports.updatePoint = async function (req, res) {
    console.log('Update diem');
    userDao.updatePoint(req.params.id, req.params.point);
}