package com.morethanheroic.swords.forum.service;

import com.morethanheroic.swords.forum.domain.ForumCategoryEntity;
import com.morethanheroic.swords.forum.domain.ForumCommentEntity;
import com.morethanheroic.swords.forum.domain.ForumTopicEntity;
import com.morethanheroic.swords.forum.repository.dao.*;
import com.morethanheroic.swords.forum.repository.domain.ForumRepository;
import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.user.service.UserEntityFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ForumService {

    private final ForumRepository forumRepository;
    private final UserEntityFactory userEntityFactory;

    @Transactional(readOnly = true)
    public List<ForumCategoryEntity> getCategories() {
        List<ForumCategoryDatabaseEntity> forumCategoriesDatabaseEntities = forumRepository.getCategories();

        List<ForumCategoryEntity> result = new ArrayList<>();
        for (ForumCategoryDatabaseEntity forumCategoryDatabaseEntity : forumCategoriesDatabaseEntities) {
            result.add(transformCategoryEntity(forumCategoryDatabaseEntity));
        }

        return result;
    }

    @Transactional(readOnly = true)
    public ForumCategoryEntity getCategory(final int categoryId) {
        return transformCategoryEntity(forumRepository.getCategory(categoryId));
    }

    private ForumCategoryEntity transformCategoryEntity(final ForumCategoryDatabaseEntity forumCategoryDatabaseEntity) {
        return ForumCategoryEntity.builder()
                .id(forumCategoryDatabaseEntity.getId())
                .name(forumCategoryDatabaseEntity.getName())
                .postCount(forumCategoryDatabaseEntity.getPostCount())
                .icon(forumCategoryDatabaseEntity.getIcon())
                .lastPostDate(forumCategoryDatabaseEntity.getLastPostDate())
                .lastPostUser(forumCategoryDatabaseEntity.getLastPostUser() > 0 ? userEntityFactory.getEntity(forumCategoryDatabaseEntity.getLastPostUser()) : null)
                .build();
    }

    @Transactional(readOnly = true)
    public List<ForumTopicEntity> getTopics(final int categoryId) {
        final List<ForumTopicDatabaseEntity> forumTopics = forumRepository.getTopics(categoryId);

        final List<ForumTopicEntity> result = new ArrayList<>();
        for (ForumTopicDatabaseEntity forumTopicDatabaseEntity : forumTopics) {
            result.add(
                    ForumTopicEntity.builder()
                            .id(forumTopicDatabaseEntity.getId())
                            .commentCount(forumTopicDatabaseEntity.getCommentCount())
                            .creator(userEntityFactory.getEntity(forumTopicDatabaseEntity.getCreator()))
                            .lastPostDate(forumTopicDatabaseEntity.getLastPostDate())
                            .lastPoster(userEntityFactory.getEntity(forumTopicDatabaseEntity.getLastPostUser()))
                            .name(forumTopicDatabaseEntity.getName())
                            .parent(transformCategoryEntity(forumRepository.getCategory(forumTopicDatabaseEntity.getParentCategory())))
                            .build()
            );
        }
        return result;
    }

    @Transactional(readOnly = true)
    public ForumTopicEntity getTopic(final int topicId) {
        final ForumTopicDatabaseEntity forumTopic = forumRepository.getTopic(topicId);

        return ForumTopicEntity.builder()
                .id(forumTopic.getId())
                .commentCount(forumTopic.getCommentCount())
                .creator(userEntityFactory.getEntity(forumTopic.getCreator()))
                .lastPostDate(forumTopic.getLastPostDate())
                .lastPoster(userEntityFactory.getEntity(forumTopic.getLastPostUser()))
                .name(forumTopic.getName())
                .parent(transformCategoryEntity(forumRepository.getCategory(forumTopic.getParentCategory())))
                .build();
    }

    @Transactional(readOnly = true)
    public List<ForumCommentEntity> getComments(final int topicId) {
        final List<ForumCommentDatabaseEntity> comments = forumRepository.getComments(topicId);

        final List<ForumCommentEntity> result = new ArrayList<>();
        for (ForumCommentDatabaseEntity comment : comments) {
            result.add(transformCommentEntity(comment));
        }
        return result;
    }

    private ForumCommentEntity transformCommentEntity(final ForumCommentDatabaseEntity comment) {
        return ForumCommentEntity.builder()
                .id(comment.getId())
                .answerToComment(comment.getAnswerToComment() > 0 ? transformCommentEntity(forumRepository.getComment(comment.getAnswerToComment())) : null)
                .content(comment.getContent())
                .postDate(comment.getPostDate().getTime())
                .postUser(userEntityFactory.getEntity(comment.getPostUser()))
                .build();
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void createTopic(final UserEntity userEntity, final CreateTopicContext createTopicContext) {
        final ForumTopicDatabaseEntity newForumTopicEntity = new ForumTopicDatabaseEntity();

        newForumTopicEntity.setCommentCount(1);
        newForumTopicEntity.setCreator(userEntity.getId());
        newForumTopicEntity.setLastPostDate(new Date());
        newForumTopicEntity.setLastPostUser(userEntity.getId());
        newForumTopicEntity.setName(createTopicContext.getName());
        newForumTopicEntity.setParentCategory(createTopicContext.getParentCategory());

        forumRepository.newTopic(newForumTopicEntity);
        forumRepository.newComment(
                newForumTopicEntity.getId(),
                createTopicContext.getContent(),
                userEntity.getId(),
                0,
                0
        );
        forumRepository.handleNewPostOnCategory(createTopicContext.getParentCategory(), userEntity.getId());
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void createNewComment(UserEntity userEntity, CreateCommentContext createCommentContext) {
        final ForumTopicDatabaseEntity forumTopicDatabaseEntity = forumRepository.getTopic(createCommentContext.getTopicId());

        forumRepository.newComment(
                createCommentContext.getTopicId(),
                createCommentContext.getContent(),
                userEntity.getId(),
                createCommentContext.isAnswer() ? 1 : 0,
                createCommentContext.getAnswerToCommentId()
        );
        forumRepository.handleNewCommentOnTopic(createCommentContext.getTopicId(), userEntity.getId());
        forumRepository.handleNewPostOnCategory(forumTopicDatabaseEntity.getParentCategory(), userEntity.getId());
    }
}
