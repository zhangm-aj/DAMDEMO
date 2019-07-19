package life.zm.damdemo.damdemo.Service;
import life.zm.damdemo.damdemo.mapper.UserMapper;
import life.zm.damdemo.damdemo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserMapper userMapper;




    public void addUser(User user) {
        userMapper.addUser(user);
    }

    public User findUserByCode(String user_code) {
        return userMapper.findUserByCode(user_code);

    }

    public User userLogin(User user2) {
        return userMapper.userLogin(user2);
    }
}
