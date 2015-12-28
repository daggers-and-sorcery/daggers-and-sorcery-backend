package com.morethanheroic.swords.attribute.service;

import com.morethanheroic.swords.attribute.service.calc.GlobalAttributeCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttributeFacade {

    @Autowired
    private GlobalAttributeCalculator globalAttributeCalculator;
}
