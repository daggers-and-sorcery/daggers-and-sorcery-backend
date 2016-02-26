package com.morethanheroic.swords.user.service.factory;

import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.user.service.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserEntityFactory implements com.morethanheroic.user.service.UserEntityFactory<UserEntity> {

    private final UserFacade userFacade;

    public UserEntity getUserEntity(int id) {
        return userFacade.getUser(id);
    }

    public UserEntity getUserEntity(String username, String password) {
        return userFacade.getUser(username, password);
    }

    public UserEntity newUserEntity(int id, String username, String password, String email) {
        throw new UnsupportedOperationException();
    }
}
