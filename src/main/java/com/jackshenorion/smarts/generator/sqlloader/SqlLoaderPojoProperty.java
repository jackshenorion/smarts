package com.jackshenorion.smarts.generator.sqlloader;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class SqlLoaderPojoProperty {
    private String csvName;
    private String sqliteName;
    private String name;
    private boolean isDouble;
    private boolean isLong;
    private boolean isInt;
    private boolean notNull;
    @JsonIgnore
    private String setter;
    @JsonIgnore
    private String getter;

    public String getName() {
        return name;
    }

    public SqlLoaderPojoProperty setName(String name) {
        this.name = name;
        return this;
    }

    public boolean isDouble() {
        return isDouble;
    }

    public SqlLoaderPojoProperty setDouble(boolean aDouble) {
        isDouble = aDouble;
        return this;
    }

    public boolean isLong() {
        return isLong;
    }

    public SqlLoaderPojoProperty setLong(boolean aLong) {
        isLong = aLong;
        return this;
    }

    public boolean isInt() {
        return isInt;
    }

    public SqlLoaderPojoProperty setInt(boolean anInt) {
        isInt = anInt;
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

    public boolean getNotNull() {
        return notNull;
    }

    public SqlLoaderPojoProperty setNotNull(boolean notNull) {
        this.notNull = notNull;
        return this;
    }

    @Override
    public String toString() {
        return "SqlLoaderPojoProperty{" +
                "csvName='" + csvName + '\'' +
                ", sqliteName='" + sqliteName + '\'' +
                ", name='" + name + '\'' +
                ", isDouble=" + isDouble +
                ", isLong=" + isLong +
                ", notNull=" + notNull +
                ", setter='" + setter + '\'' +
                ", getter='" + getter + '\'' +
                '}';
    }
}
