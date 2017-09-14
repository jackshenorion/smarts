package com.jackshenorion.smarts.generator.sqlloader;

public class SqlLoaderTable {
    public String description = "Converts CSV to Sqlite";
    public SqlLoaderCsv csv;
    public SqlLoaderSqlite sqlite;
    public SqlLoaderConverter converter;
    public SqlLoaderPojo pojo;

    public String getDescription() {
        return description;
    }

    public SqlLoaderTable setDescription(String description) {
        this.description = description;
        return this;
    }

    public SqlLoaderCsv getCsv() {
        return csv;
    }

    public SqlLoaderTable setCsv(SqlLoaderCsv csv) {
        this.csv = csv;
        return this;
    }

    public SqlLoaderSqlite getSqlite() {
        return sqlite;
    }

    public SqlLoaderTable setSqlite(SqlLoaderSqlite sqlite) {
        this.sqlite = sqlite;
        return this;
    }

    public SqlLoaderConverter getConverter() {
        return converter;
    }

    public SqlLoaderTable setConverter(SqlLoaderConverter converter) {
        this.converter = converter;
        return this;
    }

    public SqlLoaderPojo getPojo() {
        return pojo;
    }

    public SqlLoaderTable setPojo(SqlLoaderPojo pojo) {
        this.pojo = pojo;
        return this;
    }

    @Override
    public String toString() {
        return "SqlLoaderTable{" +
                "description='" + description + '\'' +
                ", pojo=" + pojo +
                ", csv=" + csv +
                ", sqlite=" + sqlite +
                ", converter=" + converter +
                '}';
    }
}
