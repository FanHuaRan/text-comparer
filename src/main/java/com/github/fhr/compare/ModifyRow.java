package com.github.fhr.compare;

/**
 * @author Fan Huaran
 * created on 2019/1/17
 * @description
 */
public class ModifyRow {

    private final Integer rowNum;

    private final Integer modifyType;

    private final String rowValue;

    // old row value only use when modify is update.
    private final String oldRowValue;

    public ModifyRow(Integer rowNum, Integer modifyType, String rowValue, String oldRowValue) {
        this.rowNum = rowNum;
        this.modifyType = modifyType;
        this.rowValue = rowValue;
        this.oldRowValue = oldRowValue;
    }

    public Integer getRowNum() {
        return rowNum;
    }

    public Integer getModifyType() {
        return modifyType;
    }

    public String getRowValue() {
        return rowValue;
    }

    public String getOldRowValue() {
        return oldRowValue;
    }

    @Override
    public String toString() {
        return "ModifyRow{" +
                "rowNum=" + rowNum +
                ", modifyType=" + modifyType +
                ", rowValue='" + rowValue + '\'' +
                ", oldRowValue='" + oldRowValue + '\'' +
                '}';
    }
}
