package com.morethanheroic.swords.combat.service.executor.action.resolver;

import com.morethanheroic.swords.settings.model.SettingType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class CombatSettingsActionHandlerResolverProvider {

    private final List<CombatSettingsActionHandlerResolver> combatSettingsActionHandlerResolvers;

    private final Map<SettingType, CombatSettingsActionHandlerResolver> combatSettingsActionHandlerResolverMap = new EnumMap<>(SettingType.class);

    @PostConstruct
    private void initialize() {
        for (CombatSettingsActionHandlerResolver combatSettingsActionHandlerResolver : combatSettingsActionHandlerResolvers) {
            combatSettingsActionHandlerResolverMap.put(combatSettingsActionHandlerResolver.getSupportedSettingType(), combatSettingsActionHandlerResolver);
        }
    }

    public CombatSettingsActionHandlerResolver getResolver(SettingType settingType) {
        Assert.isTrue(combatSettingsActionHandlerResolverMap.containsKey(settingType));

        return combatSettingsActionHandlerResolverMap.get(settingType);
    }
}
