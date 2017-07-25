package com.morethanheroic.swords.witchhuntersguild.view.filter;

import com.morethanheroic.swords.session.SessionAttributeType;
import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.user.service.UserEntityFactory;
import com.morethanheroic.swords.witchhuntersguild.service.WitchhuntersGuildAccessibilityCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This filter is responsible for denying requests for the Witchhunter's guild pages when the user can't access them.
 */
@Component
@RequiredArgsConstructor
public class WitchhuntersGuildAccessibilityFilter implements Filter {

    private final WitchhuntersGuildAccessibilityCalculator witchhuntersGuildAccessibilityCalculator;
    private final UserEntityFactory userEntityFactory;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        if (httpServletRequest.getSession().getAttribute(SessionAttributeType.USER_ID.name()) != null) {
            final UserEntity userEntity = userEntityFactory.getEntity((int) httpServletRequest.getSession().getAttribute(SessionAttributeType.USER_ID.name()));

            if (!witchhuntersGuildAccessibilityCalculator.hasAccess(userEntity)) {
                ((HttpServletResponse) response).sendError(HttpStatus.CONFLICT.value(), "The player tried to access the Witchhunter's Guild while he has no rights to access it.");
            } else {
                chain.doFilter(request, response);
            }
        }
    }

    @Override
    public void destroy() {

    }
}
