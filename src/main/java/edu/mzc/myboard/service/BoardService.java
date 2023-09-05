package edu.mzc.myboard.service;

import edu.mzc.myboard.dao.BoardDAO;
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

    public void save(BoardDTO dto) {
        boardDAO.insert(dto);
    }

    public List<BoardDTO> readAll(BoardDTO dto) {
        return boardDAO.findAll(dto);
    }
}
