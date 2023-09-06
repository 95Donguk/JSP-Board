package edu.mzc.myboard.controller.adapter;

import edu.mzc.myboard.controller.ControllerV2;
import edu.mzc.myboard.view.ModelView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControllerV2Adapter implements MyHandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllerV2);
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        ControllerV2 controllerV2 = (ControllerV2) handler;

        String viewNames = controllerV2.execute(request, response);

        ModelView mv = new ModelView(viewNames);

        return mv;
    }
}
