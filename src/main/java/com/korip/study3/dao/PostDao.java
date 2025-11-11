package com.korip.study3.dao;

import com.korip.study3.entity.Post;
import com.korip.study3.util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PostDao {
    private static PostDao instance;

    private PostDao() {}

    public static PostDao getInstance() {
        if (instance == null) {
            instance = new PostDao();
        }
        return instance;
    }

    // title 조회
    public Optional<Post> findPostByTitle(String title) {
        String sql =
                "SELECT * FROM post_tb WHERE title = ?;";
        try (
                Connection con = ConnectionFactory.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
        ) {
            ps.setString(1, title);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(toPost(rs));
                }
            }
            return Optional.empty();

        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    // id 조회
    public Optional<Post> findPostById(Integer id) {
        String sql =
                "SELECT * FROM post_tb WHERE post_id = ?;";
        try (
                Connection con = ConnectionFactory.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
        ) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(toPost(rs));
                }
            }
            return Optional.empty();

        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    // username으로 게시물 여러개 조회
    public List<Post> getPostListByUsername(String username) {
        String sql = "SELECT * FROM post_tb WHERE username = ?;";
        List<Post> postList = new ArrayList<>();
        try (
                Connection con = ConnectionFactory.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
            ){

            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()){
                while (rs.next()) {
                    postList.add(toPost(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return postList;
    }

    // 키워드 검색 조회 => 제목 또는 내용
    public List<Post> getPostListByKeyword(String Keyword) {
        String sql = "SELECT * FROM post_tb WHERE title LIKE ? or content LIKE ?;";
        List<Post> postList = new ArrayList<>();
        try (
                Connection con = ConnectionFactory.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
            ){

            ps.setString(1, "%" + Keyword + "%");
            ps.setString(2, "%" + Keyword + "%");
            try (ResultSet rs = ps.executeQuery()){
                while (rs.next()) {
                    postList.add(toPost(rs));
                }
            }
    } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return postList;
    }

    // post 추가
    public int addPost(Post post) {
        String sql =
                "INSERT INTO post_tb(post_id, title, content, username, create_dt) VALUES (0, ?, ?, ?, NOW());";
        try (
                Connection con = ConnectionFactory.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
            ){
                ps.setString(1, post.getTitle());
                ps.setString(2, post.getContent());
                ps.setString(3, post.getUsername());

                return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }












    public Post toPost(ResultSet rs) throws SQLException {
        return Post.builder()
                .postId(rs.getInt("post_id"))
                .title(rs.getString("title"))
                .content(rs.getString("content"))
                .username(rs.getString("username"))
                .createDt(rs.getTimestamp("create_dt").toLocalDateTime())
                .build();
    }






}
