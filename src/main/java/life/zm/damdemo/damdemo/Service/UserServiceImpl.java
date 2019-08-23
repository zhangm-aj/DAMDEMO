package life.zm.damdemo.damdemo.Service;

import life.zm.damdemo.damdemo.dao.mapper.UserDao;
import life.zm.damdemo.damdemo.model.UserDomain;

import life.zm.damdemo.damdemo.utils.TaleUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;




    public void addUser(UserDomain userDomain) {
        userDao.addUser(userDomain);
    }

    public UserDomain findUserByCode(String user_code) {
        return userDao.findUserByCode(user_code);

    }

    public UserDomain userLogin(UserDomain userDomain2) {
        return userDao.userLogin(userDomain2);
    }

    @Override
    public void updateUserInfo(UserDomain temp) {
        userDao.updateUserInfo(temp);
    }

    @Override
    public void updateUserPassword(UserDomain temp) {
        userDao.updateUserPassword(temp);
    }

    @Override
    public UserDomain login(String username, String password) {
        String pwd = TaleUtils.MD5encode(username + password);
       return  userDao.login(username, pwd);

    }

    @Override
    public UserDomain getUserInfoById(Integer uid) {
        return userDao.getUserInfoById(uid);
    }
}




