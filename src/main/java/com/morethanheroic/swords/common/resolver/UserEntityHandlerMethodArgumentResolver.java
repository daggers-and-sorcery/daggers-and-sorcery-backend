package com.morethanheroic.swords.common.resolver;

import com.morethanheroic.swords.user.service.UserManager;
import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.common.session.SessionAttributeType;
import org.apache.catalina.session.StandardSessionFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class UserEntityHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Autowired
    private UserManager userManager;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(UserEntity.class);
    }

    @Override
    public UserEntity resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        return userManager.getUser((String) ((StandardSessionFacade) webRequest.getSessionMutex()).getAttribute(SessionAttributeType.USER_ID));
    }
}
