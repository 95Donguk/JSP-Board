package edu.mzc.myboard.controller.user;

import edu.mzc.myboard.dto.UserDTO;
import edu.mzc.myboard.service.UserService;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Slf4j
public class UserRegisterController implements UserController {

    @Override
    public String execute(Map<String, String> paramMap, Map<String, Object> model, HttpSession session) {
        log.info("회원가입 처리");

        // 1. 사용자 입력 정보 추출
        String id = paramMap.get("id");
        String password = paramMap.get("password");
        String name = paramMap.get("name");
        String role = paramMap.get("role");

        // 2. DB 연동 처리
        UserDTO dto = new UserDTO();
        dto.setId(id);
        dto.setPassword(password);
        dto.setName(name);
        dto.setRole(role);

        UserService service = new UserService();
        service.signUp(dto);

        // 3. 화면 이동
        return "main";
    }
}
