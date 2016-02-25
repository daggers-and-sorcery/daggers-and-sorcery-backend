package com.morethanheroic.swords.metadata.repository.repository;

import com.morethanheroic.swords.metadata.repository.dao.MetadataDatabaseEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface MetadataMapper {

    @Select("SELECT * FROM inventory WHERE user_id = #{user_id} AND meta_id = #{meta_id}")
    MetadataDatabaseEntity getMetadata(@Param("user_id") int userId, @Param("meta_id") int metaId);
}
