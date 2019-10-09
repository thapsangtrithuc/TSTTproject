const userDao = require('../dao/questionDao');

module.exports.login = function (req, res) {
    userDao.login(req.body.id).then(question => {
        console.log(JSON.stringify(user));
        res.json(question);
    }).catch((err) => {
        console.log(err);
        res.json({ 'message': 'Load question failed' });
    })
}
