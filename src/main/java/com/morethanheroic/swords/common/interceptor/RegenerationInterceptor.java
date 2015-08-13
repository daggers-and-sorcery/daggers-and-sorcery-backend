package com.morethanheroic.swords.common.interceptor;

import com.morethanheroic.swords.common.session.SessionAttributeType;
import com.morethanheroic.swords.regeneration.service.RegenerationManager;
import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.user.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegenerationInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private UserManager userManager;

    @Autowired
    private RegenerationManager regenerationManager;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getSession().getAttribute(SessionAttributeType.USER_ID) != null) {
            UserEntity user = userManager.getUser((int) request.getSession().getAttribute(SessionAttributeType.USER_ID));

            if (user != null) {
                regenerationManager.regenerate(user);
            }
        }

        return true;
    }
}
