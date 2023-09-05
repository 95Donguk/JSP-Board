package edu.mzc.myboard.controller.adapter;

import edu.mzc.myboard.controller.Controller;
import edu.mzc.myboard.view.ModelView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerAdapter implements MyHandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        return (handler instanceof Controller);
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        Controller controller = (Controller) handler;

        Map<String, String> paramMap = createParamMap(request);
        HashMap<String, Object> model = new HashMap<>();

        String viewNames = controller.execute(paramMap, model, request.getSession());

        ModelView mv = new ModelView(viewNames);
        mv.setModel(model);

        return mv;
    }

    private static Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterMap()
                .forEach((key, value) -> paramMap.put(key, request.getParameter(key)));
        return paramMap;
    }
}
