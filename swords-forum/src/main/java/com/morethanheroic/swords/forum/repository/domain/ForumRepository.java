package com.morethanheroic.swords.forum.repository.domain;

import com.morethanheroic.swords.forum.repository.dao.ForumCategoryDatabaseEntity;
import com.morethanheroic.swords.forum.repository.dao.ForumCommentDatabaseEntity;
import com.morethanheroic.swords.forum.repository.dao.ForumTopicDatabaseEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ForumRepository {

    @Select("SELECT * FROM forum_categories")
    List<ForumCategoryDatabaseEntity> getCategories();

    @Select("SELECT * FROM forum_categories WHERE id = #{id}")
    ForumCategoryDatabaseEntity getCategory(@Param("id") int id);

    @Select("SELECT * FROM forum_topic WHERE parent_category = #{parentCategory} ORDER BY last_post_date DESC")
    List<ForumTopicDatabaseEntity> getTopics(@Param("parentCategory") int parentCategory);

    @Select("SELECT * FROM forum_topic WHERE id = #{topicId}")
    ForumTopicDatabaseEntity getTopic(@Param("topicId") int topicId);

    @Insert("INSERT INTO swords.forum_topic (`parent_category`, name, comment_count, last_post_date, last_post_user, creator) " +
            "values (#{parentCategory},#{name},1,#{lastPostDate},#{lastPostUser},#{creator});")
    @Options(useGeneratedKeys=true)
    void newTopic(ForumTopicDatabaseEntity forumTopicDatabaseEntity);

    @Insert("INSERT INTO `forum_comment`(`topic_id`,`content`,`post_user`,`is_answer`,`answer_to_comment_id`, post_date)VALUES (#{topic_id},#{content},#{post_user_id},#{is_answer},#{answer_to_comment_id}, NOW());")
    void newComment(@Param("topic_id") int topicId, @Param("content") String content, @Param("post_user_id") int userId, @Param("is_answer") int isAnswer, @Param("answer_to_comment_id") int answerToCommentId);

    @Select("SELECT * FROM forum_comment WHERE topic_id = #{topicId}")
    List<ForumCommentDatabaseEntity> getComments(@Param("topicId") int topicId);

    @Select("SELECT * FROM forum_comment WHERE id = #{id}")
    ForumCommentDatabaseEntity getComment(@Param("id") int id);

    @Update("UPDATE forum_categories SET post_count = post_count + 1, last_post_user = #{userId}, last_post_date = NOW() WHERE id = #{id}")
    void handleNewPostOnCategory(@Param("id") int id, @Param("userId") int userId);

    @Update("UPDATE forum_topic SET comment_count = comment_count + 1, last_post_user = #{userId}, last_post_date = NOW() WHERE id = #{id}")
    void handleNewCommentOnTopic(@Param("id") int id, @Param("userId") int userId);
}
