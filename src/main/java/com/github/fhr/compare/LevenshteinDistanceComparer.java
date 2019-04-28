package com.github.fhr.compare;

import com.github.fhr.util.StringUtils;

import java.util.Stack;

import static com.github.fhr.compare.Constants.*;

/**
 * @author Fan Huaran
 * created on 2019/1/17
 * @description
 */
public class LevenshteinDistanceComparer {

    public CompareResult compare(String[] srcRows, String[] destRows, String... ignorStrs) {
        if (destRows == null || srcRows == null) {
            throw new IllegalArgumentException("src row or dest row must not be null");
        }

        if (destRows.length == 0) {
            return completeDeleteRows(srcRows);
        }

        if (srcRows.length == 0) {
            return completeAddRows(destRows);
        }

        int srcLen = srcRows.length;
        int destLen = destRows.length;

        // dp[i][j]代表将src的前i行转为dest的前j行需要的步数
        int[][] dp = new int[srcLen + 1][destLen + 1];
        // 初始化
        for (int i = 0; i <= srcLen; i++) {
            dp[i][0] = i;
        }
        for (int i = 0; i <= destLen; i++) {
            dp[0][i] = i;
        }

        // 执行动态规划
        for (int i = 1; i <= srcLen; i++) {
            for (int j = 1; j <= destLen; j++) {
                int case1 = dp[i - 1][j] + 1;// src前i-1行变为dest前j行，再减去src的第i行
                int case2 = dp[i][j - 1] + 1;// src前i行变为dest前j-1行，再加上dest的第j行
                int case3 = dp[i - 1][j - 1];// src前i-1行变为dest前j-1行
                String srcRow = srcRows[i - 1];
                String destRow = destRows[j - 1];
                if (!StringUtils.equals(srcRow, destRow, ignorStrs)) {
                    case3++; // i行与j行不相等，case3+1
                }

                dp[i][j] = Math.min(Math.min(case1, case2), case3);
            }
        }

        Stack<ModifyRow> modifyRows = new Stack<>();
        // 反向进行回溯
        for (int i = srcLen, j = destLen; i > 0 || j > 0; ) {
            if (i > 0 && j > 0 && StringUtils.equals(srcRows[i - 1], destRows[j - 1], ignorStrs)) {
                modifyRows.push(new ModifyRow(i - 1, MODIFY_ROW_NOT, srcRows[i - 1], null));
                i--;
                j--;
                continue;
            }

            int case1 = Integer.MAX_VALUE;
            if (i > 0) {
                case1 = dp[i - 1][j];
            }
            int case2 = Integer.MAX_VALUE;
            if (j > 0) {
                case2 = dp[i][j - 1];
            }
            int case3 = Integer.MAX_VALUE;
            if (i > 0 && j > 0) {
                case3 = dp[i - 1][j - 1];
            }

            int minValue = Math.min(Math.min(case1, case2), case3);
            if (case1 == minValue) {
                modifyRows.push(new ModifyRow(i, MODIFY_ROW_DELETE, srcRows[i - 1], null));
                i--;
            } else if (case2 == minValue) {
                modifyRows.push(new ModifyRow(i, MODIFY_ROW_ADD, destRows[j - 1], null));
                j--;
            } else {
                modifyRows.push(new ModifyRow(i, MODIFY_ROW_UPDATE, destRows[j - 1], srcRows[i - 1]));
                i--;
                j--;
            }
        }

        CompareResult compareResult = new CompareResult();
        while (!modifyRows.isEmpty()) {
            compareResult.addRow(modifyRows.pop());
        }

        return compareResult;
    }

    private CompareResult completeAddRows(String[] rows) {
        int length = rows.length;
        CompareResult compareResult = new CompareResult();
        for (int i = 0; i < length; i++) {
            compareResult.addAddRows(i, rows[i]);
        }

        return compareResult;
    }

    private CompareResult completeDeleteRows(String[] rows) {
        int length = rows.length;
        CompareResult compareResult = new CompareResult();
        for (int i = 0; i < length; i++) {
            compareResult.addDeleteRows(i, rows[i]);
        }

        return compareResult;
    }
}
