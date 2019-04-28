package com.github.fhr.compare;

import org.apache.log4j.Logger;
import org.junit.Test;

import static com.github.fhr.compare.Constants.MODIFY_ROW_ADD;
import static com.github.fhr.compare.Constants.MODIFY_ROW_DELETE;
import static com.github.fhr.compare.Constants.MODIFY_ROW_UPDATE;

public class LevenshteinDistanceComparerTest {
    private static final Logger logger = Logger.getLogger(LevenshteinDistanceComparerTest.class);

    @Test
    public void compare() {
        LevenshteinDistanceComparer comparer = new LevenshteinDistanceComparer();
        test(comparer, "abc\ndef", "abc\nmn\nrt\ndef");
        test(comparer, "ab\ndef", "abc\nmn\nrt\ndef");
        test(comparer, "abc\t\ndef", "abc\nmn\nrt\ndef", "\t", "\n");
    }

    private void test(LevenshteinDistanceComparer comparer, String src, String dest, String... ignoreStrs) {
        CompareResult compareResult = comparer.compare(src.split("\n"), dest.split("\n"), ignoreStrs);
        logger.info(src);
        logger.info(dest);
        for (ModifyRow row : compareResult.getRows()) {
            if (row.getModifyType() == MODIFY_ROW_ADD) {
                logger.info("+ " + row.getRowValue());
            } else if (row.getModifyType() == MODIFY_ROW_DELETE) {
                logger.info("- " + row.getRowValue());
            } else if (row.getModifyType() == MODIFY_ROW_UPDATE) {
                logger.info(row.getOldRowValue() + " => " + row.getRowValue());
            } else {
                logger.info(row.getRowValue());
            }
        }
    }
}