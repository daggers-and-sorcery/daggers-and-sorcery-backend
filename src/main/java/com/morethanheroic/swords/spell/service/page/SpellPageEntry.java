package com.morethanheroic.swords.spell.service.page;

import com.morethanheroic.swords.response.domain.CharacterRefreshResponse;

public abstract class SpellPageEntry {

    public abstract CharacterRefreshResponse build(PageEntryDataContainer pageEntryDataContainer);
}
