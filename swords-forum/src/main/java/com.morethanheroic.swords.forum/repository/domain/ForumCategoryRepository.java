package com.morethanheroic.swords.forum.repository.domain;

import com.morethanheroic.swords.forum.repository.dao.ForumCategoriesDatabaseEntity;
import com.morethanheroic.swords.forum.repository.dao.NewComment;
import com.morethanheroic.swords.forum.repository.dao.NewTopic;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface ForumCategoryRepository {

    @Select("SELECT `name`, `post_count`,`icon`,`last_post_date`,`last_post_user` FROM swords.`forum_categories`;")
    List<ForumCategoriesDatabaseEntity> getCategories();

    @Insert("INSERT INTO swords.forum_topic (`parent_category`, name, content, comment_count, last_post_date, last_post_user, creator) " +
            "values (#{parentCategory},#{name},#{content},#{commentCount},#{lastPostDate},#{lastPostUser},#{creator});")
    void newTopic(NewTopic newTopic);

    @Insert("INSERT INTO `forum_comment`(`topic_id`,`content`,`post_user`,`is_answer`,`answer_to_comment_id`)VALUES (#{topic_id},#{content},#{post_user_id},#{is_answer},#{answer_to_comment_id});")
    void newComment(@Param("topic_id") int topicId, @Param("content") String content, @Param("post_user_id") int userId, @Param("is_answer") int isAnswer, @Param("answer_to_comment_id") int answerToCommentId);
}
