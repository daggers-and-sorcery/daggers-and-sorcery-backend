package com.morethanheroic.swords.journal.repository.domain;

import com.morethanheroic.swords.journal.model.JournalType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

public interface JournalMapper {

    @Insert("INSERT INTO journal SET user_id = #{user_id}, journal_type = #{type}, journal_id = #{journal_id} ON DUPLICATE KEY UPDATE user_id = user_id")
    void createJournal(@Param("user_id")int userId, @Param("type") JournalType type,@Param("journal_id") int journalId);
}
