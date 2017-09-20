package com.morethanheroic.swords.equipment.view.service;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.response.service.ResponseBuilder;
import com.morethanheroic.swords.equipment.domain.EquipmentSlot;
import com.morethanheroic.swords.equipment.view.service.domain.EquipmentPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.equipment.view.service.domain.ListEquipmentResponseBuilderConfiguration;
import com.morethanheroic.swords.response.service.ResponseFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ListEquipmentResponseBuilder implements ResponseBuilder<ListEquipmentResponseBuilderConfiguration> {

    private final ResponseFactory responseFactory;
    private final EquipmentPartialResponseBuilder equipmentPartialResponseBuilder;

    @Override
    public Response build(final ListEquipmentResponseBuilderConfiguration listEquipmentResponseBuilderConfiguration) {
        final Response response = responseFactory.newResponse(listEquipmentResponseBuilderConfiguration.getUserEntity());

        response.setData("equipment", Arrays.stream(EquipmentSlot.values())
                .map(equipmentSlot -> equipmentPartialResponseBuilder.build(
                        EquipmentPartialResponseBuilderConfiguration.builder()
                                .equipmentSlot(equipmentSlot)
                                .equipmentEntity(listEquipmentResponseBuilderConfiguration.getEquipmentEntity())
                                .sessionEntity(listEquipmentResponseBuilderConfiguration.getSessionEntity())
                                .build()
                        )
                )
                .collect(Collectors.toList())
        );

        return response;
    }
}
