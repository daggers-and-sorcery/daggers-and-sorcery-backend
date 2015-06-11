package com.swordssorcery.server.filter;

import com.swordssorcery.server.session.SessionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SessionLoginFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(SessionLoginFilter.class);

    @Override
    public void init(FilterConfig fc) throws ServletException {
        logger.info("Initialising SessionCheckerFilter");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        if (request.getSession().getAttribute(SessionType.USER) == null) {
            response.sendError(401);

            return;
        }

        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {
        logger.info("Destroying SessionCheckerFilter");
    }
}