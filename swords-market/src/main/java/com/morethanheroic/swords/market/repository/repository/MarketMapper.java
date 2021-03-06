package com.morethanheroic.swords.market.repository.repository;

import com.morethanheroic.swords.market.repository.domain.MarketDatabaseEntity;
import com.morethanheroic.swords.market.repository.domain.MarketDatabaseInformation;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MarketMapper {

    @Select("SELECT * FROM market WHERE id = #{id}")
    MarketDatabaseEntity getMarketInfo(@Param("id") final int id);

    @Select("SELECT item, SUM(amount) as amount, MIN(price) as price FROM market GROUP BY item")
    List<MarketDatabaseInformation> getAllItem();

    @Select("SELECT * FROM market WHERE item = #{itemId}")
    List<MarketDatabaseEntity> getOffersForItem(@Param("itemId") final int itemId);

    @Select("SELECT * FROM market WHERE seller = #{seller}")
    List<MarketDatabaseEntity> getMyOffers(@Param("seller") final int seller);

    @Insert("INSERT INTO market SET seller = #{seller}, item = #{item}, price = #{price}, amount = #{amount}")
    void insertMarketOffer(@Param("seller") final int seller, @Param("item") final int item, @Param("price") final int price, @Param("amount") final int amount);

    @Delete("DELETE FROM market WHERE id = #{id}")
    void removeMarketOffer(@Param("id") final int id);

    @Update("UPDATE market SET amount = amount - 1 WHERE id = #{id}")
    void decreaseAmountOnMarketOffer(@Param("id") final int id);
}
