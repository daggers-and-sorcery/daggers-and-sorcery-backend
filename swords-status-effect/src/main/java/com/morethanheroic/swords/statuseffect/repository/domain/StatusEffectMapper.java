package com.morethanheroic.swords.statuseffect.repository.domain;

import com.morethanheroic.swords.statuseffect.repository.dao.StatusEffectDatabaseEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

/**
 * Contains the database requests for the status effect domain.
 */
@Repository
public interface StatusEffectMapper {

    @Select("SELECT * FROM status_effect WHERE user_id = #{userId}")
    List<StatusEffectDatabaseEntity> getAllStatusEffects(@Param("userId") final int userId);

    @Insert("INSERT INTO status_effect SET user_id = #{userId}, status_effect_id = #{statusEffectId}, expiration_time = #{expiration_time}")
    void giveStatusEffect(@Param("userId") final int userId, @Param("statusEffectId") final int statusEffectId, @Param("expiration_time") final Instant expirationTime);

    @Delete("DELETE FROM status_effect WHERE expiration_time < NOW()")
    void removeExpiredEvents();
}
