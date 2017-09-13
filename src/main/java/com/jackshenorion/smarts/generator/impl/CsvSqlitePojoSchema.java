package com.jackshenorion.smarts.generator.impl;

import java.util.List;

public class CsvSqlitePojoSchema {
    private String version;
    private String date;
    private String javaPackage;
    private String className;
    private String tableName;
    private List<CsvSqlitePojoProperty> props;

    public String getVersion() {
        return version;
    }

    public CsvSqlitePojoSchema setVersion(String version) {
        this.version = version;
        return this;
    }

    public String getDate() {
        return date;
    }

    public CsvSqlitePojoSchema setDate(String date) {
        this.date = date;
        return this;
    }

    public String getJavaPackage() {
        return javaPackage;
    }

    public CsvSqlitePojoSchema setJavaPackage(String javaPackage) {
        this.javaPackage = javaPackage;
        return this;
    }

    public String getClassName() {
        return className;
    }

    public CsvSqlitePojoSchema setClassName(String className) {
        this.className = className;
        return this;
    }

    public String getTableName() {
        return tableName;
    }

    public CsvSqlitePojoSchema setTableName(String tableName) {
        this.tableName = tableName;
        return this;
    }

    public List<CsvSqlitePojoProperty> getProps() {
        return props;
    }

    public CsvSqlitePojoSchema setProps(List<CsvSqlitePojoProperty> props) {
        this.props = props;
        return this;
    }

    @Override
    public String toString() {
        return "CsvSqlitePojoSchema{" +
                "version='" + version + '\'' +
                ", date='" + date + '\'' +
                ", javaPackage='" + javaPackage + '\'' +
                ", className='" + className + '\'' +
                ", tableName='" + tableName + '\'' +
                ", props=" + props +
                '}';
    }
}
