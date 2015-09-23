package com.morethanheroic.swords.news.repository.domain;

import com.morethanheroic.swords.news.repository.dao.NewsDatabaseEntity;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface NewsMapper {

    @Select(value = "SELECT * FROM news ORDER BY release_date DESC LIMIT 10")
    List<NewsDatabaseEntity> findLastTen();
}