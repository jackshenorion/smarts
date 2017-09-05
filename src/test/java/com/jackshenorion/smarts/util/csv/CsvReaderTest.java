package com.jackshenorion.smarts.util.csv;

import org.junit.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class CsvReaderTest {
    @Test
    public void read() throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("test1.csv").getFile());
        List<String[]> records = CsvReader.read(file);
        records.forEach( record -> {
            Arrays.asList(record).forEach(field -> {
                System.out.print(field + ",");
            });
            System.out.println();
        });
    }

}