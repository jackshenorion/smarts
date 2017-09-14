package com.jackshenorion.smarts.generator.sqlloader;

public class SqlLoaderCsv {
    public String midfix;
    public String format;
    public String delimiter;
    public boolean doIgnoreRecord;
    public String ignoredRecordBy;
    public String ignoredRecordEnglish;

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

    public boolean isDoIgnoreRecord() {
        return doIgnoreRecord;
    }

    public SqlLoaderCsv setDoIgnoreRecord(boolean doIgnoreRecord) {
        this.doIgnoreRecord = doIgnoreRecord;
        return this;
    }

    public String getIgnoredRecordBy() {
        return ignoredRecordBy;
    }

    public SqlLoaderCsv setIgnoredRecordBy(String ignoredRecordBy) {
        this.ignoredRecordBy = ignoredRecordBy;
        return this;
    }

    public String getIgnoredRecordEnglish() {
        return ignoredRecordEnglish;
    }

    public SqlLoaderCsv setIgnoredRecordEnglish(String ignoredRecordEnglish) {
        this.ignoredRecordEnglish = ignoredRecordEnglish;
        return this;
    }

    @Override
    public String toString() {
        return "SqlLoaderCsv{" +
                "delimiter='" + delimiter + '\'' +
                ", doIgnoreRecord=" + doIgnoreRecord +
                ", ignoredRecordBy='" + ignoredRecordBy + '\'' +
                ", ignoredRecordEnglish='" + ignoredRecordEnglish + '\'' +
                ", midfix='" + midfix + '\'' +
                ", format='" + format + '\'' +
                '}';
    }
}
