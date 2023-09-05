package edu.mzc.myboard.controller;

import javax.servlet.http.HttpSession;
import java.util.Map;

public interface Controller {
    String execute(Map<String, String> paramMap, Map<String, Object> model, HttpSession session);
}
