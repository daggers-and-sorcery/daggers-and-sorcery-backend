package com.morethanheroic.swords.forum.service;

import com.morethanheroic.swords.forum.domain.ForumCategoryEntity;
import com.morethanheroic.swords.forum.repository.dao.ForumCategoriesDatabaseEntity;
import com.morethanheroic.swords.forum.repository.domain.ForumCategoryRepository;
import com.morethanheroic.swords.forum.view.response.request_objects.NewTopic;
import com.morethanheroic.swords.response.service.ResponseFactory;
import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.user.service.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 2016. 08. 01..
 */


@Service
public class ForumService {

    @Autowired
    private ResponseFactory responseFactory;

    @Autowired
    private ForumCategoryRepository forumCategoryRepository;

    @Autowired
    private UserFacade userFacade;

    public List<ForumCategoryEntity> getTopics(UserEntity userEntity) {
        List<ForumCategoriesDatabaseEntity> forumCategoriesDatabaseEntities = forumCategoryRepository.getCategories();

        List<ForumCategoryEntity> result = new ArrayList<>();
        for (ForumCategoriesDatabaseEntity forumCategoriesDatabaseEntity : forumCategoriesDatabaseEntities) {
            result.add(
                    ForumCategoryEntity.builder()
                            .name(forumCategoriesDatabaseEntity.getName())
                            .postCount(forumCategoriesDatabaseEntity.getPostCount())
                            .icon(forumCategoriesDatabaseEntity.getIcon())
                            .lastPostDate(forumCategoriesDatabaseEntity.getLastPostDate())
                            .lastPostUser(userFacade.getUser(forumCategoriesDatabaseEntity.getLastPostUser()))
                            .build()
            );
        }

        return result;
    }

    public boolean createNewTopic(UserEntity userEntity, NewTopic newTopic){
        forumCategoryRepository.newTopic(newTopic);
        return true;//nop
    }
}
