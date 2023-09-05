package edu.mzc.myboard.common.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/boards.do", "/board.do", "/board/*"})
public class AuthenticationFilter extends HttpFilter implements Filter {
    private static final long serialVersionUID = 1L;

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        // 세션에 userId 정보가 등록되어 있는지 확인한다.
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            response.sendRedirect("/");
        } else {
            chain.doFilter(request, response);
        }
    }
}
