package edu.mzc.myboard.dao;

import edu.mzc.myboard.common.JDBCUtil;
import edu.mzc.myboard.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
public class UserDAO {
    // UserManagement
    // 사용자를 관리하는 클래스로 인식이 될 가능성이 높음.

    // UserDAO
    // 사용자 관리(기능) + DB와 연동
    // DAO(Data Access Object) : Data Access 는 h2와 연동해서 User Table Data를 관리

    // JDBC 관련 변수
    private Connection connection = null;
    private PreparedStatement statement = null;
    private ResultSet resultSet = null;

    // USERS 테이블 관련 SQL 명령어
    private final String USER_LIST = "SELECT * FROM USERS";
    private final String USER_INSERT = "INSERT INTO users VALUES(?, ?, ?, ?)";
    private final String USER_UPDATE = "UPDATE users SET name = ?, role = ? where id = ?";
    private final String USER_DELETE = "DELETE users WHERE id = ?";
    private final String USER_GET = "SELECT * FROM users WHERE id  = ?";

    // USERS 테이블 관련 CRUD 메소드

    // 회원 상세 조회
    public UserDTO findById(UserDTO dto) {
        UserDTO user = null;

        try {
            connection = JDBCUtil.getConnection();
            statement = connection.prepareStatement(USER_GET);
            statement.setString(1, dto.getId());
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user = new UserDTO();
                user.setId(resultSet.getString("ID"));
                user.setPassword(resultSet.getString("PASSWORD"));
                user.setName(resultSet.getString("NAME"));
                user.setRole(resultSet.getString("ROLE"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(resultSet, statement, connection);
        }
        return Optional.ofNullable(user).orElseThrow(() -> new IllegalArgumentException("존재하지 않은 아이디입니다."));
    }

    // 회원 삭제
    public void deleteById(String id) {
        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(USER_DELETE)) {

            statement.setString(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            log.error("Error Code : {}", e.getErrorCode());
            log.error("Message : {}", e.getMessage());
        } finally {
            JDBCUtil.close(statement, connection);
        }
    }

    // 회원 수정
    public void updateById(UserDTO dto) {
        try {
            connection = JDBCUtil.getConnection();
            statement = connection.prepareStatement(USER_UPDATE);
            statement.setString(1, dto.getName());
            statement.setString(2, dto.getRole());
            statement.setString(3, dto.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            log.error("Error Code : {}", e.getErrorCode());
            log.error("Message : {}", e.getMessage());
        } finally {
            JDBCUtil.close(statement, connection);
        }
    }

    // 회원 등록
    public void insert(UserDTO vdto) {
        try {
            connection = JDBCUtil.getConnection();
            statement = connection.prepareStatement(USER_INSERT);
            statement.setString(1, vdto.getId());
            statement.setString(2, vdto.getPassword());
            statement.setString(3, vdto.getName());
            statement.setString(4, vdto.getRole());
            statement.executeUpdate();

        } catch (SQLException e) {
            log.error("Error Code : {}", e.getErrorCode());
            log.error("Message : {}", e.getMessage());
        } finally {
            JDBCUtil.close(statement, connection);
        }
    }


    // 회원 목록 조회
    public List<UserDTO> findAll() {
        // select 실행 후에는 ResultSet 반환됨.
        // 실행 후 여러 건의 데이터가 반환되면,
        // next() 메소드를 이용해서 다음 row로 이동.
        // true : 다음으로 읽을 데이터가 있는 경우.
        // false : 다음으로 읽을 데이터가 없는 경우.

        List<UserDTO> users = new ArrayList<>();
        try {
            connection = JDBCUtil.getConnection();
            statement = connection.prepareStatement(USER_LIST);
            resultSet = statement.executeQuery();
            log.info("[ USER 목록 ]");
            while (resultSet.next()) {
                UserDTO user = new UserDTO();
                user.setId(resultSet.getString("ID"));
                user.setPassword(resultSet.getString("PASSWORD"));
                user.setName(resultSet.getString("NAME"));
                user.setRole(resultSet.getString("ROLE"));
                users.add(user);
            }
        } catch (SQLException e) {
            log.error("Error Code : {}", e.getErrorCode());
            log.error("Message : {}", e.getMessage());
        } finally {
            JDBCUtil.close(resultSet, statement, connection);
        }
        return users;
    }

    public void findUserCount() {
        try {
            connection = JDBCUtil.getConnection();
            statement = connection.prepareStatement(USER_LIST);
            resultSet = statement.executeQuery();
            int userCount = 0;

            while (resultSet.next()) {
                userCount++;
            }

            System.out.println("회원 수 : " + userCount);
        } catch (SQLException e) {
            log.error("Error Code : {}", e.getErrorCode());
            log.error("Message : {}", e.getMessage());
        } finally {
            JDBCUtil.close(resultSet, statement, connection);
        }
    }
}
