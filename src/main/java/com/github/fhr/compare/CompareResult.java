package com.github.fhr.compare;

import java.util.List;
import java.util.TreeSet;

/**
 * @author Fan Huaran
 * created on 2019/1/17
 * @description
 */
public class CompareResult {
    private int diff;


    private TreeSet<ModifyRow> modifyRows = new TreeSet<>();

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

    public TreeSet<ModifyRow> getModifyRows() {
        return modifyRows;
    }

    public void setModifyRows(TreeSet<ModifyRow> modifyRows) {
        this.modifyRows = modifyRows;
    }
}
