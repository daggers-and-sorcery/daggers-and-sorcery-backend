package com.morethanheroic.swords.forum.view.controller;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.forum.service.ForumService;
import com.morethanheroic.swords.forum.view.response.domain.ForumPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.forum.view.response.request_objects.NewTopic;
import com.morethanheroic.swords.forum.view.response.service.ForumCategoryResponseBuilder;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ForumController {

    @Autowired
    private ForumCategoryResponseBuilder forumCategoryResponseBuilder;

    @Autowired
    private ForumService forumService;

    /**
     * List all available categories
     */
    @RequestMapping(value = "/forum/list/categories", method = RequestMethod.GET)
    public Response requestCategories(UserEntity userEntity) {
        return forumCategoryResponseBuilder.build(ForumPartialResponseBuilderConfiguration.builder()
                .userEntity(userEntity)
                .categories(forumService.getTopics(userEntity))
                .build()
        );
    }

    /**
     * List topics in a certain category (first 50 by default)
     */
  /*  @RequestMapping(value = "/forum/list/category/{category_id}", method = RequestMethod.GET)
    public Response requestSpecificCategory(UserEntity userEntity,@PathVariable int category_id){

    }*/

    /**
     *  Get certain topic
     */
  /*  @RequestMapping(value = "/forum/list/topic/{topic_id}", method = RequestMethod.GET)
    public Response requestSpecificTopic(UserEntity userEntity,@PathVariable int topic_id){


    }*/

    /**
     *  Get comments for topic
     */
  /*  @RequestMapping(value = "/forum/list/topic/{topic_id}/comments", method = RequestMethod.GET)
    public Response requestSpecificTopicComments(UserEntity userEntity,@PathVariable int topic_id){


    }*/

    /**
     *  Get comments for topic
     */
  /*  @RequestMapping(value = "/forum/list/topic/{topic_id}/comments", method = RequestMethod.GET)
    public Response requestSpecificTopicComments(UserEntity userEntity,@PathVariable int topic_id){

return null;
    }*/

    /**
     *  Create new topic
     */
    @RequestMapping(value = "/forum/new_topic", method = RequestMethod.POST)
    public ResponseEntity<?> requestSpecificTopicComments(UserEntity userEntity, @RequestBody NewTopic newTopic){

        forumService.createNewTopic(userEntity, newTopic);

        return new ResponseEntity<>(null, null, HttpStatus.OK);
    }
}
