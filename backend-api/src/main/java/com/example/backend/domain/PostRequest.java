package com.example.backend.domain;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class PostRequest {
    private Integer id;
    private String caption;
    private MultipartFile file;
}
