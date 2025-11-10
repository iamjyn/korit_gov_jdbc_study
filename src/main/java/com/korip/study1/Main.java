package com.korip.study1;

import com.korip.study1.dao.UserDao;
import com.korip.study1.entity.User;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserDao userDao = UserDao.getInstance();

        // insert
//        User user = User.builder()
//                .username("honggildong")
//                .password("123456")
//                .age(22)
//                .build();
//        int count = userDao.addUser(user);
//
//        System.out.println("추가된 행 갯수: " + count);
//        System.out.println("추가된 유저 정보: " + user);

        // 단건 조회
//        User foundUser = userDao.findUserByUsername("홍길동");
//        System.out.println("foundUser = " + foundUser);
//
//        List<User> users = userDao.getUserAllList();
//        users.forEach(System.out::println);

        // username 키워드 검색
        List<User> foundKeyword = userDao.getUserListByKeyword("길");
        foundKeyword.forEach(System.out::println);
    }
}