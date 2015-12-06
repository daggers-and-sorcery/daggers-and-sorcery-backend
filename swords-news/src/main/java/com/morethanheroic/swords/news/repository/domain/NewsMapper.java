package com.morethanheroic.swords.news.repository.domain;

import com.morethanheroic.swords.news.repository.dao.NewsDatabaseEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface NewsMapper {

    @Select(value = "SELECT * FROM news ORDER BY release_date DESC LIMIT #{amount}")
    List<NewsDatabaseEntity> findLast(@Param("amount") int amount);

    @Select("SELECT * FROM news WHERE id = #{id}")
    NewsDatabaseEntity findNewsEntity(@Param("id") int id);
}