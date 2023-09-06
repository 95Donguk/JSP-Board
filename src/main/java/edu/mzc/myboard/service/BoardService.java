package edu.mzc.myboard.service;

import edu.mzc.myboard.dao.BoardDAO;
import edu.mzc.myboard.domain.board.Page;
import edu.mzc.myboard.dto.BoardDTO;

import java.util.List;

public class BoardService {

    private final BoardDAO boardDAO = new BoardDAO();

    public void edit(BoardDTO dto) {
        boardDAO.update(dto);
    }

    public BoardDTO read(BoardDTO dto) {
        return boardDAO.findBySeq(dto);

    }

    public void remove(BoardDTO dto) {
        boardDAO.deleteBySeq(dto);
    }

    public void register(BoardDTO dto) {
        boardDAO.insert(dto);
    }

    public Page<BoardDTO> readAll(BoardDTO dto, int page) {

        Page<BoardDTO> boardDTOPage = new Page<>(page, boardDAO.findBoardCount());

        List<BoardDTO> boards = boardDAO.findAll(dto, boardDTOPage.getSqlLimitStart(), boardDTOPage.getSqlLimitEnd());
        boardDTOPage.setBoards(boards);
        return boardDTOPage;
    }
}
