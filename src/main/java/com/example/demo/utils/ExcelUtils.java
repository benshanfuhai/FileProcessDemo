package com.example.demo.utils;

import com.example.demo.bean.StudentBean;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class ExcelUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelUtils.class);

    /**
     * 测试数据导入
     *
     * @param inputStream
     * @return
     */
    public static List<StudentBean> excelToStudentList_i(InputStream inputStream) {
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


    /**
     * excel文件转化为Student列表
     *
     * @param inputStream
     * @return
     */
    public static List<StudentBean> excelToStudentList(InputStream inputStream) {
        List<StudentBean> studentBeanList = new ArrayList<>();
        Workbook workbook;
        try {
            workbook = WorkbookFactory.create(inputStream);

            inputStream.close();

            Sheet sheet = workbook.getSheetAt(0);

            int rowLength = sheet.getLastRowNum() + 1;

            int colLength = sheet.getRow(0).getLastCellNum();

            Cell cell;
            for (int i = 1; i < rowLength; i++) {
                StudentBean studentBean = new StudentBean();
                Row row = sheet.getRow(i);
                for (int j = 0; j < colLength; j++) {
                    cell = row.getCell(j);
                    cell.setCellType(CellType.STRING);
                    String stringCellValue = cell.getStringCellValue();
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


    /**
     * 数据导出到excle
     *
     * @param studentBeanList     学生列表
     * @param httpServletResponse
     * @return
     */
    public static void exportToExcel(List<StudentBean> studentBeanList, HttpServletResponse httpServletResponse) {
        ServletOutputStream outputStream;
        try {
            outputStream = httpServletResponse.getOutputStream();

            XSSFWorkbook workbook = new XSSFWorkbook();

            XSSFSheet sheet = workbook.createSheet("students");

            String[] titles = {"num", "name", "chineseScore", "mathScore", "englishScore", "totalScore"};
            int colLength = titles.length;
            XSSFRow titleRow = sheet.createRow(0);
            for (int i = 0; i < colLength; i++) {
                XSSFCell titleCell = titleRow.createCell(i);
                titleCell.setCellType(CellType.STRING);
                titleCell.setCellValue(titles[i]);
            }
            for (int i = 1; i < studentBeanList.size(); i++) {
                StudentBean studentBean = studentBeanList.get(i);
                XSSFRow dataRow = sheet.createRow(i);
                for (int j = 0; j < colLength; j++) {
                    XSSFCell dataCell = dataRow.createCell(j);
                    dataCell.setCellType(CellType.STRING);
                    switch (j) {
                        case 0:
                            dataCell.setCellValue(studentBean.getNum());
                            break;
                        case 1:
                            dataCell.setCellValue(studentBean.getName());
                            break;
                        case 2:
                            dataCell.setCellValue(studentBean.getChineseScore());
                            break;
                        case 3:
                            dataCell.setCellValue(studentBean.getMathScore());
                            break;
                        case 4:
                            dataCell.setCellValue(studentBean.getEnglishScore());
                            break;
                        case 5:
                            dataCell.setCellValue(studentBean.getTotalScore());
                            break;
                    }
                }
            }
            httpServletResponse.setContentType("application/vnd.ms-excel");
            httpServletResponse.setHeader("Content-Disposition", "attachment; filename=" + "students1.xlsx");
            httpServletResponse.setCharacterEncoding("UTF-8");
            workbook.write(outputStream);
            workbook.close();
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
