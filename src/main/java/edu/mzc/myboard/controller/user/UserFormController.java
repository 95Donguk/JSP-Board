package edu.mzc.myboard.controller.user;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Slf4j
public class UserFormController implements UserController {

    @Override
    public String execute(Map<String, String> paramMap, Map<String, Object> model, HttpSession session) {
        log.info("회원가입 화면으로 이동");

        return VIEW_PATH + "new-form";
    }
}
