package com.jackshenorion.smarts.generator;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.jackshenorion.smarts.generator.sqlloader.*;
import com.jackshenorion.smarts.util.csv.CsvReader;
import com.jackshenorion.smarts.util.io.IOUtil;
import com.jackshenorion.smarts.util.json.JsonUtil;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.WordUtils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class SchemaGenerator {

    public final static String marketCode = "dcass";
    public final static String javaPackage = "com.smartsgroup.connectors.hksqlgw.feedlets.lookup";
    public final static String processInputClass = "com.smartsgroup.tools.streams.csv.CsvInputStream";
    public final static String feedletClass = "com.smartsgroup.lib.sqlite.SqlLoaderFeedlet";
    public final static String converterXmlFilePrefix = "hksqlgw_loader_";
    public final static String sampleFilePathRoot = "samples/";
    public final static String outputDir = System.getProperty("user.home") + "/Documents/Temp/gen";
    public final static String schemaFileName = "csv-sqlite-pojo-schema.json";

    public static void main(String[] args) throws IOException {
        new SchemaGenerator().createSchema(Arrays.asList(
                new CsvFile().setFileName("BI_Omni_Code_Info_SFC.csv").setTableName("bi_omni_code_info"),
                new CsvFile().setFileName("EP_Info_SFC.csv").setTableName("ep_info"),
                new CsvFile().setFileName("EP_OI_SFC.csv").setTableName("ep_oi"),
                new CsvFile().setFileName("LOP TO BI CODE TO SFC.csv").setTableName("lop_to_bi_code"),
                new CsvFile().setFileName("LOP_AC_Info_SFC.csv").setTableName("lop_ac_info"),
                new CsvFile().setFileName("LOP_Data_SFC.csv").setTableName("lop_data"),
                new CsvFile().setFileName("Market_GPD.csv").setTableName("market_gpd"),
                new CsvFile().setFileName("Market_OI_SFC.csv").setTableName("market_oi"),
                new CsvFile().setFileName("TO_Code_Info_SFC.csv").setTableName("to_code_info"),
                new CsvFile().setFileName("TO_LOP_AC_Info_SFC.csv").setTableName("to_lop_ac_info"),
                new CsvFile().setFileName("TO_LOP_Data_SFC.csv").setTableName("to_lop_data"),
                new CsvFile().setFileName("TP001_o_Position.raw").setTableName("tp001_o_position"),
                new CsvFile().setFileName("TP001_f_Position.raw").setTableName("tp001_f_position")
        ));
    }

    public void createSchema(List<CsvFile> csvFiles) throws IOException {
        initDirectory(outputDir);
        SqlLoaderSchema schema = new SqlLoaderSchema()
                .setMarketCode(marketCode)
                .setJavaPackage(javaPackage)
                .setProcessInputClass(processInputClass)
                .setFeedletClass(feedletClass)
                .setTables(Lists.newArrayList());

        for (CsvFile file : csvFiles) {
            schema.getTables().add(createCsvFileSchema(file));
        }
        String filePath = outputDir + "/" + schemaFileName;
        writeFile(filePath, JsonUtil.writeAsJsonString(schema));
    }

    private void writeFile(String filePath, String pojoString) {
        try {
            System.out.println("Write file " + filePath + " successfully!");
            IOUtil.writeTextFile(filePath, pojoString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initDirectory(String javaOutputDir) throws IOException {
        File dir = new File(javaOutputDir);
        IOUtil.touchDirectory(dir);
    }

    public String getAbsolutePath(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        String absolutePath = new File(classLoader.getResource(sampleFilePathRoot + fileName).getFile()).getAbsolutePath();
        return absolutePath.replace("%20", " ");
    }

    private SqlLoaderTable createCsvFileSchema(CsvFile file) throws IOException {
        SqlLoaderTable table = new SqlLoaderTable();
        table.setDescription("Converts " + getFileBaseName(file) + " to Sqlite")
                .setCsv(createCsvSchemaPart(file))
                .setSqlite(createSqliteSchemaPart(file))
                .setConverter(createConverterSchemaPart(file))
                .setPojo(createPojoSchemaPart(file));
        return table;
    }

    private SqlLoaderCsv createCsvSchemaPart(CsvFile file) throws IOException {
        SqlLoaderCsv csv = new SqlLoaderCsv();
        csv.setDelimiter(file.getDelimiter())
                .setMidfix("." + getFileBaseName(file).toLowerCase().replace(" ", "_"))
                .setFormat(getFileExtension(file).toLowerCase())
                .setIgnoreHeaderLine(file.isHasTitle());
        return csv;
    }

    private SqlLoaderSqlite createSqliteSchemaPart(CsvFile file) {
        SqlLoaderSqlite sqlite = new SqlLoaderSqlite();
        sqlite.setDbFile(getDbFile(file).replace(" ", "_"))
                .setTableName(getTableName(file).replace(" ", "_"));
        return sqlite;
    }

    private SqlLoaderConverter createConverterSchemaPart(CsvFile file) {
        SqlLoaderConverter converter = new SqlLoaderConverter();
        converter.setFileName(converterXmlFilePrefix + getTableName(file).toLowerCase().replace(" ", "_") + ".xml");
        return converter;
    }

    private SqlLoaderPojo createPojoSchemaPart(CsvFile file) throws IOException {
        SqlLoaderPojo pojo = new SqlLoaderPojo();
        List<SqlLoaderPojoProperty> props = Lists.newArrayList();
        getTitles(file).forEach(title -> {
            if (!Strings.isNullOrEmpty(title)) {
                props.add(createPojoProperty(title));
            }
        });
        pojo.setClassName(createClassNameFromTableName(getTableName(file)));
        pojo.setProps(props);
        return pojo;
    }

    private SqlLoaderPojoProperty createPojoProperty(String title) {
        SqlLoaderPojoProperty prop = new SqlLoaderPojoProperty();
        String fieldName = createFieldNameFromTitle(title);
        boolean isNumber = isNumber(title);
        boolean notNull = notNull(title);

        prop.setCsvName(getTitleWithoutAttributes(title))
                .setSqliteName(createSqliteNameFromTitle(title))
                .setName(fieldName)
                .setIsNumber(isNumber)
                .setNotNull(notNull)
                .setType("String")
                .setSetter("set" + WordUtils.capitalize(fieldName))
                .setGetter("get" + WordUtils.capitalize(fieldName));
        return prop;
    }

    private String getTitleWithoutAttributes(String title) {
        return title.indexOf(":") >= 0 ? title.substring(0, title.indexOf(":")) : title;

    }

    private String getTitleAttribute(String title) {
        return title.indexOf(":") >= 0 ? title.substring(title.indexOf(":")) : "";
    }

    private boolean notNull(String title) {
        // if title's attribute part has "n", notNull = true
        return getTitleAttribute(title).contains("n");
    }

    private boolean isNumber(String title) {
        // if title's attribute part has "d", isNumber = true
        return getTitleAttribute(title).contains("d");
    }

    private String createClassNameFromTableName(String tableName) {
        StringBuilder sb = new StringBuilder();
        for (String t : split(tableName)) {
            sb.append(WordUtils.capitalize(t.toLowerCase()));
        }
        return sb.toString();
    }

    private String createSqliteNameFromTitle(String title) {
        String titleWithoutAttributes = title.indexOf(":") >= 0 ? title.substring(0, title.indexOf(":")) : title;
        return String.join("_", split(titleWithoutAttributes.replace("A/C", "AC"))).toUpperCase();
    }

    private String createFieldNameFromTitle(String title) {
        String titleWithoutAttributes = title.indexOf(":") >= 0 ? title.substring(0, title.indexOf(":")) : title;
        StringBuilder sb = new StringBuilder();
        for (String t : split(titleWithoutAttributes.replace("A/C", "AC"))) {
            sb.append(WordUtils.capitalize(t.toLowerCase()));
        }
        return WordUtils.uncapitalize(sb.toString());
    }

    private String[] split(String s) {
        return s.split("[^a-zA-Z0-9']+");
    }

    private List<String> getTitles(CsvFile file) throws IOException {
        if (!file.isHasTitle()) {
            return file.getTitles();
        } else {
            return Arrays.asList(CsvReader.readTitle(getAbsolutePath(file.getFileName())));
        }
    }

    private String getDbFile(CsvFile file) {
        return file.getTableName() + ".sqlite";
    }

    private String getTableName(CsvFile file) {
        return file.getTableName(); //getFileBaseName(file).toLowerCase();
    }

    private String getFileBaseName(CsvFile file) {
        return FilenameUtils.getBaseName(file.getFileName());
    }

    private String getFileExtension(CsvFile file) {
        return FilenameUtils.getExtension(file.getFileName());
    }


    static class CsvFile {
        private String fileName;
        private String tableName;
        private boolean hasTitle = true;
        private String delimiter = ",";
        private List<String> titles = Lists.newArrayList();

        public String getFileName() {
            return fileName;
        }

        public CsvFile setFileName(String fileName) {
            this.fileName = fileName;
            return this;
        }

        public boolean isHasTitle() {
            return hasTitle;
        }

        public CsvFile setHasTitle(boolean hasTitle) {
            this.hasTitle = hasTitle;
            return this;
        }

        public List<String> getTitles() {
            return titles;
        }

        public CsvFile setTitles(List<String> titles) {
            this.titles = titles;
            return this;
        }

        public String getDelimiter() {
            return delimiter;
        }

        public CsvFile setDelimiter(String delimiter) {
            this.delimiter = delimiter;
            return this;
        }

        public String getTableName() {
            return tableName;
        }

        public CsvFile setTableName(String tableName) {
            this.tableName = tableName;
            return this;
        }
    }
}
