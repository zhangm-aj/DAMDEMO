package life.zm.damdemo.damdemo.dao.mapper;

import life.zm.damdemo.damdemo.model.UserDomain;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
;
@Repository
@Mapper
public interface UserDao {

    @Insert("insert into dam_user (username,password,screenName,email,created) values(#{username},#{password},#{screenName},#{email},#{created})")
    void addUser(UserDomain userDomain);
    @Select("select * from dam_user where username = #{username}")
    UserDomain findUserByCode(String username);
    @Select("select * from dam_user where username = #{username} and password = #{password}")
    UserDomain userLogin(UserDomain userDomain2);

    @Update("update dam_user set screenName = #{screenName},email = #{email} where uid = #{uid}")
    void updateUserInfo(UserDomain temp);
    @Update("update dam_user set password = #{password} where uid = #{uid}")
    void updateUserPassword(UserDomain temp);

    @Select("select * from dam_user where username = #{username} and password = #{pwd}")
    UserDomain login(@Param("username") String username, @Param("pwd") String pwd);
    @Select("select * from dam_user where uid = #{uid}")
    UserDomain getUserInfoById(Integer uid);
}
