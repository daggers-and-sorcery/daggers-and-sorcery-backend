package com.morethanheroic.swords.common.interceptor;

import com.morethanheroic.swords.session.SessionAttributeType;
import com.morethanheroic.swords.regeneration.service.RegenerationFacade;
import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.user.service.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegenerationInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private UserFacade userFacade;

    @Autowired
    private RegenerationFacade regenerationFacade;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getSession().getAttribute(SessionAttributeType.USER_ID.name()) != null) {
            UserEntity user = userFacade.getUser((int) request.getSession().getAttribute(SessionAttributeType.USER_ID.name()));

            if (user != null) {
                regenerationFacade.regenerate(user);
            }
        }

        return true;
    }
}
