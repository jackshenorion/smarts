package com.jackshenorion.smarts.util.csv;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {

    public static List<String[]> read(InputStream inputStream) throws IOException {
        List<String[]> records = new ArrayList<>();
        CsvMapper mapper = new CsvMapper();
        mapper.enable(CsvParser.Feature.WRAP_AS_ARRAY);
        MappingIterator<String[]> it = mapper.readerFor(String[].class).readValues(inputStream);
        while (it.hasNext()) {
            records.add(it.next());
        }
        return records;
    }

    public static List<String[]> read(File file) throws IOException {
        return read(new FileInputStream(file));
    }

    public static List<String[]> read(String fileName) throws IOException {
        return read(new FileInputStream(new File(fileName)));
    }

}
