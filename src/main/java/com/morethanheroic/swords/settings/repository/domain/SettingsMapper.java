package com.morethanheroic.swords.settings.repository.domain;

import com.morethanheroic.swords.settings.repository.dao.Settings;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface SettingsMapper {

    @Insert("INSERT INTO settings SET user_id = #{user_id}")
    void insert(@Param("user_id") int userId);

    @Update("UPDATE settings SET scavenging_enabled = #{scavenging_enabled} WHERE user_id = #{user_id}")
    void saveScavengingEnabled(@Param("user_id") int userId, @Param("scavenging_enabled") boolean scavengingEnabled);

    @Select("SELECT * FROM settings WHERE user_id = #{user_id}")
    Settings getSettings(@Param("user_id") int userId);
}
