package com.morethanheroic.swords.equipment.service;

import com.morethanheroic.swords.cache.value.ValueCacheProvider;
import com.morethanheroic.swords.equipment.repository.dao.EquipmentDatabaseEntity;
import com.morethanheroic.swords.equipment.repository.domain.EquipmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
//TODO: Use userEntity instead of Integer
public class EquipmentValueCacheProvider implements ValueCacheProvider<EquipmentDatabaseEntity, Integer> {

    @Autowired
    private EquipmentMapper equipmentMapper;

    @Override
    public EquipmentDatabaseEntity getCacheEntity(Integer id) {
        return equipmentMapper.getEquipment(id);
    }
}
