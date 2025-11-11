package com.korip.study3.dto;

import com.korip.study3.entity.Post;
import lombok.Data;

@Data
public class PostReqDto {
    private String title;
    private String content;
    private String username;

    public Post toEntity() {
        return Post.builder()
                .title(title)
                .content(content)
                .username(username)
                .build();
    }
}
