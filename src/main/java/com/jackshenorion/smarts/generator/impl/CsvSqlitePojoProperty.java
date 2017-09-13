package com.jackshenorion.smarts.generator.impl;

public class CsvSqlitePojoProperty {
    private String name;
    private String type;
    private boolean isNumber;
    private String csvName;
    private String sqliteName;
    private String setter;
    private String getter;

    public String getName() {
        return name;
    }

    public CsvSqlitePojoProperty setName(String name) {
        this.name = name;
        return this;
    }

    public String getType() {
        return type;
    }

    public CsvSqlitePojoProperty setType(String type) {
        this.type = type;
        return this;
    }

    public boolean getIsNumber() {
        return isNumber;
    }

    public CsvSqlitePojoProperty setIsNumber(boolean number) {
        isNumber = number;
        return this;
    }

    public String getCsvName() {
        return csvName;
    }

    public CsvSqlitePojoProperty setCsvName(String csvName) {
        this.csvName = csvName;
        return this;
    }

    public String getSqliteName() {
        return sqliteName;
    }

    public CsvSqlitePojoProperty setSqliteName(String sqliteName) {
        this.sqliteName = sqliteName;
        return this;
    }

    public String getSetter() {
        return setter;
    }

    public CsvSqlitePojoProperty setSetter(String setter) {
        this.setter = setter;
        return this;
    }

    public String getGetter() {
        return getter;
    }

    public CsvSqlitePojoProperty setGetter(String getter) {
        this.getter = getter;
        return this;
    }

    @Override
    public String toString() {
        return "CsvSqlitePojoProperty{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", isNumber=" + isNumber +
                ", csvName='" + csvName + '\'' +
                ", sqliteName='" + sqliteName + '\'' +
                ", setter='" + setter + '\'' +
                ", getter='" + getter + '\'' +
                '}';
    }
}
