package com.morethanheroic.swords.forum.view.controller;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.forum.service.ForumService;
import com.morethanheroic.swords.forum.view.response.domain.category.ForumCategoryListPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.forum.view.response.service.category.ForumCategoryListResponseBuilder;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final ForumService forumService;
    private final ForumCategoryListResponseBuilder forumCategoryResponseBuilder;

    /**
     * List all available categories.
     */
    @RequestMapping(value = "/forum/list/categories", method = RequestMethod.GET)
    public Response requestCategoryList(UserEntity userEntity) {
        return forumCategoryResponseBuilder.build(
                ForumCategoryListPartialResponseBuilderConfiguration.builder()
                        .userEntity(userEntity)
                        .categories(forumService.getCategories())
                        .build()
        );
    }
}
