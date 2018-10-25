package com.morethanheroic.swords.journal.repository.domain;

import com.morethanheroic.swords.journal.domain.JournalType;
import com.morethanheroic.swords.journal.repository.dao.JournalDatabaseEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface JournalMapper {

    @Insert("INSERT INTO journal SET user_id = #{user_id}, journal_type = #{type}, journal_id = #{journal_id} ON DUPLICATE KEY UPDATE user_id = user_id")
    void createJournal(@Param("user_id")int userId, @Param("type") JournalType type,@Param("journal_id") int journalId);

    @Select("SELECT * FROM journal WHERE user_id = #{user_id} AND journal_type = #{type}")
    List<JournalDatabaseEntity> getJournalEntryListByType(@Param("user_id")int userId, @Param("type") JournalType type);

    @Select("SELECT * FROM journal WHERE user_id = #{user_id} AND journal_type = #{type} AND journal_id = #{journal_id}")
    JournalDatabaseEntity getJournal(@Param("user_id")int userId, @Param("type") JournalType type,@Param("journal_id") int journalId);
}
