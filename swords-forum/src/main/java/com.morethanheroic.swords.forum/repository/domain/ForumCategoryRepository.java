package com.morethanheroic.swords.forum.repository.domain;

import com.morethanheroic.swords.forum.repository.dao.ForumCategoriesDatabaseEntry;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface ForumCategoryRepository {



    @Insert("INSERT INTO swords.forum_categories (`name`, post_count, icon, last_post_date, last_post_user) " +
            "values (#{name},#{postCount},#{icon},#{lastPostDate},#{lastPostUser});")
    void newCategory(ForumCategoriesDatabaseEntry forumCategoriesDatabaseEntry);


}
