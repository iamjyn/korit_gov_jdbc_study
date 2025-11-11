package com.korip.study3.service;

import com.korip.study3.dao.PostDao;
import com.korip.study3.dto.PostReqDto;
import com.korip.study3.entity.Post;

import java.util.List;
import java.util.Optional;

public class PostService {
    private static PostService instance;
    private PostDao postDao;

    private PostService(PostDao postDao) {
        this.postDao = postDao;
    }

    public static PostService getInstance() {
        if (instance == null) {
            instance = new PostService(PostDao.getInstance());
        }
        return instance;
    }

    // add
    public int addPost(PostReqDto postReqDto) {
        return postDao.addPost(postReqDto.toEntity());
    }

    // 제목 중복 확인
    public boolean isValidDuplicatedTitle(String title) {
        Optional<Post> foundTitle = postDao.findPostByTitle(title);
        return foundTitle.isPresent();
    }

    // id로 단건 조회
    public Post searchId(Integer id) {
        Optional<Post> foundId = postDao.findPostById(id);
        if (foundId.isEmpty()) {
            System.out.println("해당 Id는 존재하지 않습니다.");
            return null;
        }
        return foundId.get();
    }

    // username으로 게시물 여러개 조회
    public List<Post> searchByUsername(String username) {
        return postDao.getPostListByUsername(username);
    }

    // 키워드 검색 조회 => 제목 또는 내용
    public List<Post> searchPostKeyword(String keyword) {
        return postDao.getPostListByKeyword(keyword);
    }

}
