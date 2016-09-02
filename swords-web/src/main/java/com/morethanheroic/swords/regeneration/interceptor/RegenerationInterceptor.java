package com.morethanheroic.swords.regeneration.interceptor;

import com.morethanheroic.swords.regeneration.service.RegenerationFacade;
import com.morethanheroic.swords.session.SessionAttributeType;
import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.user.service.UserEntityFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegenerationInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private UserEntityFactory userEntityFactory;

    @Autowired
    private RegenerationFacade regenerationFacade;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getSession().getAttribute(SessionAttributeType.USER_ID.name()) != null) {
            final UserEntity user = userEntityFactory.getEntity((int) request.getSession().getAttribute(SessionAttributeType.USER_ID.name()));

            if (user != null) {
                regenerationFacade.regenerate(user);
            }
        }

        return true;
    }
}
