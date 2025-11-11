package com.korip.study2.dao;

import com.korip.study2.dto.GetUserListRespDto;
import com.korip.study2.entity.User;
import com.korip.study2.util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class UserDao {
    private static UserDao instance;

    private UserDao() {}

    public static UserDao getInstance() {
        if (instance == null) {
            instance = new UserDao();
        }
        return instance;
    }

    // username 조회
    public Optional<User> findUserByUsername(String username) {
        String sql =
                "SELECT * FROM user2_tb WHERE username = ?;";
        try (
                Connection con = ConnectionFactory.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
        ) {
            ps.setString(1, username);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(toUser(rs));
                }
            }
            return Optional.empty();

        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    // email 조회
    public Optional<User> findUserByEmail(String email) {
        String sql =
                "SELECT * FROM user2_tb WHERE email = ?;";
        try (
                Connection con = ConnectionFactory.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
        ) {
            ps.setString(1, email);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(toUser(rs));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
        return Optional.empty();
    }

    // user 추가
    public int addUser(User user) {
        String sql =
                "INSERT INTO user2_tb(user_id, username, password, email, create_dt) VALUES (0, ?, ?, ?, NOW());";
        try (
                Connection con = ConnectionFactory.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
        ){
                ps.setString(1, user.getUsername());
                ps.setString(2, user.getPassword());
                ps.setString(3, user.getEmail());

                return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    // userList 전체 조회
    public List<GetUserListRespDto> getUserAllList() {
        String sql = "SELECT user_id, username, email, create_dt FROM user2_tb;";
        List<GetUserListRespDto> userList = new ArrayList<>();
        try (
                Connection con = ConnectionFactory.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
        ){
            while (rs.next()) {
                userList.add(toGetUserListRespDto(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    // user - username으로 조회
    public List<GetUserListRespDto> getUserListByUsername(String username) {
        String sql = "SELECT user_id, username, email, create_dt FROM user2_tb WHERE username LIKE ?;";
        List<GetUserListRespDto> userList = new ArrayList<>();
        try (
                Connection con = ConnectionFactory.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
        ){
                ps.setString(1, "%" + username + "%");
                try (ResultSet rs = ps.executeQuery()){
                    while (rs.next()) {
                        userList.add(toGetUserListRespDto(rs));
                    }
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public User toUser(ResultSet rs) throws SQLException {
        return User.builder()
                .userId(rs.getInt("user_id"))
                .username(rs.getString("username"))
                .email(rs.getString("email"))
                .createDt(rs.getTimestamp("create_dt").toLocalDateTime())
                .build();
    }

    public GetUserListRespDto toGetUserListRespDto(ResultSet rs) throws SQLException {
        return GetUserListRespDto.builder()
                .userId(rs.getInt("user_id"))
                .username(rs.getString("username"))
                .email(rs.getString("email"))
                .createDt(rs.getTimestamp("create_dt").toLocalDateTime())
                .build();
    }
}
