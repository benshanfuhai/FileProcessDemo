package com.example.demo.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.bean.StudentBean;
import com.example.demo.service.StudentService;
import com.mysql.cj.xdevapi.JsonArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@Controller
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/uploadToDB")
    @ResponseBody
    public boolean batchImportStudent(@RequestParam("file") MultipartFile multipartFile) {
        return studentService.batchImport(multipartFile);
    }

    @PostMapping("/inputToDB")
    @ResponseBody
    public boolean singleImportStudent(@RequestParam("studentString") String studentString) {
        System.out.println("studentInfo: " + studentString);
        StudentBean studentBean = JSONArray.toJavaObject((JSONObject) JSONArray.parse(studentString), StudentBean.class);
        return studentService.singleImport(studentBean);
    }
}
