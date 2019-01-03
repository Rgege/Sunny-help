package org.blue.helper.test.util.support;

import java.util.List;
import java.util.Map;

public class ExcelTb {
    private Map<String,ExcelCell> cellMap;
    private int totalRows;
    private int totalLines;

    public Map<String, ExcelCell> getCellMap() {
        return cellMap;
    }

    public void setCellMap(Map<String, ExcelCell> cellMap) {
        this.cellMap = cellMap;
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public int getTotalLines() {
        return totalLines;
    }

    public void setTotalLines(int totalLines) {
        this.totalLines = totalLines;
    }

    @Override
    public String toString() {
        return "ExcelTb{" +
                "cellMap=" + cellMap +
                ", totalRows=" + totalRows +
                ", totalLines=" + totalLines +
                '}';
    }

//    public  ExcelCell getCell(int row,int line){
//        int size=this.cellList.size();
//        for (ExcelCell cell:this.cellList) {
//            if(cell.getLine()==line && cell.getRow()==row){
//                return cell;
//            }
//        }
//        return null;
//    }
}
