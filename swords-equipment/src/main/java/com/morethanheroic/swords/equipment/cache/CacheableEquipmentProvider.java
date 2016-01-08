package com.morethanheroic.swords.equipment.cache;

import com.morethanheroic.swords.equipment.repository.dao.EquipmentDatabaseEntity;
import com.morethanheroic.swords.equipment.repository.domain.EquipmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CacheableEquipmentProvider implements Cacheable<EquipmentDatabaseEntity, Integer> {

    @Autowired
    private EquipmentMapper equipmentMapper;

    @Override
    public EquipmentDatabaseEntity getCacheEntity(Integer id) {
        return equipmentMapper.getEquipment(id);
    }
}
