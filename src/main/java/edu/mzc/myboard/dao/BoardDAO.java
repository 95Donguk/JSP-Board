package edu.mzc.myboard.dao;

import edu.mzc.myboard.common.JDBCUtil;
import edu.mzc.myboard.dto.BoardDTO;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// DAO(Data Access Object) 클래스
@Slf4j
public class BoardDAO {
    // SQL 명령어
    private static final String BOARD_INSERT =
            "insert into board(title, content, user_id) " +
            "values (?, ?, ?)";
    private static final String BOARD_UPDATE =
            "UPDATE BOARD SET TITLE=?, CONTENT=? WHERE SEQ=?";
    private static final String BOARD_DELETE =
            "DELETE FROM BOARD WHERE SEQ=?";
    private static final String BOARD_GET =
            "SELECT SEQ, TITLE, CONTENT, REGDATE, CNT, USER_ID, USERS.NAME AS WRITER " +
                    "FROM BOARD INNER JOIN USERS " +
                    "ON USER_ID = ID " +
                    "WHERE SEQ=?";
    private static final String BOARD_LIST = "SELECT * FROM BOARD ORDER BY SEQ DESC";
    // 검색 관련 쿼리
    private static final String BOARD_LIST_T =
            "SELECT SEQ, TITLE, CONTENT, REGDATE, CNT, USERS.NAME AS WRITER " +
                    "FROM BOARD INNER JOIN USERS " +
                    "ON USER_ID = ID " +
                    "WHERE TITLE LIKE ? " +
                    "ORDER BY SEQ DESC LIMIT ?, ?";
    private static final String BOARD_LIST_C =
            "SELECT SEQ, TITLE, CONTENT, REGDATE, CNT, USERS.NAME AS WRITER " +
                    "FROM BOARD INNER JOIN USERS " +
                    "ON USER_ID = ID " +
                    "WHERE CONTENT LIKE ? " +
                    "ORDER BY SEQ DESC LIMIT ?, ?";
    private static final String BOARD_INCREASE_VIEW_COUNT =
            "UPDATE BOARD SET CNT = CNT + 1 WHERE SEQ=?";
    private static final String BOARD_COUNT =
            "SELECT COUNT(*) AS BOARD_COUNT FROM BOARD";

    // JDBC 관련 변수
    private Connection connection;
    private PreparedStatement statement;
    private ResultSet resultSet;


    // CRUD 기능의 메소드
    // 글 등록
    public void insert(BoardDTO dto) {
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(BOARD_INSERT)) {
            stmt.setString(1, dto.getTitle());
            stmt.setString(2, dto.getContent());
            stmt.setString(3, dto.getUserId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
    }

    // 글 수정
    public void update(BoardDTO dto) {
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(BOARD_UPDATE)) {
            stmt.setString(1, dto.getTitle());
            stmt.setString(2, dto.getContent());
            stmt.setInt(3, dto.getSeq());
            stmt.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
    }

    // 글 삭제
    public void deleteBySeq(BoardDTO dto) {
        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(BOARD_DELETE)) {

            statement.setInt(1, dto.getSeq());
            statement.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
    }

    // 글 상세 조회
    public BoardDTO findBySeq(BoardDTO dto) {
        BoardDTO result = null;
        try {
            connection = JDBCUtil.getConnection();
            connection.setAutoCommit(false);

            statement = connection.prepareStatement(BOARD_INCREASE_VIEW_COUNT);
            statement.setInt(1, dto.getSeq());
            statement.executeUpdate();
            statement.close();

            statement = connection.prepareStatement(BOARD_GET);
            statement.setInt(1, dto.getSeq());
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                result = new BoardDTO();
                result.setSeq(resultSet.getInt("SEQ"));
                result.setTitle(resultSet.getString("TITLE"));
                result.setWriter(resultSet.getString("WRITER"));
                result.setContent(resultSet.getString("CONTENT"));
                result.setRegDate(resultSet.getDate("REGDATE"));
                result.setCnt(resultSet.getInt("CNT"));
                result.setUserId(resultSet.getString("USER_ID"));
            }
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                log.error(e.getMessage());
            }
            log.error(e.getMessage());
        } finally {
            JDBCUtil.close(resultSet, statement, connection);
        }
        return result;
    }

    // 글 목록 검색
    public List<BoardDTO> findAll(BoardDTO dto, int sqlLimitStart, int sqlLimitEnd) {
        ArrayList<BoardDTO> boards = new ArrayList<>();
        try {
            connection = JDBCUtil.getConnection();
            if (dto.getSearchCondition().equals("TITLE")) {
                statement = connection.prepareStatement(BOARD_LIST_T);
            } else if (dto.getSearchCondition().equals("CONTENT")) {
                statement = connection.prepareStatement(BOARD_LIST_C);
            }
            statement.setString(1, "%" + dto.getSearchKeyword() + "%");
            statement.setInt(2, sqlLimitStart);
            statement.setInt(3, sqlLimitEnd);

            System.out.println(statement.toString());
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                BoardDTO board = new BoardDTO();
                board.setSeq(resultSet.getInt("SEQ"));
                board.setTitle(resultSet.getString("TITLE"));
                board.setWriter(resultSet.getString("WRITER"));
                board.setContent(resultSet.getString("CONTENT"));
                board.setRegDate(resultSet.getDate("REGDATE"));
                board.setCnt(resultSet.getInt("CNT"));
                boards.add(board);
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return boards;
    }

    public int findBoardCount() {
        int boardCount = 0;
        try {
            connection = JDBCUtil.getConnection();
            statement = connection.prepareStatement(BOARD_COUNT);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                boardCount = resultSet.getInt("BOARD_COUNT");
            }

        } catch (SQLException e) {
            log.error("Error Code : {}", e.getErrorCode());
            log.error("Message : {}", e.getMessage());
        } finally {
            JDBCUtil.close(resultSet, statement, connection);
        }
        return boardCount;
    }
}
