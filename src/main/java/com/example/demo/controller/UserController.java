package com.example.demo.controller;

import com.example.demo.bean.StudentBean;
import com.example.demo.service.UserService;
import com.example.demo.utils.ExcelUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/hello")
    // @ResponseBody
    public String hello() {
        return "manage";
    }

    @PostMapping("/login")
    @ResponseBody
    public boolean login(String username, String password) {
        return userService.verifyUserByUsername(username, password);
    }
}
