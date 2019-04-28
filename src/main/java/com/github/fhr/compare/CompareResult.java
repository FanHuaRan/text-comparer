package com.github.fhr.compare;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

import static com.github.fhr.compare.Constants.*;

/**
 * @author Fan Huaran
 * created on 2019/1/17
 * @description
 */
public class CompareResult {
    private int diff;

    private int notChange;

    private int addDiff;

    private int deleteDiff;

    private int updateDiff;

    private final List<ModifyRow> rows = new LinkedList<>();

    public int getDiff() {
        return diff;
    }

    public void setDiff(int diff) {
        this.diff = diff;
    }

    public int getNotChange() {
        return notChange;
    }

    public void setNotChange(int notChange) {
        this.notChange = notChange;
    }

    public int getAddDiff() {
        return addDiff;
    }

    public void setAddDiff(int addDiff) {
        this.addDiff = addDiff;
    }

    public int getDeleteDiff() {
        return deleteDiff;
    }

    public void setDeleteDiff(int deleteDiff) {
        this.deleteDiff = deleteDiff;
    }

    public int getUpdateDiff() {
        return updateDiff;
    }

    public void setUpdateDiff(int updateDiff) {
        this.updateDiff = updateDiff;
    }

    public List<ModifyRow> getRows() {
        return rows;
    }

    public void addRow(ModifyRow modifyRow) {
        if (MODIFY_ROW_NOT == modifyRow.getModifyType()) {
            notChange++;
        } else if (MODIFY_ROW_UPDATE == modifyRow.getModifyType()) {
            diff++;
            updateDiff++;
        } else if (MODIFY_ROW_ADD == modifyRow.getModifyType()) {
            diff++;
            addDiff++;
        } else if (MODIFY_ROW_DELETE == modifyRow.getModifyType()) {
            diff++;
            deleteDiff++;
        } else {
            throw new IllegalArgumentException("illegal modify_type:" + modifyRow.getModifyType());
        }

        rows.add(modifyRow);
    }

    public void addNotRow(int rowNum, String row) {
        notChange++;
        rows.add(new ModifyRow(rowNum, MODIFY_ROW_NOT, row, null));
    }

    public void addUpdateRow(int rowNum, String row, String oldRow) {
        diff++;
        updateDiff++;
        rows.add(new ModifyRow(rowNum, MODIFY_ROW_UPDATE, row, oldRow));
    }

    public void addAddRows(int rowNum, String row) {
        diff++;
        addDiff++;
        rows.add(new ModifyRow(rowNum, MODIFY_ROW_ADD, row, null));
    }

    public void addDeleteRows(int rowNum, String row) {
        diff++;
        deleteDiff++;
        rows.add(new ModifyRow(rowNum, MODIFY_ROW_DELETE, row, null));
    }

    @Override
    public String toString() {
        return "CompareResult{" +
                "diff=" + diff +
                ", notChange=" + notChange +
                ", addDiff=" + addDiff +
                ", deleteDiff=" + deleteDiff +
                ", updateDiff=" + updateDiff +
                ", rows=" + rows +
                '}';
    }
}
