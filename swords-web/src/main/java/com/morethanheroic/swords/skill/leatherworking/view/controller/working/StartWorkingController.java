package com.morethanheroic.swords.skill.leatherworking.view.controller.working;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.recipe.service.definition.cache.RecipeDefinitionCache;
import com.morethanheroic.swords.skill.leatherworking.domain.LeatherworkingResult;
import com.morethanheroic.swords.skill.leatherworking.service.LeatherworkingService;
import com.morethanheroic.swords.skill.leatherworking.view.request.domain.working.LeatherworkingCreateRequest;
import com.morethanheroic.swords.skill.leatherworking.view.response.domain.configuration.working.WorkingStartResponseBuilderConfiguration;
import com.morethanheroic.swords.skill.leatherworking.view.response.service.working.WorkingStartResponseBuilder;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class StartWorkingController {

    @Autowired
    private LeatherworkingService leatherworkingService;

    @Autowired
    private RecipeDefinitionCache recipeDefinitionCache;

    @Autowired
    private WorkingStartResponseBuilder workingStartResponseBuilder;

    @RequestMapping(value = "/skill/leatherworking/working/start", method = RequestMethod.POST)
    public Response startLeatherworking(final UserEntity userEntity, @RequestBody @Valid final LeatherworkingCreateRequest leatherworkingCreateRequest) {
        final LeatherworkingResult leatherworkingResult = leatherworkingService.work(userEntity, recipeDefinitionCache.getDefinition(leatherworkingCreateRequest.getRecipeId()));

        return workingStartResponseBuilder.build(
                WorkingStartResponseBuilderConfiguration.builder()
                        .userEntity(userEntity)
                        .leatherworkingResult(leatherworkingResult)
                        .build()
        );
    }
}
