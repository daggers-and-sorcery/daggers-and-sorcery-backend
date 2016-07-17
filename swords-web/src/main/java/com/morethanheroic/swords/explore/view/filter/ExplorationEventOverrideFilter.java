package com.morethanheroic.swords.explore.view.filter;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Log4j
@RequiredArgsConstructor
public class ExplorationEventOverrideFilter implements Filter {

    private static final String EXPLORATION_EVENT_OVERRIDE_SESSION_ENTRY_KEY = "EXPLORATION_EVENT_OVERRIDE";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("Initialising exploration event override filter.");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        final Cookie[] cookies = httpServletRequest.getCookies();
        boolean sessionAdded = false;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (EXPLORATION_EVENT_OVERRIDE_SESSION_ENTRY_KEY.equals(cookie.getName()) && StringUtils.isNumeric(cookie.getValue())) {
                    httpServletRequest.getSession().setAttribute(EXPLORATION_EVENT_OVERRIDE_SESSION_ENTRY_KEY, Integer.valueOf(cookie.getValue()));
                    sessionAdded = true;
                }
            }
        }

        if (!sessionAdded) {
            httpServletRequest.getSession().removeAttribute(EXPLORATION_EVENT_OVERRIDE_SESSION_ENTRY_KEY);
        }

        chain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        log.info("Destroying exploration event override filter.");
    }
}
