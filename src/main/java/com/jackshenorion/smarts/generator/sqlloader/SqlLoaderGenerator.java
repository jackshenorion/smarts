package com.jackshenorion.smarts.generator.sqlloader;

import com.jackshenorion.smarts.util.io.IOUtil;
import com.jackshenorion.smarts.util.json.JsonUtil;
import org.apache.commons.lang.WordUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SqlLoaderGenerator {
    private String schemaFile;
    private String pojoTemplateFile;
    private String xmlTemplateFile;
    private String outputDir;
    private String version;
    private Date date = new Date();
    private SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd hh:mm");

    private SqlLoaderSchema schema;

    public SqlLoaderGenerator setVersion(String version) {
        this.version = version;
        return this;
    }

    public SqlLoaderGenerator setSchemaFile(String schemaFilePath) {
        this.schemaFile = schemaFilePath;
        return this;
    }

    public SqlLoaderGenerator setPojoTemplateFile(String pojoTemplateFile) {
        this.pojoTemplateFile = pojoTemplateFile;
        return this;
    }

    public SqlLoaderGenerator setXmlTemplateFile(String xmlTemplateFile) {
        this.xmlTemplateFile = xmlTemplateFile;
        return this;
    }

    public SqlLoaderGenerator setOutputDir(String outputDir) {
        this.outputDir = outputDir;
        return this;
    }

    public void generate() throws Exception {
        schema = loadSchema();
        Template pojoTemplate = loadTemplate(pojoTemplateFile);
        Template xmlTemplate = loadTemplate(xmlTemplateFile);
        initDirectory(outputDir);
        schema.getTables().forEach(table -> {
            enhancePojo(table.pojo);
            createPojoClassFileFromTemplate(table, pojoTemplate);
            createXmlFileFromTemplate(table, xmlTemplate);
        });
    }

    private SqlLoaderSchema loadSchema() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        String absolutePath = new File(classLoader.getResource(schemaFile).getFile()).getAbsolutePath();;
        return JsonUtil.readFromFile(absolutePath, SqlLoaderSchema.class);
    }

    private Template loadTemplate(String templateFile) {
        VelocityEngine ve = new VelocityEngine();
        ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        ve.init();
        return ve.getTemplate(templateFile);
    }

    private void createPojoClassFileFromTemplate(SqlLoaderTable table, Template template) {
        String s = getStringFromTemplate(template, table);
        String filePath = outputDir + "/" + this.schema.getJavaPackage() + "/" + table.getPojo().getClassName() + ".java";
        writeFile(filePath, s);
    }

    private void createXmlFileFromTemplate(SqlLoaderTable table, Template template) {
        String s = getStringFromTemplate(template, table);
        System.out.println(s);
        String filePath = outputDir + "/" + this.schema.getJavaPackage() + "/" + table.getConverter().getFileName();
        writeFile(filePath, s);
    }

    private void enhancePojo(SqlLoaderPojo pojo) {
        pojo.getProps().forEach(prop -> {
            prop.setType(prop.getIsNumber() ? "double" : "String");
            prop.setSetter("set" + WordUtils.capitalize(prop.getName()));
            prop.setGetter("get" + WordUtils.capitalize(prop.getName()));
        });
    }

    private String getStringFromTemplate(Template t, SqlLoaderTable table) {
        VelocityContext ctx = new VelocityContext();
        ctx.put("version", version);
        ctx.put("date", date);
        ctx.put("schema", schema);
        ctx.put("table", table);
        StringWriter sw = new StringWriter();
        t.merge(ctx, sw);
        return sw.toString();
    }

    private void writeFile(String filePath, String pojoString) {
        try {
            IOUtil.writeTextFile(filePath, pojoString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initDirectory(String javaOutputDir) throws IOException {
        File dir = new File(javaOutputDir);
        IOUtil.touchDirectory(dir);
    }

}
