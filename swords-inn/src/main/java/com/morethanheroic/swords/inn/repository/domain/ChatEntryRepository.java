package com.morethanheroic.swords.inn.repository.domain;

import com.morethanheroic.swords.inn.repository.dao.ChatDatabaseEntry;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatEntryRepository {

    @Select("SELECT * FROM chat LIMIT 25 ORDER BY writingDate")
    List<ChatDatabaseEntry> getLastTwentyFiveMessage();
}
