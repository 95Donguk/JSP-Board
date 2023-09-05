package edu.mzc.myboard.controller.board;

import edu.mzc.myboard.dto.BoardDTO;
import edu.mzc.myboard.service.BoardService;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Slf4j
public class BoardsReadController implements BoardController {

    @Override
    public String execute(Map<String, String> paramMap, Map<String, Object> model, HttpSession session) {
        log.info("글 목록 검색 처리");

        // 1. 사용자 입력 정보 추출
        String searchCondition = paramMap.get("searchCondition");
        String searchKeyword = paramMap.get("searchKeyword");

        // Null Check
        if (searchCondition == null) searchCondition = "TITLE";
        if (searchKeyword == null) searchKeyword = "";


        // model에 검색 관련 정보를 저장한다.
        model.put("condition", searchCondition);
        model.put("keyword", searchKeyword);

        // 2. DB 연동 처리
        BoardDTO dto = new BoardDTO();
        dto.setSearchCondition(searchCondition);
        dto.setSearchKeyword(searchKeyword);

        BoardService service = new BoardService();
        List<BoardDTO> boardList = service.readAll(dto);

        // 3. 화면 이동
        model.put("boardList", boardList);
        return VIEW_PATH + "boards";
    }
}
