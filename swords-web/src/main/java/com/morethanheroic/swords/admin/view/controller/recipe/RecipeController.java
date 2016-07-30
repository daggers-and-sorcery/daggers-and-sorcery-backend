package com.morethanheroic.swords.admin.view.controller.recipe;

import com.morethanheroic.swords.recipe.domain.RecipeDefinition;
import com.morethanheroic.swords.recipe.service.cache.RecipeDefinitionCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Profile("development")
public class RecipeController {

    @Autowired
    private RecipeDefinitionCache recipeDefinitionCache;

    @RequestMapping(path = "/admin/recipe/{id}", method = RequestMethod.GET)
    public RecipeDefinition getRecipe(@PathVariable int id) {
        return recipeDefinitionCache.getDefinition(id);
    }
}
