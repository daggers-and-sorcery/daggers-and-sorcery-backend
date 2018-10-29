package com.morethanheroic.swords.shop.repository.domain;

import com.morethanheroic.swords.shop.repository.dao.ShopItemDatabaseEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

//TODO: Rename item_amount to amount
@Mapper
public interface ShopMapper {

    @Select("SELECT * FROM shop_stock WHERE shop_id = #{shop_id}")
    List<ShopItemDatabaseEntity> getItemsInShop(@Param("shop_id") int shopId);

    @Select("SELECT * FROM shop_stock WHERE shop_id = #{shop_id} AND item_id = #{item_id}")
    ShopItemDatabaseEntity getItemInShop(@Param("shop_id") int shopId, @Param("item_id") int itemId);

    //UNSAFE! If we ever going to change to master-slave based mysql setup we should fix this!
    @Insert("INSERT INTO shop_stock SET shop_id = #{shop_id}, item_id = #{item_id}, item_amount = #{item_amount} ON DUPLICATE KEY UPDATE item_amount = item_amount + #{item_amount}")
    void addStock(@Param("shop_id") int shopId, @Param("item_id") int itemId, @Param("item_amount") int amount);

    @Update("UPDATE shop_stock SET item_amount = item_amount - #{item_amount} WHERE shop_id = #{shop_id} AND item_id = #{item_id}")
    void removeStock(@Param("shop_id") int shopId, @Param("item_id") int itemId, @Param("item_amount") int amount);

    @Delete("DELETE FROM shop_stock WHERE shop_id = #{shop_id} AND item_id = #{item_id}")
    void deleteStock(@Param("shop_id") int shopId, @Param("item_id") int itemId);
}
