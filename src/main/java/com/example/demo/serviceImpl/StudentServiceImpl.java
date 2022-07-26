package com.example.demo.serviceImpl;

import com.example.demo.bean.StudentBean;
import com.example.demo.mapper.StudentMapper;
import com.example.demo.service.StudentService;
import com.example.demo.utils.ExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentMapper studentMapper;

    @Override
    public boolean batchImport(MultipartFile multipartFile) {
        try {
            List<StudentBean> studentBeanList = ExcelUtils.excelToStudentList(multipartFile.getInputStream());
            for (StudentBean studentBean : studentBeanList) {
                studentMapper.addStudent(studentBean);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean singleImport(StudentBean studentBean) {
        try {
            studentMapper.addStudent(studentBean);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<StudentBean> getAllStudent() {
        try {
            List<StudentBean> studentBeanList = studentMapper.queryAllStudent();
            return studentBeanList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
