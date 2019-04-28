package com.github.fhr.util;

import java.util.Objects;

/**
 * Created by Huaran Fan on 2019/1/17
 *
 * @description
 */
public class StringUtils {

    public static boolean equals(String src, String dest, String... ignoreStrs) {
        if (src == null && dest == null) {
            return true;
        }
        if (src == null && dest != null) {
            return false;
        }
        if (src != null && dest == null) {
            return false;
        }

        // TODO use the special alo to optimization the equals
        for (String ignoreStr : ignoreStrs) {
            src = src.replace(ignoreStr, "");
            dest = dest.replace(ignoreStr, "");
        }

        return src.equals(dest);
    }
}
