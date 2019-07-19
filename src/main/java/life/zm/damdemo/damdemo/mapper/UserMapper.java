package life.zm.damdemo.damdemo.mapper;

import life.zm.damdemo.damdemo.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Insert("insert into dam_user (user_code,user_password,user_name,user_enable,user_create_time) values(#{user_code},#{user_password},#{user_name},#{user_enable},#{user_createTime})")
    void addUser(User user);
    @Select("select * from dam_user where user_code = #{user_code}")
    User findUserByCode(String user_code);
    @Select("select * from dam_user where user_code = #{user_code} and user_password = #{user_password}")
    User userLogin(User user2);
}
