package edu.mzc.myboard.controller;

import edu.mzc.myboard.controller.board.*;
import edu.mzc.myboard.controller.user.*;

import java.util.HashMap;
import java.util.Map;

public class HandlerMapping {

    private static HandlerMapping handlerMapping = null;
    // Controller를 구현한 객체들을 저장하는 Map
    private final Map<String, Controller> mappings;

    private HandlerMapping() {
        // Key-Value 형태로 수 많은 Controller를 등록한다.
        mappings = new HashMap<>();
        mappings.put("/user/new-form.do", new UserFormController());
        mappings.put("/user/register.do", new UserRegisterController());
        mappings.put("/login/form.do", new LoginFormController());
        mappings.put("/login.do", new LoginController());
        mappings.put("/boards.do", new BoardsReadController());
        mappings.put("/board.do", new BoardReadController());
        mappings.put("/board/new-form.do", new BoardFormController());
        mappings.put("/board/register.do", new BoardRegisterController());
        mappings.put("/board/edit.do", new BoardEditController());
        mappings.put("/board/remove.do", new BoardRemoveController());
        mappings.put("/logout.do", new LogoutController());
    }

    public static HandlerMapping getInstance() {
        if (handlerMapping == null) {
            handlerMapping = new HandlerMapping();
        }
        return handlerMapping;
    }

    public Controller getHandler(String path) {
        // Map에 등록된 Controller 중에서
        // 특정 경로(path)에 해당하는 Controller를 리턴한다.
        return mappings.get(path);
    }
}
