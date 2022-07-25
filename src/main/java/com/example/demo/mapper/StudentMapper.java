package com.example.demo.mapper;

import com.example.demo.bean.StudentBean;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentMapper {
    void addStudent(StudentBean studentBean);

    // @Select("select s_num,s_name,chinese_score,math_score,english_score,total_score " +
    //         "from tb_student")
    List<StudentBean> queryAllStudent();
}
