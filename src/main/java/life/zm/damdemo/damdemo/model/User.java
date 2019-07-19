package life.zm.damdemo.damdemo.model;



import javax.xml.crypto.Data;
import java.util.Date;

public class User {
    private Long user_id;



    private Integer user_enable;

    private String user_code;
    private String user_name;

    private String user_password;

    private String user_confirmPassword;

    private Date user_createTime;

    private String user_newPassword;

    private String key;

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Integer getUser_enable() {
        return user_enable;
    }

    public void setUser_enable(Integer user_enable) {
        this.user_enable = user_enable;
    }

    public String getUser_code() {
        return user_code;
    }

    public void setUser_code(String user_code) {
        this.user_code = user_code;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_confirmPassword() {
        return user_confirmPassword;
    }

    public void setUser_confirmPassword(String user_confirmPassword) {
        this.user_confirmPassword = user_confirmPassword;
    }

    public Date getUser_createTime() {
        return user_createTime;
    }

    public void setUser_createTime(Date user_createTime) {
        this.user_createTime = user_createTime;
    }

    public String getUser_newPassword() {
        return user_newPassword;
    }

    public void setUser_newPassword(String user_newPassword) {
        this.user_newPassword = user_newPassword;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
