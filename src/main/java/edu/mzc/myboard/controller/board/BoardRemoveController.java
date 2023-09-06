package edu.mzc.myboard.controller.board;

import edu.mzc.myboard.dto.BoardDTO;
import edu.mzc.myboard.dto.UserDTO;
import edu.mzc.myboard.service.BoardService;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Objects;

@Slf4j
public class BoardRemoveController implements BoardController {

    @Override
    public String execute(Map<String, String> paramMap, Map<String, Object> model, HttpSession session) {
        log.info("글 삭제 처리");

        // 1. 사용자 입력 정보 추출
        String seq = paramMap.get("seq");
        String writerId = paramMap.get("writer-id");

        String userId = ((UserDTO) session.getAttribute("user")).getId();

        if (Objects.equals(writerId, userId)) {
            BoardDTO dto = new BoardDTO();
            dto.setSeq(Integer.parseInt(seq));
            // 2. DB 연동 처리
            BoardService service = new BoardService();
            service.remove(dto);

            // 3. 화면 이동
            return "/boards.do";
        }

        return "/board.do?seq="+ seq;
    }
}
