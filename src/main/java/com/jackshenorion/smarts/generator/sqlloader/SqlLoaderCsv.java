package com.jackshenorion.smarts.generator.sqlloader;

public class SqlLoaderCsv {
    public String midfix;
    public String format;
    public String delimiter;
    public boolean ignoreHeaderLine;

    public String getMidfix() {
        return midfix;
    }

    public SqlLoaderCsv setMidfix(String midfix) {
        this.midfix = midfix;
        return this;
    }

    public String getFormat() {
        return format;
    }

    public SqlLoaderCsv setFormat(String format) {
        this.format = format;
        return this;
    }

    public String getDelimiter() {
        return delimiter;
    }

    public SqlLoaderCsv setDelimiter(String delimiter) {
        this.delimiter = delimiter;
        return this;
    }

    public boolean isIgnoreHeaderLine() {
        return ignoreHeaderLine;
    }

    public SqlLoaderCsv setIgnoreHeaderLine(boolean ignoreHeaderLine) {
        this.ignoreHeaderLine = ignoreHeaderLine;
        return this;
    }

    @Override
    public String toString() {
        return "SqlLoaderCsv{" +
                "midfix='" + midfix + '\'' +
                ", format='" + format + '\'' +
                ", delimiter='" + delimiter + '\'' +
                ", ignoreHeaderLine=" + ignoreHeaderLine +
                '}';
    }
}
