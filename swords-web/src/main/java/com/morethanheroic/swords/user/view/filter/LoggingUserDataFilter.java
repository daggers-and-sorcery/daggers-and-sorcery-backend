package com.morethanheroic.swords.user.view.filter;

import com.morethanheroic.swords.session.SessionAttributeType;
import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.user.service.UserEntityFactory;
import lombok.RequiredArgsConstructor;
import org.jboss.logging.MDC;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class LoggingUserDataFilter implements Filter {

    private final UserEntityFactory userEntityFactory;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        if (httpServletRequest.getSession().getAttribute(SessionAttributeType.USER_ID.name()) != null) {
            final UserEntity userEntity = userEntityFactory.getEntity((int) httpServletRequest.getSession().getAttribute(SessionAttributeType.USER_ID.name()));

            MDC.put("requestid", UUID.randomUUID());
            MDC.put("username", userEntity.getUsername());
            MDC.put("userid", userEntity.getId());
        }

        chain.doFilter(request, response);

        MDC.clear();
    }

    @Override
    public void destroy() {

    }
}
