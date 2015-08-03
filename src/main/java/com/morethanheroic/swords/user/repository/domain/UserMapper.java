package com.morethanheroic.swords.user.repository.domain;

import com.morethanheroic.swords.user.repository.dao.UserDatabaseEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserMapper {

    @Select("SELECT * FROM user WHERE username = #{username}")
    UserDatabaseEntity findByUsername(String username);

    @Select("SELECT * FROM user WHERE email = #{email}")
    UserDatabaseEntity findByEmail(String email);

    @Select("SELECT * FROM user WHERE username = #{param1} AND password = #{param2}")
    UserDatabaseEntity findByUsernameAndPassword(String username, String password);

    @Select("SELECT * FROM user WHERE id = #{id}")
    UserDatabaseEntity findById(int id);

    @Update("UPDATE user SET last_login_date = NOW() WHERE id = #{id} AND email = #{email} AND username = #{username}")
    void updateLastLoginDate(UserDatabaseEntity user);

    @Update("UPDATE user SET x = #{x}, y = #{y} WHERE id = #{userId}")
    void updatePosition(@Param("userId") int userId, @Param("x") int x,  @Param("y") int y);

    @Insert("INSERT INTO user SET username = #{username}, email = #{email}, password = #{password}, race = #{race}")
    void insert(UserDatabaseEntity userDatabaseEntity);
}