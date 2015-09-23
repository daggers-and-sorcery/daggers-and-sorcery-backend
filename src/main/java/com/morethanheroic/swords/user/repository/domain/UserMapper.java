package com.morethanheroic.swords.user.repository.domain;

import com.morethanheroic.swords.user.repository.dao.UserDatabaseEntity;
import org.apache.ibatis.annotations.*;

import java.util.Date;

public interface UserMapper {

    @Select("SELECT * FROM users WHERE username = #{param1} AND password = #{param2}")
    UserDatabaseEntity findByUsernameAndPassword(String username, String password);

    @Select("SELECT * FROM users WHERE id = #{id}")
    UserDatabaseEntity findById(int id);

    @Update("UPDATE users SET last_login_date = NOW() WHERE id = #{id} AND email = #{email} AND username = #{username}")
    void updateLastLoginDate(UserDatabaseEntity user);

    @Update("UPDATE users SET x = #{x}, y = #{y} WHERE id = #{userId}")
    void updatePosition(@Param("userId") int userId, @Param("x") int x,  @Param("y") int y);

    @Update("UPDATE users SET movement = #{movement} WHERE id = #{userId}")
    void updateMovement(@Param("userId") int userId, @Param("movement") int movement);

    @Update("UPDATE users SET last_regeneration_date = #{date}, health = #{health}, mana = #{mana}, movement = #{movement} WHERE id = #{userId}")
    void updateRegeneration(@Param("userId") int userId, @Param("health") int health, @Param("mana") int mana, @Param("movement") int movement, @Param("date") Date date);

    @Options(useGeneratedKeys=true, keyProperty = "id", keyColumn = "id")
    @Insert("INSERT INTO users SET username = #{username}, email = #{email}, password = #{password}, race = #{race}, x = #{x}, y = #{y}, map = #{map}, health = #{health}, mana = #{mana}, movement = #{movement}, registration_date = #{registration_date}, last_login_date = #{last_login_date}")
    void insert(UserDatabaseEntity userDatabaseEntity);
}