const userDao = require('../dao/userDao');

module.exports.login = async function(req, res) {
    try {
        let user = await userDao.login(req.body.username, req.body.password);
        res.json(user);
    } catch(err) {
        console.log(err);
        res.json({'message' : 'login failed'});
    }
}

module.exports.getUser = async function(req, res) {
    console.log('getUser');
    let user = await userDao.getUser(req.params.id);
    res.json(user);
}