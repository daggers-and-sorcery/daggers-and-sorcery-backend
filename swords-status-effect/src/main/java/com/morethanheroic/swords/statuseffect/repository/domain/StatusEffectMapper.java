package com.morethanheroic.swords.statuseffect.repository.domain;

import com.morethanheroic.swords.statuseffect.repository.dao.StatusEffectDatabaseEntity;
import org.apache.ibatis.annotations.*;

import java.time.Instant;
import java.util.List;

/**
 * Contains the database requests for the status effect domain.
 */
@Mapper
public interface StatusEffectMapper {

    @Select("SELECT * FROM status_effect WHERE user_id = #{userId}")
    List<StatusEffectDatabaseEntity> getStatusEffects(@Param("userId") final int userId);

    @Insert("INSERT INTO status_effect SET user_id = #{userId}, status_effect_id = #{statusEffectId}, expiration_time = #{expiration_time}")
    void giveStatusEffect(@Param("userId") final int userId, @Param("statusEffectId") final int statusEffectId, @Param("expiration_time") final Instant expirationTime);

    @Update("UPDATE status_effect SET expiration_time = #{expiration_time} WHERE user_id = #{userId} AND status_effect_id = #{statusEffectId}")
    void refreshStatusEffect(@Param("userId") final int userId, @Param("statusEffectId") final int statusEffectId, @Param("expiration_time") final Instant expirationTime);

    @Select("SELECT EXISTS(SELECT 1 FROM status_effect WHERE user_id = #{userId} AND status_effect_id = #{statusEffectId})")
    boolean hasStatusEffect(@Param("userId") final int userId, @Param("statusEffectId") final int statusEffectId);

    @Delete("DELETE FROM status_effect WHERE expiration_time < NOW()")
    void removeExpiredStatusEffects();
}
