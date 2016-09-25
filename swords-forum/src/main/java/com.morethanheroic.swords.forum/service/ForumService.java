package com.morethanheroic.swords.forum.service;

import com.morethanheroic.swords.forum.domain.ForumCategoryEntity;
import com.morethanheroic.swords.forum.repository.dao.ForumCategoriesDatabaseEntity;
import com.morethanheroic.swords.forum.repository.dao.NewComment;
import com.morethanheroic.swords.forum.repository.dao.NewTopic;
import com.morethanheroic.swords.forum.repository.domain.ForumCategoryRepository;
import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.user.service.UserEntityFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ForumService {

    private final ForumCategoryRepository forumCategoryRepository;
    private final UserEntityFactory userEntityFactory;

    public List<ForumCategoryEntity> getCategories() {
        List<ForumCategoriesDatabaseEntity> forumCategoriesDatabaseEntities = forumCategoryRepository.getCategories();

        List<ForumCategoryEntity> result = new ArrayList<>();
        for (ForumCategoriesDatabaseEntity forumCategoriesDatabaseEntity : forumCategoriesDatabaseEntities) {
            result.add(
                    ForumCategoryEntity.builder()
                            .id(forumCategoriesDatabaseEntity.getId())
                            .name(forumCategoriesDatabaseEntity.getName())
                            .postCount(forumCategoriesDatabaseEntity.getPostCount())
                            .icon(forumCategoriesDatabaseEntity.getIcon())
                            .lastPostDate(forumCategoriesDatabaseEntity.getLastPostDate())
                            .lastPostUser(forumCategoriesDatabaseEntity.getLastPostUser() > 0 ? userEntityFactory.getEntity(forumCategoriesDatabaseEntity.getLastPostUser()) : null)
                            .build()
            );
        }

        return result;
    }

    public void createNewTopic(NewTopic newTopic) {
        forumCategoryRepository.newTopic(newTopic);
    }

    public void createNewComment(UserEntity userEntity, NewComment newComment) {
        forumCategoryRepository.newComment(
                newComment.getTopicId(),
                newComment.getContent(),
                userEntity.getId(),
                newComment.isAnswer() ? 1 : 0,
                newComment.getAnswerToCommentId()
        );
    }
}
