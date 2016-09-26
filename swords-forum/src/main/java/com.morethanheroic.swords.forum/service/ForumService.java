package com.morethanheroic.swords.forum.service;

import com.morethanheroic.swords.forum.domain.ForumCategoryEntity;
import com.morethanheroic.swords.forum.domain.ForumTopicEntity;
import com.morethanheroic.swords.forum.repository.dao.ForumCategoryDatabaseEntity;
import com.morethanheroic.swords.forum.repository.dao.ForumTopicDatabaseEntity;
import com.morethanheroic.swords.forum.repository.dao.NewComment;
import com.morethanheroic.swords.forum.repository.dao.NewTopic;
import com.morethanheroic.swords.forum.repository.domain.ForumRepository;
import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.user.service.UserEntityFactory;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ForumService {

    private final ForumRepository forumRepository;
    private final UserEntityFactory userEntityFactory;

    public List<ForumCategoryEntity> getCategories() {
        List<ForumCategoryDatabaseEntity> forumCategoriesDatabaseEntities = forumRepository.getCategories();

        List<ForumCategoryEntity> result = new ArrayList<>();
        for (ForumCategoryDatabaseEntity forumCategoryDatabaseEntity : forumCategoriesDatabaseEntities) {
            result.add(transformCategoryEntity(forumCategoryDatabaseEntity));
        }

        return result;
    }

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

    public List<ForumTopicEntity> getTopics(final int categoryId) {
        final List<ForumTopicDatabaseEntity> forumTopics = forumRepository.getTopics(categoryId);

        List<ForumTopicEntity> result = new ArrayList<>();
        for (ForumTopicDatabaseEntity forumTopicDatabaseEntity : forumTopics) {
            result.add(
                    ForumTopicEntity.builder()
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

    public void createNewTopic(NewTopic newTopic) {
        forumRepository.newTopic(newTopic);
    }

    public void createNewComment(UserEntity userEntity, NewComment newComment) {
        forumRepository.newComment(
                newComment.getTopicId(),
                newComment.getContent(),
                userEntity.getId(),
                newComment.isAnswer() ? 1 : 0,
                newComment.getAnswerToCommentId()
        );
    }
}
