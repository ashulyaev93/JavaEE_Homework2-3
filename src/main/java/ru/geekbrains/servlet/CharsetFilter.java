package ru.geekbrains.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter (urlPatterns = "/*" )
public class CharsetFilter implements Filter {
    private String encoding;

    public void init(FilterConfig config) throws ServletException {
        // читаем из конфигурации;
        encoding = config.getInitParameter("requestEncoding");

        // если не установлена — устанавливаем UTF-8
        if (encoding == null) encoding = "UTF-8";
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletResponse.setContentType("text/html; charset=UTF-8");
        servletRequest.setCharacterEncoding(encoding);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    public void destroy() {
    }
}
