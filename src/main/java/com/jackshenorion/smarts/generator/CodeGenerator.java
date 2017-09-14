package com.jackshenorion.smarts.generator;

import com.jackshenorion.smarts.generator.sqlloader.SqlLoaderGenerator;

import java.io.File;

public class CodeGenerator {
    public final static String pojoTemplateFile = "templates/csv-sqlite-pojo-template.vm";
    public final static String xmlTemplateFile = "templates/csv-sqlite-xml-template.vm";
    public final static String schemaFile = "schemas/csv-sqlite-pojo-schema.json";
    public final static String outputDir = System.getProperty("user.home") + "/Documents/Temp/gen";

    public static void main(String[] args) throws Exception {
        new CodeGenerator().generate();
    }

    public void generate() throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        String absolutePath = new File(classLoader.getResource(schemaFile).getFile()).getAbsolutePath();;
        new SqlLoaderGenerator()
                .setVersion("1.0.0")
                .setPojoTemplateFile(pojoTemplateFile)
                .setXmlTemplateFile(xmlTemplateFile)
                .setSchemaFile(absolutePath)
                .setOutputDir(outputDir)
                .generate();
    }
}
