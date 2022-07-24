package com.example.demo.controller;

import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/uploadToDB")
    @ResponseBody
    public boolean importUser(@RequestParam("file") MultipartFile multipartFile) {
        return studentService.batchImport(multipartFile);
    }

}
