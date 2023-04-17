package com.example.backend.domain;

import lombok.Data;

@Data
public class CommentRequest {
    private Integer user_id;
    private Integer post_id;
    private String comment;
}
