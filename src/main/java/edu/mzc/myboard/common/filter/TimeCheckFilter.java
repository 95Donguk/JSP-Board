package edu.mzc.myboard.common.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
@WebFilter(filterName = "timeCheck", urlPatterns = "*.do")
public class TimeCheckFilter extends HttpFilter implements Filter {
    private static final long serialVersionUID = 1L;

    public TimeCheckFilter() {
        log.debug("===> timeCheckFilter 생성");
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        log.debug("---> init() 호출");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        String uri = request.getRequestURI();
        String path = uri.substring(uri.lastIndexOf("/"));

        long startTime = System.currentTimeMillis();

        chain.doFilter(req, res);

        long endTime = System.currentTimeMillis();
        log.debug("{} 요청 처리에 소요된 시간 : {} (ms)초", path, (endTime - startTime));
    }

    public void destroy() {
        log.debug("---> destory() 호출");
    }
}
