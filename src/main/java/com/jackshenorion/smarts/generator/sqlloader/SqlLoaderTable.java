package com.jackshenorion.smarts.generator.sqlloader;

public class SqlLoaderTable {
    public String description = "Converts CSV to Sqlite";
    public SqlLoaderPojo pojo;
    public SqlLoaderCsv csv;
    public SqlLoaderSqlite sqlite;
    public SqlLoaderConverter converter;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public SqlLoaderPojo getPojo() {
        return pojo;
    }

    public void setPojo(SqlLoaderPojo pojo) {
        this.pojo = pojo;
    }

    public SqlLoaderCsv getCsv() {
        return csv;
    }

    public void setCsv(SqlLoaderCsv csv) {
        this.csv = csv;
    }

    public SqlLoaderSqlite getSqlite() {
        return sqlite;
    }

    public void setSqlite(SqlLoaderSqlite sqlite) {
        this.sqlite = sqlite;
    }

    public SqlLoaderConverter getConverter() {
        return converter;
    }

    public void setConverter(SqlLoaderConverter converter) {
        this.converter = converter;
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
