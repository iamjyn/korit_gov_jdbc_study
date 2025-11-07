package com.korip.dao;

import com.korip.entity.User;
import com.korip.util.ConnectionFactory;

import java.sql.*;

/*
* DAO (Data Access Object)
* 데이터베이스에 접근하고 데이터를 조작하는데 사용되는 객체
* 일반적으로 데이터베이스에 대한 접근을 캡슐화
* */
public class UserDao {
    private static UserDao instance;

    private UserDao() {}

    public static UserDao getInstance() {
        if (instance == null) {
            instance = new UserDao();
        }
        return instance;
    }

    // addUser(User user)
    public int addUser(User user) {
        String sql =
                "INSERT INTO user_tb(user_id, username, password, age, create_dt) VALUES (0, ?, ?, ?, NOW());";
        try (Connection con = ConnectionFactory.getConnection();
             // Statement.RETURN_GENERATED_KEYS
             // DB가 생성한 자동 증가 키를 되돌려 받겠다라는 옵션
             // 단, 실제로 키가 생성되려면 insert시 db에서 auto increment가 되도록 해야한다.
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            // Statement => SQL insection에 취약
            // PreparedStatement
            // SQL문에 있는 ?(placeholder) 자리에 자바 값을 타입별로 안전하게 채운다.
            // 이 방식은 SQL 인젝션을 방지하고, DB가 파라미터 타입을 안전하게 처리할 수 있게 돕는다.
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setInt(3, user.getAge());

            int updateInt = ps.executeUpdate(); // 쿼리 실행: 변경된 행의 갯수를 반환

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    int id = keys.getInt(1);
                    user.setUserId(id);
                }
            }
            return updateInt;
        } catch (SQLException e) {
            e.printStackTrace(); // 실무에서는 비권장(에러 메시지를 보고 악의적인 해킹이 있을 수도 있으므로)
            return 0;
        }
    }

    // findUserByUsername(String username)

    // getUserAllList()
}
