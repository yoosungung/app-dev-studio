package kr.ac.jj.shared.infrastructure.grid.model;

public class DataCellMergeInfo {

    private int mergeStartRow;
    private String mergeValue;

    public DataCellMergeInfo(int mergeStartRow, String mergeValue) {
        this.reset(mergeStartRow, mergeValue);
    }

    public void reset(int mergeStartRow, String mergeValue) {
        this.mergeStartRow = mergeStartRow;
        this.mergeValue = mergeValue;
    }

    public int getMergeStartRow() {
        return this.mergeStartRow;
    }

    public void setMergeStartRow(int mergeStartRow) {
        this.mergeStartRow = mergeStartRow;
    }

    public String getMergeValue() {
        return this.mergeValue;
    }

    public void setMergeValue(String mergeValue) {
        this.mergeValue = mergeValue;
    }

}
