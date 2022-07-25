package com.example.demo.service;

import com.example.demo.bean.StudentBean;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface StudentService {
    boolean batchImport(MultipartFile multipartFile);

    boolean singleImport(StudentBean studentBean);

    List<StudentBean> getAllStudent();
}
