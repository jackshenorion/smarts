package com.jackshenorion.smarts.generator.sqlloader;

public class SqlLoaderConverter {
    public String fileName;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return "SqlLoaderConverter{" +
                "fileName='" + fileName + '\'' +
                '}';
    }
}
