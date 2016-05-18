package com.morethanheroic.swords.inn.web.chat;

import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InnChatSendController {

    @RequestMapping(value = "/inn/chat/send", method = RequestMethod.POST)
    public String sendNewMessages(UserEntity userEntity) {
        return null;
    }
}
