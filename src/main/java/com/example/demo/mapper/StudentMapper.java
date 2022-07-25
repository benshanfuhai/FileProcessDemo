package com.example.demo.mapper;

import com.example.demo.bean.StudentBean;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentMapper {
    void addStudent(StudentBean studentBean);
}
