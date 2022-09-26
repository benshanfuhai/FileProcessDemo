package com.example.demo.utils;

import com.linuxense.javadbf.DBFReader;

import java.io.FileInputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReadDBF {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\Wang\\Desktop\\T_JHK.dbf";
        List<Map<String, Object>> resList = readDBF(filePath);
        for (Map<String, Object> temp : resList) {
            System.out.println(temp.toString());
        }
    }

    public static List<Map<String, Object>> readDBF(String filePath) {
        FileInputStream inputStream;
        DBFReader reader;
        Object[] rowValues;
        List<Map<String, Object>> rowList = new ArrayList<>();
        try {
            // System.out.println("文件：" + filePath);
            inputStream = new FileInputStream(filePath);
            reader = new DBFReader(inputStream, Charset.forName("GBK"));
            int fieldCounts = reader.getFieldCount();
            // System.out.println("总列数：" + fieldCounts);
            int recordCounts = reader.getRecordCount();
            // System.out.println("总行数：" + recordCounts);
            String[] fieldNames = new String[fieldCounts];
            for (int i = 0; i < fieldCounts; i++) {
                fieldNames[i] = reader.getField(i).getName();
            }
            // System.out.println("列名称：" + Arrays.toString(fieldNames));
            // int j = 1;
            while ((rowValues = reader.nextRecord()) != null) {
                Map<String, Object> rowMap = new HashMap<>();
                for (int i = 0; i < rowValues.length; i++) {
                    rowMap.put(fieldNames[i], rowValues[i]);
                }
                // System.out.println("第" + j + "行：" + rowMap.toString());
                rowList.add(rowMap);
                // j++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowList;
    }
}
