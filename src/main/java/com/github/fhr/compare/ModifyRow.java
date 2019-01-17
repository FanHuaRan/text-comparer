package com.github.fhr.compare;

/**
 * @author Fan Huaran
 * created on 2019/1/17
 * @description
 */
public class ModifyRow {
    private final String rowNum;

    private final String modifyType;

    private final String rowValue;

    public ModifyRow(String rowNum, String modifyType, String rowValue) {
        this.rowNum = rowNum;
        this.modifyType = modifyType;
        this.rowValue = rowValue;
    }

    public String getRowNum() {
        return rowNum;
    }

    public String getModifyType() {
        return modifyType;
    }

    public String getRowValue() {
        return rowValue;
    }
}
