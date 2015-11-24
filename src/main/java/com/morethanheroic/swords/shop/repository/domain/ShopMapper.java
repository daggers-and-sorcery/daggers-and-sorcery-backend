package com.morethanheroic.swords.shop.repository.domain;

import com.morethanheroic.swords.shop.repository.dao.ShopItemDatabaseEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopMapper {

    @Select("SELECT * FROM shop_stock WHERE shop_id = #{shop_id}")
    List<ShopItemDatabaseEntity> getItemsInShop(@Param("shop_id") int shopId);
}
