package com.jackshenorion.smarts.generator.sqlloader;

public class SqlLoaderCsv {
    public String delimiter;
    public boolean doIgnoreRecord;
    public String ignoredRecordBy;
    public String ignoredRecordEnglish;
    public String midfix;
    public String format;

    public String getDelimiter() {
        return delimiter;
    }

    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }

    public boolean isDoIgnoreRecord() {
        return doIgnoreRecord;
    }

    public void setDoIgnoreRecord(boolean doIgnoreRecord) {
        this.doIgnoreRecord = doIgnoreRecord;
    }

    public String getIgnoredRecordBy() {
        return ignoredRecordBy;
    }

    public void setIgnoredRecordBy(String ignoredRecordBy) {
        this.ignoredRecordBy = ignoredRecordBy;
    }

    public String getIgnoredRecordEnglish() {
        return ignoredRecordEnglish;
    }

    public void setIgnoredRecordEnglish(String ignoredRecordEnglish) {
        this.ignoredRecordEnglish = ignoredRecordEnglish;
    }

    public String getMidfix() {
        return midfix;
    }

    public void setMidfix(String midfix) {
        this.midfix = midfix;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
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
