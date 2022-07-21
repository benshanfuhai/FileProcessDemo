package com.example.demo.service;

import org.springframework.web.multipart.MultipartFile;

public interface StudentService {
    boolean batchImport(MultipartFile multipartFile);
}
