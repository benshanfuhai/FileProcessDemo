package com.example.demo.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.bean.StudentBean;
import com.example.demo.service.StudentService;
import com.example.demo.utils.ExcelUtils;
import com.mysql.cj.xdevapi.JsonArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@Controller
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/file/upload")
    @ResponseBody
    public List<StudentBean> parseExcel(@RequestParam("file") MultipartFile multipartFile, Model model) throws
            Exception {
        String fileName = multipartFile.getOriginalFilename();
        // System.out.println(fileName);
        if (!fileName.substring(fileName.length() - 5).equals(".xlsx")) {
            model.addAttribute("error", "文件解析错误");
            return null;
        }
        List<StudentBean> studentBeanList = ExcelUtils.excelToStudentList(multipartFile.getInputStream());
        model.addAttribute("msg", studentBeanList);
        return studentBeanList;
    }

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

    @GetMapping("/download/students")
    @ResponseBody
    public void dbToFile(HttpServletResponse httpServletResponse) {
        ExcelUtils.exportToExcel(studentService.getAllStudent(), httpServletResponse);
    }
}
