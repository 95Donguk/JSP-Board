package edu.mzc.myboard.controller.board;

import edu.mzc.myboard.dto.BoardDTO;
import edu.mzc.myboard.dto.UserDTO;
import edu.mzc.myboard.service.BoardService;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Slf4j
public class BoardRegisterController implements BoardController {

    @Override
    public String execute(Map<String, String> paramMap, Map<String, Object> model, HttpSession session) {
        log.info("글 등록 처리");

        // 1. 사용자 입력 정보 추출
        String title = paramMap.get("title");
        String content = paramMap.get("content");
        String userId = ((UserDTO) session.getAttribute("user")).getId();

        log.error("title = {}", title);
        log.error("content = {}", content);

        // 2. DB 연동 처리
        BoardDTO dto = new BoardDTO();
        dto.setTitle(title);
        dto.setContent(content);
        dto.setUserId(userId);

        BoardService service = new BoardService();
        service.register(dto);

        // 3. 화면 이동
        return "/boards.do";
    }
}
