package com.morethanheroic.swords.admin.view.controller.recipe;

import com.morethanheroic.swords.recipe.domain.RecipeDefinition;
import com.morethanheroic.swords.recipe.service.cache.RecipeDefinitionCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Profile("admin")
public class RecipeListController {

    @Autowired
    private RecipeDefinitionCache recipeDefinitionCache;

    @RequestMapping(path = "/admin/recipe/list", method = RequestMethod.GET)
    public List<RecipeDefinition> listItems() {
        return recipeDefinitionCache.getDefinitions();
    }
}
