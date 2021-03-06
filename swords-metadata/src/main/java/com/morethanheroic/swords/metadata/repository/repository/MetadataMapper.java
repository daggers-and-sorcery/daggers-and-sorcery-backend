package com.morethanheroic.swords.metadata.repository.repository;

import com.morethanheroic.swords.metadata.repository.dao.MetadataDatabaseEntity;
import org.apache.ibatis.annotations.*;

@Mapper
public interface MetadataMapper {

    @Select("SELECT * FROM user_meta WHERE user_id = #{user_id} AND meta_id = #{meta_id}")
    MetadataDatabaseEntity getMetadata(@Param("user_id") int userId, @Param("meta_id") int metaId);

    @Insert("INSERT INTO user_meta SET user_id = #{user_id}, meta_id = #{meta_id}, meta_value = #{meta_value} ON DUPLICATE KEY UPDATE meta_value = #{meta_value}")
    void setMetadata(@Param("user_id") int userId, @Param("meta_id") int metaId, @Param("meta_value") int metaValue);

    @Delete("DELETE FROM user_meta WHERE meta_id = #{meta_id}")
    void deleteAllMetadataWithId(@Param("meta_id") int metaId);
}
