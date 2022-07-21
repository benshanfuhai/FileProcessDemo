package com.example.demo.utils;

import com.example.demo.bean.StudentBean;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class ExcelUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelUtils.class);

    public static List<StudentBean> excelToShopIdList(InputStream inputStream) {
        List<StudentBean> studentBeanList = new ArrayList<>();
        Workbook workbook;
        try {
            // 根据传递过来的文件输入流创建一个wordbook对象（对应Excel中的工作簿）
            workbook = WorkbookFactory.create(inputStream);
            // 创建完成，关闭工作流
            inputStream.close();
            // 获取工作表对象，即第一个工作表（工作簿里面有很多张工作表，这里取第一张工作表）
            Sheet sheet = workbook.getSheetAt(0);
            // 获取工作表的总行数
            int rowLength = sheet.getLastRowNum() + 1;
            // 获取工作表第一行数据
            Row row = sheet.getRow(0);
            // 获取工作表总列数
            int colLength = row.getLastCellNum();

            // 创建一个单元格对象
            Cell cell;
            for (int i = 1; i < rowLength; i++) {
                StudentBean studentBean = new StudentBean();
                for (int j = 0; j < colLength; j++) {
                    // 获取第i行j列的数据
                    cell = sheet.getRow(i).getCell(j);
                    // 设置单元格的数据类型
                    cell.setCellType(CellType.STRING);
                    // 获取单元格的数据
                    String stringCellValue = cell.getStringCellValue();
                    // 通过
                    switch (j) {
                        case 0:
                            studentBean.setNum(stringCellValue);
                            break;
                        case 1:
                            studentBean.setName(stringCellValue);
                            break;
                        case 2:
                            studentBean.setChineseScore(Integer.parseInt(stringCellValue));
                            break;
                        case 3:
                            studentBean.setMathScore(Integer.parseInt(stringCellValue));
                            break;
                        case 4:
                            studentBean.setEnglishScore(Integer.parseInt(stringCellValue));
                            break;
                        case 5:
                            studentBean.setTotalScore(Integer.parseInt(stringCellValue));
                            break;
                    }
                }
                studentBeanList.add(studentBean);
            }
        } catch (Exception e) {
            LOGGER.error("parse excel file error :", e);
        }
        return studentBeanList;
    }
}
