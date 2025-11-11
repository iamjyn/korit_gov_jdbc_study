package com.korip.study2.dto;

import com.korip.study2.entity.User;
import com.korip.study2.util.PasswordEncoder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SignupReqDto {
    private String username;
    private String password;
    private String email;

    public User toEntity() {
        return User.builder()
                .username(username)
                .password(PasswordEncoder.encode(password))
                .email(email)
                .build();
    }
}
