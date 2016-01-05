package com.morethanheroic.swords.user.view.controller;

import com.morethanheroic.swords.race.model.Race;
import com.morethanheroic.swords.security.PasswordEncoder;
import com.morethanheroic.swords.skill.service.SkillFacade;
import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.user.repository.dao.UserDatabaseEntity;
import com.morethanheroic.swords.user.service.UserFacade;
import com.morethanheroic.swords.user.view.request.RegistrationRequest;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles all registration related requests.
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserRegistrationController {

    @NonNull
    private final PasswordEncoder passwordEncoder;

    @NonNull
    private final UserFacade userFacade;

    @NonNull
    private final SkillFacade skillFacade;

    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    @Transactional
    //TODO: Use Response.
    public ResponseEntity<Object> register(@Valid @RequestBody RegistrationRequest registrationRequest, BindingResult result) {
        if (!registrationRequest.getPasswordFirst().equals(registrationRequest.getPasswordSecond())) {
            result.addError(new ObjectError(String.valueOf(UserDatabaseEntity.class), "The two passwords must be equals."));
        }

        if (result.hasErrors()) {
            final List<String> resultAsList = new ArrayList<>();

            for (ObjectError error : result.getAllErrors()) {
                resultAsList.add(error.getDefaultMessage());
            }

            return new ResponseEntity<>(resultAsList, HttpStatus.BAD_REQUEST);
        } else {
            final String username = registrationRequest.getUsername();
            final String password = passwordEncoder.encodePassword(registrationRequest.getPasswordFirst());
            final String email = registrationRequest.getEmail();
            final Race race = Race.valueOf(registrationRequest.getRace());

            final UserEntity userEntity = userFacade.createUser(username, password, email, race);

            skillFacade.createSkillsForUser(userEntity);

            //TODO: enable these when they are refactored out of the main package! That should be done asap!
            //equipmentManager.createSkillsForUser(userEntity);
            //settingsManager.createSettingsForUser(userEntity);
            //movementFacade.createMovementForUser(userEntity);

            //TODO: add user email validation

            return new ResponseEntity<>("{}", HttpStatus.OK);
        }
    }
}
