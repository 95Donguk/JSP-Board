package edu.mzc.myboard.controller;

import lombok.Setter;

@Setter
public class ViewResolver {
    public String prefix;
    public String suffix;

    public String getView(String viewName) {
        String view = null;
        if (!viewName.contains(".do")) {
            if (viewName.equals("main")) {
                view = "/" + viewName + ".jsp";
            } else {
                view = prefix + viewName + suffix;
            }
        } else {
            view = viewName;
        }
        return view;
    }
}
