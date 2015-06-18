package com.swordssorcery.server.controller.user;

import com.swordssorcery.server.model.AttributeData;
import com.swordssorcery.server.model.DefaultAttributeData;
import com.swordssorcery.server.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Controller
public class CharacterController {

    @Autowired
    private UserRepository userRepository;

    @ResponseBody
    @RequestMapping(value = "/character/info", method = RequestMethod.GET)
    public HashMap<String, HashMap<String, AttributeData>> info(HttpSession session) {
        HashMap<String, HashMap<String, AttributeData>> response = new HashMap<>();

        HashMap<String, AttributeData> basicAttributes = new HashMap<>();
        response.put("basic", basicAttributes);

        DefaultAttributeData movementPoint = new DefaultAttributeData();
        movementPoint.setActual(30);
        movementPoint.setMaximum(30);
        basicAttributes.put("movement", movementPoint);

        HashMap<String, AttributeData> combatAttributes = new HashMap<>();
        response.put("combat", combatAttributes);

        DefaultAttributeData lifepoint = new DefaultAttributeData();
        lifepoint.setActual(30);
        lifepoint.setMaximum(30);
        combatAttributes.put("life", lifepoint);

        DefaultAttributeData manapoint = new DefaultAttributeData();
        manapoint.setActual(30);
        manapoint.setMaximum(30);
        combatAttributes.put("mana", manapoint);

        DefaultAttributeData initiationpoint = new DefaultAttributeData();
        initiationpoint.setActual(30);
        combatAttributes.put("initiation", initiationpoint);

        DefaultAttributeData attackpoint = new DefaultAttributeData();
        attackpoint.setActual(30);
        combatAttributes.put("attack", attackpoint);

        DefaultAttributeData aimingpoint = new DefaultAttributeData();
        aimingpoint.setActual(30);
        combatAttributes.put("aiming", aimingpoint);

        return response;
    }
}
