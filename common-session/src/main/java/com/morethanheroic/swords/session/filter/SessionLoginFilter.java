package com.morethanheroic.swords.session.filter;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpStatus;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * A filter used to allow access only authenticated users to specific pages.
 */
@Log4j
@RequiredArgsConstructor
public class SessionLoginFilter implements Filter {

    @NonNull
    private final String sessionAttributeName;

    @Override
    public void init(FilterConfig fc) throws ServletException {
        log.info("Initialising session login filter.");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws ServletException, IOException {
        final HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        final HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        if (!httpServletRequest.getMethod().equalsIgnoreCase("OPTIONS") && httpServletRequest.getSession().getAttribute(sessionAttributeName) == null) {
            httpServletResponse.sendError(HttpStatus.UNAUTHORIZED.value());

            return;
        }

        chain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        log.info("Destroying session login filter.");
    }
}
