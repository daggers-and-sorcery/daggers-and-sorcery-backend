package com.morethanheroic.swords.market.repository.repository;

import com.morethanheroic.swords.market.repository.domain.MarketDatabaseEntity;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface MarketMapper {

    @Select("SELECT * FROM market WHERE id = #{id}")
    MarketDatabaseEntity getMarketInfo(@Param("id") final int id);
}
