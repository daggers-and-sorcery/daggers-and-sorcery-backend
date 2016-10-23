package com.morethanheroic.swords.market.repository.repository;

import com.morethanheroic.swords.market.repository.domain.MarketDatabaseEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface MarketMapper {

    MarketDatabaseEntity getMarketInfo(final int id);
}
