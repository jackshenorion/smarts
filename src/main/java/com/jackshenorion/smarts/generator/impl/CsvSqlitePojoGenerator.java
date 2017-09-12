package com.jackshenorion.smarts.generator.impl;

import com.jackshenorion.smarts.util.io.IOUtil;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CsvSqlitePojoGenerator {
    private String schemaContent;
    private int version;
    private Date date = new Date();
    private SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd hh:mm");

    private String templateFile;
    private String javaPkg;

    private String outputDir;

    public CsvSqlitePojoGenerator setVersion(int version) {
        this.version = version;
        return this;
    }

    public CsvSqlitePojoGenerator setSchemaFile(String schemaFilePath) {
        try {
            this.schemaContent = IOUtil.readAndClose(new FileReader(schemaFilePath));
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        return this;
    }

    public CsvSqlitePojoGenerator setSchemaContent(String schemaContent) {
        this.schemaContent = schemaContent;
        return this;
    }

    public CsvSqlitePojoGenerator setTemplateFile(String templateFile) {
        this.templateFile = templateFile;
        return this;
    }

    public CsvSqlitePojoGenerator setJavaPkg(String javaPkg) {
        this.javaPkg = javaPkg;
        return this;
    }

    public CsvSqlitePojoGenerator setOutputDir(String outputDir) {
        this.outputDir = outputDir;
        return this;
    }

    public void generate() throws Exception {
        String javaOutputDir = outputDir + "/java";
        String template = IOUtil.readAndClose(new FileReader(templateFile));

        System.out.println(template);
        System.out.println(schemaContent);

//        initDirectory(javaOutputDir);

//        SchemaOptions options = new SchemaOptions()
//                .setJavaTypePackage(javaPkg)
//                .setJavaEnumPackage(javaEnumPkg)
//                ;
//
//        Schema schema = Schema.load(schemaContent, options);
//        for (Type type: schema.types) {
//            ST template = new ST(IOUtil.readAndClose(new FileReader(templateFile)));
//            template.add("version", version);
//            template.add("date", dateFormatter.format(date));
//            template.add("javaPackage", type.javaPackage);
//            template.add("javaImportPackages", type.javaImportPackages);
//            template.add("name", type.name);
//            template.add("backwardComp", type.backwardComp);
//            template.add("swiftName", type.swiftName);
//            template.add("base", type.base);
//            template.add("javaBase", type.javaBase);
//            template.add("hasBase", type.hasBase);
//            template.add("protocols", type.protocols);
//            template.add("props", type.props);
//            String filePath = javaOutputDir + "/" + type.javaPackage + "/" + type.name + ".java";
//            IOUtil.writeTextFile(filePath, template.render());
//            System.out.println(filePath);
//        }
//        for (EnumType enumType : schema.enumTypes.values()) {
//            ST template = new ST(IOUtil.readAndClose(new FileReader(javaEnumTmplFile)));
//            template.add("version", version);
//            template.add("date", dateFormatter.format(date));
//            template.add("javaPackage",   enumType.javaPackage);
//            template.add("name",  enumType.name);
//            template.add("swiftName", enumType.swiftName);
//            template.add("poe",   enumType.poe);
//            template.add("isInt", enumType.isInt);
//            template.add("items", enumType.items);
//            String filePath = javaOutputDir + "/" + enumType.javaPackage + "/" + enumType.name + ".java";
//            IOUtil.writeTextFile(filePath, template.render());
//            System.out.println(filePath);
//        }

    }

    private void initDirectory(String javaOutputDir) throws IOException {
        File dir = new File(javaOutputDir);
        IOUtil.touchDirectory(dir);
    }

}