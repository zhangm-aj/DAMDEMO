package life.zm.damdemo.damdemo.Service;

import life.zm.damdemo.damdemo.model.UserDomain;


public interface UserService {
    UserDomain findUserByCode(String user_code);

    void addUser(UserDomain userDomain);

    UserDomain userLogin(UserDomain user2);


    void updateUserInfo(UserDomain temp);

    void updateUserPassword(UserDomain temp);

    UserDomain login(String username, String password);
}
