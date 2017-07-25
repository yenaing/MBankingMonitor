package ua.kiev.sinenko.mbankingmonitor.controller;

/**
 * Created by a.sinenko on 10.02.2016.
 */

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

@WebFilter(filterName = "EncodingFilter", urlPatterns = {"*.xhtml"})
public class EncodingFilter implements Filter {

    public EncodingFilter() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
       chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
