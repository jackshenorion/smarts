package com.jackshenorion.smarts.generator.sqlloader;

public class SqlLoaderSqlite {
    private String dbFile;
    private String tableName;

    public String getDbFile() {
        return dbFile;
    }

    public SqlLoaderSqlite setDbFile(String dbFile) {
        this.dbFile = dbFile;
        return this;
    }

    public String getTableName() {
        return tableName;
    }

    public SqlLoaderSqlite setTableName(String tableName) {
        this.tableName = tableName;
        return this;
    }

    @Override
    public String toString() {
        return "SqlLoaderSqlite{" +
                "dbFile='" + dbFile + '\'' +
                ", tableName='" + tableName + '\'' +
                '}';
    }
}
