package com.jackshenorion.smarts.generator.sqlloader;

public class SqlLoaderSqlite {
    private String dbFile;
    private String tableName;

    public String getDbFile() {
        return dbFile;
    }

    public void setDbFile(String dbFile) {
        this.dbFile = dbFile;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    @Override
    public String toString() {
        return "SqlLoaderSqlite{" +
                "dbFile='" + dbFile + '\'' +
                ", tableName='" + tableName + '\'' +
                '}';
    }
}
