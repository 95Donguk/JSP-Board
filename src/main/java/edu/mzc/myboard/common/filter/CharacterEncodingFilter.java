package edu.mzc.myboard.common.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpFilter;
import java.io.IOException;

@WebFilter(urlPatterns = "*.do",
        initParams = @WebInitParam(name = "boardEncoding", value = "UTF-8"))
public class CharacterEncodingFilter extends HttpFilter implements Filter {
    private static final long serialVersionUID = 1L;
    private String encoding;

    @Override
    public void init(FilterConfig config) throws ServletException {
        encoding = config.getInitParameter("boardEncoding");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        // 서블릿이 수행되기 전에 인코딩을 처리한다.
//        ServletContext context = req.getServletContext();
//        String encoding = context.getInitParameter("boardEncoding");
        req.setCharacterEncoding(encoding);

        chain.doFilter(req, res);
    }
}
