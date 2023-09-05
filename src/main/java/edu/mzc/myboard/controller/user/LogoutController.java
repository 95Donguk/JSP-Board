package edu.mzc.myboard.controller.user;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Slf4j
public class LogoutController implements UserController {

    @Override
    public String execute(Map<String, String> paramMap, Map<String, Object> model, HttpSession session) {
        log.info("로그아웃 처리");

        session.invalidate();

        return "main";
    }
}
