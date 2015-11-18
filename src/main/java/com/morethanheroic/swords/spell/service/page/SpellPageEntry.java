package com.morethanheroic.swords.spell.service.page;

import com.morethanheroic.swords.response.domain.Response;

public abstract class SpellPageEntry {

    public abstract Response build(PageEntryDataContainer pageEntryDataContainer);
}
