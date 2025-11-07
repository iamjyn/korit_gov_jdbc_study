package com.korip;

import com.korip.dao.UserDao;
import com.korip.entity.User;

public class Main {
    public static void main(String[] args) {
        UserDao userDao = UserDao.getInstance();

        // insert
        User user = User.builder()
                .username("honggildong")
                .password("123456")
                .age(22)
                .build();
        int count = userDao.addUser(user);

        System.out.println("추가된 행 갯수: " + count);
        System.out.println("추가된 유저 정보: " + user);
    }
}