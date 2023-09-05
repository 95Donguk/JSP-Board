package edu.mzc.myboard.controller.user;

import edu.mzc.myboard.dto.UserDTO;
import edu.mzc.myboard.service.UserService;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Objects;

@Slf4j
public class LoginController implements UserController {

    @Override
    public String execute(Map<String, String> paramMap, Map<String, Object> model, HttpSession session) {
        log.info("로그인 처리");

        // 1. 사용자 입력 정보 추출
        String id = paramMap.get("id");
        String password = paramMap.get("password");

        UserDTO dto = new UserDTO();
        dto.setId(id);

        // 2. DB 연동 처리
        UserService userService = new UserService();
        UserDTO user = userService.signIn(dto);

        // 3. 화면 이동
        if (user != null && Objects.equals(user.getPassword(), password)) {
            // 상태 정보를 세션에 저장한다.
            session.setAttribute("user", user);

            // 글 목록 화면으로 이동한다
            return "/boards.do";
        } else {
            return "/login/form.do";
        }
    }
}
