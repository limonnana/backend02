package com.limonnana.backend02.security;


import com.limonnana.backend02.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class CustomURLFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomURLFilter.class);

    @Autowired
    Utils utils;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if(utils.checkToken(request)){
            filterChain.doFilter(request, response);
        }else{
            LOGGER.info("Is secure NOT: " + request.getRemoteAddr());
           // response.sendRedirect("/notAuthorized");
        }


    }

    @Override
    public void destroy() {

    }
}
