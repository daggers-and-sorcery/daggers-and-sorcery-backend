package com.morethanheroic.swords.inn.service;

import com.google.common.collect.Lists;
import com.morethanheroic.swords.inn.domain.ServiceType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InnServiceAvailabilityCalculator {

    public List<ServiceType> getAvailableServices() {
        return Lists.newArrayList(ServiceType.values());
    }
}
