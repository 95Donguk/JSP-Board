package edu.mzc.myboard.controller.board;

import edu.mzc.myboard.dto.BoardDTO;
import edu.mzc.myboard.service.BoardService;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Slf4j
public class BoardReadController implements BoardController {

    @Override
    public String execute(Map<String, String> paramMap, Map<String, Object> model, HttpSession session) {
        log.info("글 상세 조회 처리");

        // 1. 사용자 입력 정보 추출
        String seq = paramMap.get("seq");

        // 2. DB 연동 처리
        BoardDTO dto = new BoardDTO();
        dto.setSeq(Integer.parseInt(seq));

        BoardService service = new BoardService();
        BoardDTO board = service.read(dto);

        // 3. 화면 이동
        model.put("board", board);
        return VIEW_PATH + "board";
    }
}
