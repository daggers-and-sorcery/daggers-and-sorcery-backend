package com.swordssorcery.server.controller.user;

import com.swordssorcery.server.model.User;
import com.swordssorcery.server.model.repository.UserRepository;
import com.swordssorcery.server.session.SessionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ShaPasswordEncoder shaPasswordEncoder;
    @Autowired
    private Validator validator;

    @ResponseBody
    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public HashMap<String, String> login(HttpSession session, @RequestParam String username, @RequestParam String password) throws UnsupportedEncodingException {
        User login = userRepository.findByUsernameAndPassword(username, shaPasswordEncoder.encodePassword(password, null));

        HashMap<String, String> response = new HashMap<>();

        if (login != null) {
            response.put("success", "true");

            session.setAttribute(SessionType.USER, login);
        } else {
            response.put("success", "false");
            response.put("error", "Wrong username or password!");
        }

        return response;
    }

    @ResponseBody
    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    public ResponseEntity<Object> register(@Valid @RequestBody RegistrationRequest registrationRequest, BindingResult result) throws UnsupportedEncodingException {
        if (!registrationRequest.getPasswordFirst().equals(registrationRequest.getPasswordSecond())) {
            result.addError(new ObjectError(String.valueOf(User.class), "The two passwords must be equals."));
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

            User user = new User(username, password);
            user.setEmail(registrationRequest.getEmail());
            user.setRace(registrationRequest.getRace());

            userRepository.save(user);

            //TODO: add user email validation

            return new ResponseEntity<>("{}", HttpStatus.OK);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/user/info", method = RequestMethod.GET)
    public HashMap<String, String> info(HttpSession session) {
        HashMap<String, String> response = new HashMap<>();

        response.put("loggedIn", String.valueOf(session.getAttribute(SessionType.USER) != null));

        return response;
    }

    @ResponseBody
    @RequestMapping(value = "/user/logout", method = RequestMethod.GET)
    public HashMap<String, String> logout(HttpSession session) {
        session.invalidate();

        HashMap<String, String> response = new HashMap<>();

        response.put("loggedIn", "false");

        return response;
    }
}
