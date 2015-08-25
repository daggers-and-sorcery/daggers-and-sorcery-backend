package com.morethanheroic.swords.user.view.controller;

import com.morethanheroic.swords.race.model.Race;
import com.morethanheroic.swords.user.repository.dao.UserDatabaseEntity;
import com.morethanheroic.swords.user.repository.domain.UserMapper;
import com.morethanheroic.swords.user.service.UserManager;
import com.morethanheroic.swords.user.view.request.RegistrationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

@RestController
public class UserRegistrationController {

    private final ShaPasswordEncoder shaPasswordEncoder;
    private final UserManager userManager;

    @Autowired
    public UserRegistrationController(ShaPasswordEncoder shaPasswordEncoder, UserManager userManager) {
        this.shaPasswordEncoder = shaPasswordEncoder;
        this.userManager = userManager;
    }

    //TODO: if ever refactor this use Response instead!
    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    public ResponseEntity<Object> register(@Valid @RequestBody RegistrationRequest registrationRequest, BindingResult result) throws UnsupportedEncodingException {
        if (!registrationRequest.getPasswordFirst().equals(registrationRequest.getPasswordSecond())) {
            result.addError(new ObjectError(String.valueOf(UserDatabaseEntity.class), "The two passwords must be equals."));
        }

        if (result.hasErrors()) {
            ArrayList<String> resultAsList = new ArrayList<>();

            for (ObjectError error : result.getAllErrors()) {
                resultAsList.add(error.getDefaultMessage());
            }

            return new ResponseEntity<>(resultAsList, HttpStatus.BAD_REQUEST);
        } else {
            String username = registrationRequest.getUsername();
            String password = shaPasswordEncoder.encodePassword(registrationRequest.getPasswordFirst(), null);
            String email = registrationRequest.getEmail();
            Race race = Race.valueOf(registrationRequest.getRace());

            userManager.saveNewUser(username, password, email, race);

            //TODO: add user email validation

            return new ResponseEntity<>("{}", HttpStatus.OK);
        }
    }
}
