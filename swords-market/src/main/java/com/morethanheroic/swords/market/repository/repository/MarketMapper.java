package com.morethanheroic.swords.market.repository.repository;

import com.morethanheroic.swords.market.repository.domain.MarketDatabaseEntity;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarketMapper {

    @Select("SELECT * FROM market WHERE id = #{id}")
    MarketDatabaseEntity getMarketInfo(@Param("id") final int id);

    @Select("SELECT item FROM market GROUP BY item")
    List<Integer> getAllItemId();
}
