package com.example.backend.domain;

import lombok.Data;

@Data
public class FollowRequest {
    private Integer follower_id;
    private Integer following_id;
}