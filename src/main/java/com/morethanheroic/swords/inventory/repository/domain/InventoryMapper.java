package com.morethanheroic.swords.inventory.repository.domain;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

public interface InventoryMapper {

    //UNSAFE! If we ever going to change to master-slave based mysql setup we should fix this!
    @Insert("INSERT INTO inventory SET user_id = #{user_id}, item_id = #{item_id}, amount = #{amount} ON DUPLICATE KEY UPDATE amount = amount + #{amount}")
    void addItem(@Param("user_id") int userId, @Param("item_id") int itemId, @Param("amount") int amount);
}
