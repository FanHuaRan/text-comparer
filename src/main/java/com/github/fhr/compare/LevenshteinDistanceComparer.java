package com.github.fhr.compare;

/**
 * @author Fan Huaran
 * created on 2019/1/17
 * @description
 */
public class LevenshteinDistanceComparer {

    public CompareResult compare(String[] srcRows, String[] destRows, String... ignorStrs) {
        if (destRows == null || srcRows == null) {
            throw new IllegalArgumentException("srcRows or destRow must not be null");
        }
        CompareResult compareResult = new CompareResult();
        if (destRows.length == 0) {
            compareResult.addRemoveRows(srcRows);
            return compareResult;
        }

        if (srcRows.length == 0) {
            compareResult.addAddRows(destRows);
            return compareResult;
        }

        int srcLen = srcRows.length;
        int desLen = destRows.length;

        // dp[i][j]代表将src的前i个字符转为dest的前j个字符需要的步数
        int[][] dp = new int[srcLen + 1][desLen + 1];
        // 初始化
        for (int i = 0; i <= srcLen; i++) {
            dp[i][0] = i;
        }
        for (int i = 0; i <= desLen; i++) {
            dp[0][i] = i;
        }

        for (int i = 1; i <= srcLen; i++) {
            for (int j = 1; j <= desLen; j++) {
                int case1 = dp[i - 1][j] + 1;// 前i-1个字符变为前j个字符前-1个字符
                int case2 = dp[i][j - 1] + 1;// 前i个字符变为前j-1个字符后+1个字符
                int case3 = dp[i - 1][j - 1];// 前i-1个字符变为前j-1个字符
                String srcRow = srcRows[i - 1];
                String destRow = destRows[j - 1];
                if (!equalStr(srcRow, destRow, ignorStrs)) {
                    case3++; // i字符与j字符不相等，+1
                }

                dp[i][j] = Math.min(Math.min(case1, case2), case3);
            }
        }

        int diff = dp[srcLen][desLen];
        compareResult.setDiff(diff);
        return compareResult;
    }

    private boolean equalStr(String str1, String str2, String... ignorStrs) {
        if (str1 == null && str2 == null) {
            return true;
        }
        if (str1 != null) {
            return str1.equals(str2);
        }

        return str2.equals(str1);
    }

}
