package com.morethanheroic.swords.user.repository.domain;

import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.user.repository.dao.UserDatabaseEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.time.Instant;

/**
 * Provides an API to access {@link UserEntity}es in the database.
 */
@Repository
public interface UserMapper {

    @Select("SELECT * FROM users WHERE username = #{param1} AND password = #{param2}")
    UserDatabaseEntity findByUsernameAndPassword(String username, String password);

    @Select("SELECT * FROM users WHERE id = #{id}")
    UserDatabaseEntity findById(int id);

    @Update("UPDATE users SET last_login_date = NOW() WHERE id = #{id}")
    void updateLastLoginDate(@Param("id") int id);

    @Update("UPDATE users SET scavenging_point= #{scavenging_point} WHERE id = #{userId}")
    void updateScavengingPoint(@Param("userId") int userId, @Param("scavenging_point") int scavengingPoint);

    @Update("UPDATE users SET movement = #{movement} WHERE id = #{userId}")
    void updateMovement(@Param("userId") int userId, @Param("movement") int movement);

    @Update("UPDATE users SET exploration_event = #{explorationEvent}, exploration_state = #{explorationState} WHERE id = #{userId}")
    void updateExploration(@Param("explorationEvent") int explorationEvent, @Param("explorationState") int explorationState, @Param("userId") int userId);

    @Update("UPDATE users SET health = #{health}, mana = #{mana}, movement = #{movement} WHERE id = #{userId}")
    void updateBasicCombatStats(@Param("userId") int userId, @Param("health") int health, @Param("mana") int mana, @Param("movement") int movement);

    @Update("UPDATE users SET last_regeneration_date = #{date}, health = #{health}, mana = #{mana}, movement = #{movement} WHERE id = #{userId}")
    void updateRegeneration(@Param("userId") int userId, @Param("health") int health, @Param("mana") int mana,
                            @Param("movement") int movement, @Param("date") Instant date);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("INSERT INTO users SET username = #{username}, email = #{email}, password = #{password}, race = #{race}, "
            + "health = #{health}, mana = #{mana}, movement = #{movement}, registration_date = #{registrationDate},"
            + " last_login_date = #{lastLoginDate}")
    void insert(UserDatabaseEntity userDatabaseEntity);
}
