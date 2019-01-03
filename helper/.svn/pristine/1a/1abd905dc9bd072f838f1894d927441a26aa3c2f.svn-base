package org.blue.helper.StringHelper.utils.support;

import java.util.List;

public class ExcelTb {
    private List<ExcelCell> cellList;
    private int totalRows;
    private int totalLines;

    public List<ExcelCell> getCellList() {
        return cellList;
    }

    public void setCellList(List<ExcelCell> cellList) {
        this.cellList = cellList;
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
                "cellList=" + cellList +
                ", totalRows=" + totalRows +
                ", totalLines=" + totalLines +
                '}';
    }

    public ExcelCell getCell(int row, int line){
        int size=this.cellList.size();
        for (ExcelCell cell:this.cellList) {
            if(cell.getLine()==line && cell.getRow()==row){
                return cell;
            }
        }
        return null;
    }
}
