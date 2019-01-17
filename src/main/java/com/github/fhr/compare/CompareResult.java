package com.github.fhr.compare;

import java.util.List;

/**
 * @author Fan Huaran
 * created on 2019/1/17
 * @description
 */
public class CompareResult {
    private int diff;

    private List<ModifyRow> modifyRows;

    public int getDiff() {
        return diff;
    }

    public void setDiff(int diff) {
        this.diff = diff;
    }

    public void addRemoveRows(String[] srcRows) {
    }

    public void addAddRows(String[] destRows) {
    }
}
