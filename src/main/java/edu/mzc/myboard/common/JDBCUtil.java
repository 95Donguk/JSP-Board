package edu.mzc.myboard.common;

import java.sql.*;

public class JDBCUtil {
    static {
        // JDBC 1단계: 드라이버 객체 로딩
        // H2 드라이버 등록
        // 직접 초기화 하지 않는 것은 응용시스템과 Driver 구현체가 강결합하지 않도록 하기 위함
        // Driver 로드는 전체 프로젝트에서 한 번만 실행해야 합니다. (대부분의 드라이버 로드는 한 번만 실행하도록 방어코드가 포함되어 있습니다.)
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {

            // JDBC 2단계: 커넥션 연결
            // session 연결을 위한 connection 연결
            // jdbc:h2, jdbc를 이용해서 h2에 접속
            // tcp: ip, port 정보를 이용해서 접속
            // 사용자, pw
            String jdbcUrl = "jdbc:mysql://localhost/test";
            connection = DriverManager.getConnection(jdbcUrl, "root", "1234");
        } catch (SQLException e) {
            e.getErrorCode();
            e.getMessage();
        }

        return connection;
    }

    // 자원 해제( RecordSet이 있는 경우. select sql 문장용)
    public static void close(PreparedStatement statement, Connection connection) {
        // JDBC 5단계 : 연결 해제
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.getErrorCode();
                e.getMessage();
            }
        }

        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.getErrorCode();
                e.getMessage();
            }
        }
    }

    // 자원 해제( RecordSet 이 없는 경우. insert, update, delete 문장용 )
    public static void close(ResultSet resultSet, PreparedStatement statement, Connection connection) {
        // JDBC 5단계 : 연결 해제
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.getErrorCode();
                e.getMessage();
            }
        }


        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.getErrorCode();
                e.getMessage();
            }
        }

        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.getErrorCode();
                e.getMessage();
            }
        }
    }
}
