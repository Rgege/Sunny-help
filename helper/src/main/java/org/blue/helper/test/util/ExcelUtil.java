package org.blue.helper.test.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.blue.helper.test.util.support.ExcelCell;
import org.blue.helper.test.util.support.ExcelTb;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.NumberFormat;
import java.util.*;

public class ExcelUtil {
    private static final Logger logger=LoggerFactory.getLogger(ExcelUtil.class);
    //excel数据存在map里，map.get(0).get(0)为excel第1行第1列的值，此处可对数据进行处理
    public static Map<Integer, Map<Integer,Object>> readExcelContentz(String path) throws Exception{
        Map<Integer, Map<Integer, Object>> content = new HashMap<Integer, Map<Integer, Object>>();
        File file=new File(path);
        // 上传文件名
        Workbook wb = getWb(file);
        if (wb == null) {
            throw new Exception("Workbook对象为空！");
        }
        Sheet sheet = wb.getSheetAt(0);
        // 得到总行数
        int rowNum = sheet.getLastRowNum();
        Row row = sheet.getRow(0);
        int colNum = row.getPhysicalNumberOfCells();
        // 正文内容应该从第二行开始,第一行为表头的标题
        for (int i = 1; i <= rowNum; i++) {
            row = sheet.getRow(i);
            int j = 0;
            Map<Integer, Object> cellValue = new HashMap<Integer, Object>();
            while (j < colNum) {
                Object obj = getCellFormatValue(row.getCell(j));
                cellValue.put(j, obj);
                j++;
            }
            content.put(i, cellValue);

        }
        return content;
    }
    //根据Cell类型设置数据
    private static Object getCellFormatValue(Cell cell) {
        Object cellvalue = "";
        if (cell != null) {
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_NUMERIC:
                case Cell.CELL_TYPE_FORMULA: {
                    if (DateUtil.isCellDateFormatted(cell)) {
                        Date date = cell.getDateCellValue();
                        cellvalue = date;
                    } else {
                        cellvalue = fomartNumc(cell.getNumericCellValue());
                    }
                    break;
                }
                case Cell.CELL_TYPE_STRING:
                    cellvalue = cell.getRichStringCellValue().getString();
                    break;
                default:
                    cellvalue = "";
            }
        } else {
            cellvalue = "";
        }
        return cellvalue;
    }
    private static String fomartNumc(double numericCellValue){
        NumberFormat nf = NumberFormat.getInstance();
        String s = nf.format(numericCellValue);
        if (s.indexOf(",") >= 0) {
            s = s.replace(",", "");
        }
        return s;
    }
    private static Workbook getWb(File file){
        String filepath = file.getPath();
        String ext = filepath.substring(filepath.lastIndexOf("."));
        Workbook wb = null;
        try (InputStream is=new FileInputStream(file)){
            if(".xls".equals(ext)){
                wb = new HSSFWorkbook(is);
            }else if(".xlsx".equals(ext)){
                wb = new XSSFWorkbook(is);
            }else{
                wb=null;
            }
        } catch (FileNotFoundException e) {
           logger.error("FileNotFoundException:",e);
        } catch (IOException e) {
            logger.error("IOException:",e);
        }
        return wb;
    }

    public static ExcelTb readExcelContentzNew(String path) throws Exception{
        ExcelTb tb=new ExcelTb();
        Map<String,ExcelCell> cellMap=new HashMap<String, ExcelCell>();
        File file=new File(path);
        // 上传文件名
        Workbook wb = getWb(file);
        if (wb == null) {
            throw new Exception("Workbook对象为空！");
        }
        Sheet sheet = wb.getSheetAt(0);
        // 得到总行数
        int rowNum = sheet.getLastRowNum();
        tb.setTotalRows(rowNum);
        Row row = sheet.getRow(0);
        int colNum = row.getPhysicalNumberOfCells();
        tb.setTotalLines(colNum);
        // 正文内容应该从第二行开始,第一行为表头的标题
        for (int i = 1; i <= rowNum; i++) {
            row = sheet.getRow(i);
            int j = 0;
            Map<Integer, Object> cellValue = new HashMap<Integer, Object>();
            while (j < colNum) {
                ExcelCell cell=new ExcelCell();
                Object obj = getCellFormatValue(row.getCell(j));
                int line=j+1;
                cell.setLine(line);
                cell.setValue(obj.toString());
                cell.setRow(i);
                String key=i+""+line;
                cellMap.put(key,cell);
                j++;
            }

        }
        tb.setCellMap(cellMap);
        return tb;
    }
}
