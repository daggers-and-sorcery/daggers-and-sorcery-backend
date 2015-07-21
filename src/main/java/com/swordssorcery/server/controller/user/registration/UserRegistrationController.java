package com.swordssorcery.server.controller.user.registration;

import com.swordssorcery.server.game.race.Race;
import com.swordssorcery.server.model.db.user.UserDatabaseEntity;
import com.swordssorcery.server.model.db.user.UserRepository;
import com.swordssorcery.server.controller.user.registration.request.RegistrationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

@RestController
public class UserRegistrationController {

    @Autowired
    private ShaPasswordEncoder shaPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

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

            UserDatabaseEntity user = new UserDatabaseEntity(username, password);
            user.setEmail(registrationRequest.getEmail());
            user.setRace(Race.valueOf(registrationRequest.getRace()));

            userRepository.save(user);

            //TODO: add user email validation

            return new ResponseEntity<>("{}", HttpStatus.OK);
        }
    }
}
