package com.morethanheroic.swords.session.filter;

import com.morethanheroic.swords.common.session.SessionAttributeType;
import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpStatus;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//TODO: Add the SessionAttributeType.USER_ID at creation time so we can separate common sessions from the sessions related to swords.
//TODO: finish refactoring of this @ home...
@Log4j
public class SessionLoginFilter implements Filter {

    @Override
    public void init(FilterConfig fc) throws ServletException {
        log.info("Initialising SessionCheckerFilter");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        if (request.getSession().getAttribute(SessionAttributeType.USER_ID.name()) == null) {
            response.sendError(HttpStatus.UNAUTHORIZED.value());

            return;
        }

        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {
        logger.info("Destroying SessionCheckerFilter");
    }
}