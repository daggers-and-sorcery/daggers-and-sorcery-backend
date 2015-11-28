package com.morethanheroic.swords.profile.service;

import com.beust.jcommander.internal.Lists;
import com.morethanheroic.swords.attribute.domain.GeneralAttribute;
import com.morethanheroic.swords.attribute.domain.Attribute;
import com.morethanheroic.swords.attribute.domain.type.AttributeModifierType;
import com.morethanheroic.swords.attribute.domain.type.AttributeModifierValueType;
import com.morethanheroic.swords.attribute.service.calc.domain.AttributeData;
import com.morethanheroic.swords.attribute.service.modifier.domain.AttributeModifierEntry;
import com.morethanheroic.swords.attribute.service.AttributeUtil;
import com.morethanheroic.swords.attribute.service.calc.GlobalAttributeCalculator;
import com.morethanheroic.swords.attribute.service.calc.domain.AttributeCalculationResult;
import com.morethanheroic.swords.attribute.service.modifier.domain.AttributeModifierValue;
import com.morethanheroic.swords.profile.service.response.ProfileInfoResponseBuilder;
import com.morethanheroic.swords.response.domain.Response;
import com.morethanheroic.swords.inventory.domain.InventoryEntity;
import com.morethanheroic.swords.inventory.service.InventoryFacade;
import com.morethanheroic.swords.item.service.cache.ItemDefinitionCache;
import com.morethanheroic.swords.race.model.Race;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.testng.annotations.Test;

import javax.servlet.http.HttpSession;
import java.util.*;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

public class ProfileInfoResponseBuilderServiceTest {

    @Test
    public void testBuild() {
        ProfileInfoResponseBuilder profileInfoResponseBuilder = new ProfileInfoResponseBuilder(buildGlobalAttributeCalculatorMock(), mock(ItemDefinitionCache.class), buildAttributeUtilMock(), buildInventoryManagerMock(), null, null, null, null, null);

        Response response = profileInfoResponseBuilder.build(buildUserEntityMock(), mock(HttpSession.class));

        assertEquals(response.getDataMap(), buildExpectedResult());
    }

    private HashMap<String, Object> buildExpectedResult() {
        HashMap<String, Object> expected = new HashMap<>();

        expected.put("race", Race.DRACONIC);
        expected.put("registrationDate", new Date(1111111111));
        expected.put("lastLoginDate", new Date(2222));
        expected.put("inventory", new ArrayList<HashMap<String, Object>>());
        expected.put("username", "testuser");
        expected.put("attribute", buildAttributeListExpectedResult());

        return expected;
    }

    private LinkedList<HashMap<String, Object>> buildAttributeListExpectedResult() {
        LinkedList<HashMap<String, Object>> result = new LinkedList<>();

        result.add(buildDexterityAttributeExpectedResult());
        result.add(buildWisdomAttributeExpectedResult());

        return result;
    }

    private HashMap<String, Object> buildWisdomAttributeExpectedResult() {
        HashMap<String, Object> attribute = new HashMap<>();
        attribute.put("actual", 10);
        attribute.put("maximum", 20);

        attribute.put("modifierDataArray", buildAttributeModifierExpectedResult());

        HashMap<String, Object> attributeStaticData = new HashMap<>();
        attributeStaticData.put("attributeType", "GENERAL");
        attributeStaticData.put("name", "WISDOM");
        attributeStaticData.put("generalAttributeType", "MENTAL");
        attributeStaticData.put("initialValue", 10);

        attribute.put("attribute", attributeStaticData);

        return attribute;
    }

    private HashMap<String, Object> buildDexterityAttributeExpectedResult() {
        HashMap<String, Object> attribute = new HashMap<>();
        attribute.put("actual", 10);
        attribute.put("maximum", 20);

        attribute.put("modifierDataArray", buildAttributeModifierExpectedResult());

        HashMap<String, Object> attributeStaticData = new HashMap<>();
        attributeStaticData.put("attributeType", "GENERAL");
        attributeStaticData.put("name", "DEXTERITY");
        attributeStaticData.put("generalAttributeType", "PHYSICAL");
        attributeStaticData.put("initialValue", 10);

        attribute.put("attribute", attributeStaticData);

        return attribute;
    }

    private LinkedList<HashMap<String, Object>> buildAttributeModifierExpectedResult() {
        LinkedList<HashMap<String, Object>> modifierList = new LinkedList<>();

        HashMap<String, Object> result = new HashMap<>();

        result.put("attributeModifierType", "RACIAL");
        result.put("attributeModifierValueType", "VALUE");
        result.put("attributeModifierValue", 10);

        modifierList.add(result);

        return modifierList;
    }

    private GlobalAttributeCalculator buildGlobalAttributeCalculatorMock() {
        GlobalAttributeCalculator globalAttributeCalculator = mock(GlobalAttributeCalculator.class);

        AttributeData attributeData = new AttributeData(GeneralAttribute.DEXTERITY, new AttributeCalculationResult(10, GeneralAttribute.DEXTERITY), new AttributeCalculationResult(20, GeneralAttribute.STRENGTH), Lists.newArrayList(new AttributeModifierEntry(AttributeModifierType.RACIAL, AttributeModifierValueType.VALUE, new AttributeModifierValue(10))));

        when(globalAttributeCalculator.calculateAttributeValue(any(), any())).thenReturn(attributeData);

        return globalAttributeCalculator;
    }

    private AttributeUtil buildAttributeUtilMock() {
        AttributeUtil attributeUtil = mock(AttributeUtil.class);

        Set<Attribute> attributeSet = new TreeSet<>();
        attributeSet.add(GeneralAttribute.WISDOM);
        attributeSet.add(GeneralAttribute.DEXTERITY);

        when(attributeUtil.getAllAttributes()).thenReturn(attributeSet);

        return attributeUtil;
    }

    private UserEntity buildUserEntityMock() {
        UserEntity user = mock(UserEntity.class);

        when(user.getRace()).thenReturn(Race.DRACONIC);
        when(user.getRegistrationDate()).thenReturn(new Date(1111111111));
        when(user.getLastLoginDate()).thenReturn(new Date(2222));
        when(user.getUsername()).thenReturn("testuser");

        return user;
    }

    private InventoryFacade buildInventoryManagerMock() {
        InventoryEntity inventoryEntity = mock(InventoryEntity.class);
        when(inventoryEntity.getItems()).thenReturn(new ArrayList<>());

        InventoryFacade inventoryFacade = mock(InventoryFacade.class);
        when(inventoryFacade.getInventory(any())).thenReturn(inventoryEntity);

        return inventoryFacade;
    }
}