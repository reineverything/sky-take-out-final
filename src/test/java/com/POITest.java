package com;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;

import java.io.*;

/**
 * @ClassName POITest
 * @Author shuai
 * @create 2023/8/13 17:13
 * @Instruction
 */
public class POITest {

    /**
     * 解析"E:\itcast.xlsx"
     * workbook-sheet-row-cell
     */

    @Test
    public void testReadExcel() throws IOException {

        //1.构建workbook
        FileInputStream in = new FileInputStream(new File("E:\\itcast.xlsx"));
        Workbook workbook=new XSSFWorkbook(in);//接口实现类，对应不同版本的excel，这是新版

        //2.定位sheet
        Sheet sheet = workbook.getSheetAt(0);//第一张sheet

        //3.遍历一行
        int rowNum = sheet.getPhysicalNumberOfRows();//获取实际行数
        for(int i=0;i<rowNum;i++){
            Row row = sheet.getRow(i);
            int cellNum = row.getPhysicalNumberOfCells();//这行实际单元格

            StringBuffer sb=new StringBuffer();
            for(int j=0;j<cellNum;j++){
                Cell cell = row.getCell(j);
                if(cell !=null){
                    CellType cellType = cell.getCellType();
                    switch (cellType){
                        case NUMERIC://数字
                            sb.append(cell.getNumericCellValue()).append("  ");
                            break;
                        case STRING:
                            sb.append(cell.getStringCellValue()).append("  ");
                            break;
                        case ERROR:
                            sb.append(cell.getErrorCellValue()).append("  ");
                            break;
                        case BOOLEAN:
                            sb.append(cell.getBooleanCellValue()).append("  ");
                            break;
                        default:
                            sb.append(cell.getStringCellValue()).append("  ");
                            break;
                    }
                }
            }
            System.out.println(sb.toString());
        }

        //4获取每一个单元格
        //5释放资源
        workbook.close();
        in.close();
    }

    /**
     * 写入excel
     */
    @Test
    public void writeExcel() throws IOException {
        //1.创建workbook
        Workbook workbook = new XSSFWorkbook();

        //2.创建sheet
        Sheet sheet = workbook.createSheet("花名册");//设置表格名称

        /**
         * 设置行高列宽等
         */
        sheet.setDefaultColumnWidth(20);
        sheet.setDefaultRowHeight((short) 700);

        //3.创建row
        Row row0 = sheet.createRow(0);//表头

        //4.创建cell填数据
        Cell cell0 = row0.createCell(0);cell0.setCellValue("编号");
        Cell cell1 = row0.createCell(1);cell1.setCellValue("姓名");
        Cell cell2 = row0.createCell(2);cell2.setCellValue("性别");
        Cell cell3 = row0.createCell(3);cell3.setCellValue("年龄");

        for(int i=1;i<=10;i++){
            Row row = sheet.createRow(i );
            Cell _cell0 = row.createCell(0);_cell0.setCellValue(i);
            Cell _cell1 = row.createCell(1);_cell1.setCellValue("张"+i);
            Cell _cell2 = row.createCell(2);_cell2.setCellValue(i%2==0?"男":"女");
            Cell _cell3 = row.createCell(3);_cell3.setCellValue(15+i);
        }

        FileOutputStream out = new FileOutputStream(new File("E:\\花名册.xlsx"));
        workbook.write(out);

        //5.释放资源
        out.close();
        workbook.close();

    }
}
