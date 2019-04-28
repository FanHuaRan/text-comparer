package com.github.fhr;

import com.github.fhr.compare.CompareResult;
import com.github.fhr.compare.LevenshteinDistanceComparer;
import com.github.fhr.compare.ModifyRow;
import com.github.fhr.util.FileUtils;

import java.io.IOException;

/**
 * @author Fan Huaran
 * created on 2019/4/28
 * @description
 */
public class App {
    public static void main(String[] args) throws IOException {
        if(args.length < 2) {
            System.out.println("usage: compare.jar <primary file path> <secondary file path>");
            return;
        }

        String[] srcRows = FileUtils.readToEnd(args[0]);
        String[] destRows = FileUtils.readToEnd(args[1]);
        LevenshteinDistanceComparer distanceComparer = new LevenshteinDistanceComparer();
        CompareResult compareResult = distanceComparer.compare(srcRows,destRows);

        for(ModifyRow modifyRow : compareResult.getRows()){
            System.out.println(modifyRow);
        }

    }
}
