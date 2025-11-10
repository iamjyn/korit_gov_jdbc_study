package com.korip.study2.dto;

import com.korip.study2.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class SigninReqDto {
    private String username;
    private String password;

    public User toEntity() {
        return User.builder()
                .username(username)
                .password(password)
                .build();
    }
}
