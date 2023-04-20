package com.example.backend.domain;

import lombok.Data;

@Data
public class CommentRequest {
    private Integer user_id;
    private Integer postid;
    private String comment;
}