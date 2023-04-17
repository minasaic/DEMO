package com.example.backend.domain;
import lombok.*;
@Data
public class LoginRequest {
    private String username;
    private String password;
    private String profile_picture;
}
