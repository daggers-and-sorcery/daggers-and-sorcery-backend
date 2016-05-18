package com.morethanheroic.swords.inn.web.chat;

import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InnChatListController {

    @RequestMapping(value = "/inn/chat/list", method = RequestMethod.GET)
    public String listChatMessages(UserEntity userEntity) {
        return null;
    }
}
