package com.morethanheroic.swords.forum.repository.domain;

import com.morethanheroic.swords.forum.repository.dao.ForumCategoriesDatabaseEntity;
import com.morethanheroic.swords.forum.view.response.request_objects.NewTopic;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ForumCategoryRepository {



    @Insert("INSERT INTO swords.forum_categories (`name`, post_count, icon, last_post_date, last_post_user) " +
            "values (#{name},#{postCount},#{icon},#{lastPostDate},#{lastPostUser});")
    void newCategory(ForumCategoriesDatabaseEntity forumCategoriesDatabaseEntity);

    @Select("SELECT `name`, `post_count`,`icon`,`last_post_date`,`last_post_user` FROM swords.`forum_categories`;")
    List<ForumCategoriesDatabaseEntity> getCategories();

    @Insert("INSERT INTO swords.forum_topic (`parent_category`, name, content, comment_count, last_post_date, last_post_user, creator) " +
            "values (#{name},#{postCount},#{icon},#{lastPostDate},#{lastPostUser});")
    void newTopic(NewTopic newTopic);
}
