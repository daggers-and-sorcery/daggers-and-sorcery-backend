package com.morethanheroic.swords.common.resolver;

import com.morethanheroic.swords.common.session.SessionAttributeType;
import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.user.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class UserEntityHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Autowired
    private UserManager userManager;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(UserEntity.class);
    }

    @Override
    public UserEntity resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpSession session = ((HttpServletRequest) webRequest.getNativeRequest()).getSession();

        if (session.getAttribute(SessionAttributeType.USER_ID.name()) == null) {
            return null;
        }

        return userManager.getUser((int) session.getAttribute(SessionAttributeType.USER_ID.name()));
    }
}
