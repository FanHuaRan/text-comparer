package com.github.fhr.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Fan Huaran
 * created on 2019/4/28
 * @description
 */
public class FileUtils {

    public static String[] readToEnd(String fileName) throws IOException {
        try (FileReader fileReader = new FileReader(fileName);
             BufferedReader reader = new BufferedReader(fileReader)) {
            List<String> lines = new LinkedList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }

            return lines.toArray(new String[lines.size()]);
        }
    }

}
