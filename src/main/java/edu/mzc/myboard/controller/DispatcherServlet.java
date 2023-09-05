package edu.mzc.myboard.controller;

import edu.mzc.myboard.controller.adapter.ControllerAdapter;
import edu.mzc.myboard.controller.adapter.MyHandlerAdapter;
import edu.mzc.myboard.view.ModelView;
import edu.mzc.myboard.view.MyView;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@WebServlet(urlPatterns = "*.do")
public class DispatcherServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();

    private HandlerMapping handlerMapping;
    private ViewResolver viewResolver;

    @Override
    public void init() throws ServletException {
        handlerMapping = HandlerMapping.getInstance();
        initViewResolver();
        initHandlerAdapters();
    }

    private void initHandlerAdapters() {
        handlerAdapters.add(new ControllerAdapter());
    }

    private void initViewResolver() {
        viewResolver = new ViewResolver();
        viewResolver.setPrefix("/WEB-INF/");
        viewResolver.setSuffix(".jsp");
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object handler = getHandler(request);

        if (handler == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        MyHandlerAdapter adapter = getHandlerAdapter(handler);

        // 3. 검색된 Handler를 실행한다.
        ModelView mv = adapter.handle(request, response, handler);

        String viewName = mv.getViewName();

        // 4. ViewResolver 를 통해 viewName 에 해당하는 경로를 완성한다.
        String view = viewResolver.getView(viewName);

        MyView myView = new MyView(view);

        // 검색된 화면으로 포워딩한다.
        myView.render(mv.getModel(), request, response);
    }

    private MyHandlerAdapter getHandlerAdapter(Object handler) {
        for (MyHandlerAdapter adapter : handlerAdapters) {
            if (adapter.supports(handler)) {
                return adapter;
            }
        }
        throw new IllegalArgumentException("handler adapter 를 찾을 수 없습니다. handler=" + handler);
    }

    private Object getHandler(HttpServletRequest request) {
        // 1. 사용자 요청 path를 추출한다.
        String uri = request.getRequestURI();

        // 2. HandlerMapping을 통해 path에 해당하는 Controller를 검색한다.
        return handlerMapping.getHandler(uri);
    }
}
