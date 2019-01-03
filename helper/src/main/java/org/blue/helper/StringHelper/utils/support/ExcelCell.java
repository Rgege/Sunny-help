package org.blue.helper.StringHelper.utils.support;

public class ExcelCell {
    private int line;
    private int row;
    private String value;

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ExcelCell{" +
                "line=" + line +
                ", row=" + row +
                ", value='" + value + '\'' +
                '}';
    }
}
