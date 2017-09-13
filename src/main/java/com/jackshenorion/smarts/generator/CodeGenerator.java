package com.jackshenorion.smarts.generator;

import com.jackshenorion.smarts.generator.sqlloader.SqlLoaderGenerator;

public class CodeGenerator {
    public static final String SQLLOADER_POJO_TMPLATE_FILE = "templates/csv-sqlite-pojo-template.vm";
    public static final String SQLLOADER_XML_TMPLATE_FILE = "templates/csv-sqlite-xml-template.vm";
    public static final String SQLLOADER_SCHEMA_FILE = "schemas/csv-sqlite-pojo-schema.json";
    public static String outputDir = System.getProperty("user.home") + "/Documents/Temp/gen";

    public static void main(String[] args) throws Exception {
        new CodeGenerator().generateCsvSqlitePojo();
    }

    private void generateCsvSqlitePojo() throws Exception {
        new SqlLoaderGenerator()
                .setVersion("1.0.0")
                .setPojoTemplateFile(SQLLOADER_POJO_TMPLATE_FILE)
                .setXmlTemplateFile(SQLLOADER_XML_TMPLATE_FILE)
                .setSchemaFile(SQLLOADER_SCHEMA_FILE)
                .setOutputDir(outputDir)
                .generate();
    }
}
