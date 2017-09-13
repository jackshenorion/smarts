package com.jackshenorion.smarts.generator.sqlloader;

public class SqlLoaderPojoProperty {
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

    public SqlLoaderPojoProperty setName(String name) {
        this.name = name;
        return this;
    }

    public String getType() {
        return type;
    }

    public SqlLoaderPojoProperty setType(String type) {
        this.type = type;
        return this;
    }

    public boolean getIsNumber() {
        return isNumber;
    }

    public SqlLoaderPojoProperty setIsNumber(boolean number) {
        isNumber = number;
        return this;
    }

    public String getCsvName() {
        return csvName;
    }

    public SqlLoaderPojoProperty setCsvName(String csvName) {
        this.csvName = csvName;
        return this;
    }

    public String getSqliteName() {
        return sqliteName;
    }

    public SqlLoaderPojoProperty setSqliteName(String sqliteName) {
        this.sqliteName = sqliteName;
        return this;
    }

    public String getSetter() {
        return setter;
    }

    public SqlLoaderPojoProperty setSetter(String setter) {
        this.setter = setter;
        return this;
    }

    public String getGetter() {
        return getter;
    }

    public SqlLoaderPojoProperty setGetter(String getter) {
        this.getter = getter;
        return this;
    }

    @Override
    public String toString() {
        return "SqlLoaderPojoProperty{" +
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
