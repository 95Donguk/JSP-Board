package edu.mzc.myboard.controller.board;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Slf4j
public class BoardFormController implements BoardController {

    @Override
    public String execute(Map<String, String> paramMap, Map<String, Object> model, HttpSession session) {
        log.info("글 등록 화면으로 이동");

        return VIEW_PATH + "new-form";
    }
}
