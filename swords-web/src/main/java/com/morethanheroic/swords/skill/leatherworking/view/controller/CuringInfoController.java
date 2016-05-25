package com.morethanheroic.swords.skill.leatherworking.view.controller;

import com.morethanheroic.swords.recipe.domain.RecipeType;
import com.morethanheroic.swords.recipe.service.learn.LearnedRecipeEvaluator;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CuringInfoController {

    @Autowired
    @Qualifier("leatherworkingCuringLearnedRecipeEvaluator")
    private LearnedRecipeEvaluator learnedRecipeEvaluator;

    @RequestMapping(value = "/skill/leatherworking/curing/info", method = RequestMethod.GET)
    public Object scavengingInfo(UserEntity userEntity) {
        return learnedRecipeEvaluator.getLearnedRecipes(userEntity, RecipeType.LEATHERWORKING_CURING);
    }
}
