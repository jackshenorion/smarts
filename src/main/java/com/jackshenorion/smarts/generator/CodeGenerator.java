package com.jackshenorion.smarts.generator;

import com.jackshenorion.smarts.generator.impl.CsvSqlitePojoGenerator;

public class CodeGenerator {
    public static final String INPUT_DIR = "/Users/jshen/Documents/workspace/smarts/src/main/java/com/jackshenorion/smarts/generator/";
    public static final String CSV_SQLITE_POJO_TMPLATE_FILE = INPUT_DIR + "template/csv-sqlite-pojo-template.txt";
    public static final String CSV_SQLITE_POJO_SCHEMA_FILE = INPUT_DIR + "schema/csv-sqlite-pojo-schema.json";
    public static final String CSV_SQLITE_POJO_PKG = "com.smartsgroup.connectors.hksql.feedlets.lookup";
    public static String outputDir = System.getProperty("user.home") + "/Temp/gen";


    public static void main(String[] args) throws Exception {
        generateCsvSqlitePojo();
    }

    private static void generateCsvSqlitePojo() throws Exception {
        new CsvSqlitePojoGenerator()
                .setVersion(1)
                .setTemplateFile(CSV_SQLITE_POJO_TMPLATE_FILE)
                .setSchemaFile(CSV_SQLITE_POJO_SCHEMA_FILE)
                .setJavaPkg(CSV_SQLITE_POJO_PKG)
                .setOutputDir(outputDir)
                .generate();
    }
}
