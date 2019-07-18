package life.zm.damdemo.damdemo.Service;

import com.google.common.collect.Lists;
import life.zm.damdemo.damdemo.mapper.UserMapper;
import life.zm.damdemo.damdemo.model.User;
import life.zm.damdemo.damdemo.utils.BeanHelper;
import life.zm.damdemo.damdemo.utils.HashUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class UserService {
    @Autowired
    private MailService mailService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private FileService fileService;
    public List<User> getUser(){
        return userMapper.selectUsers();
    }
    /*
    * 1.插入数据库，非激活；密码加盐md5；保存头像到本地
    * 2.生成key，绑定email
    * 3.发送邮件给用户
    * */
    @Transactional(rollbackFor = Exception.class)
    public boolean addAccount(User account){
        account.setPasswd(HashUtils.encryPassword(account.getPasswd()));
        List<String> imgList = fileService.getImgPaths(Lists.newArrayList(account.getAvatarFile()));
        if(!imgList.isEmpty()){
            account.setAvatar(imgList.get(0));
        }
        BeanHelper.setDefaultProp(account,User.class);
        BeanHelper.onInsert(account);
        account.setEnable(0);
        userMapper.insert(account);
        mailService.registerNotify(account.getEmail());
        return true;
    }

    public boolean enable(String key) {

        return mailService.enable(key);
    }
}
