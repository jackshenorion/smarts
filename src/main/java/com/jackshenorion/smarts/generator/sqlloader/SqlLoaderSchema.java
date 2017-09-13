package com.jackshenorion.smarts.generator.sqlloader;

import com.google.common.collect.Lists;

import java.util.List;

public class SqlLoaderSchema {
    private String marketCode = "CHANGE_ME";
    private String processInputClass = "CHANGE_ME";
    private String feedletClass = "CHANGE_ME";
    private String javaPackage = "CHANGE_ME";

    public List<SqlLoaderTable> tables = Lists.newArrayList();

    public String getMarketCode() {
        return marketCode;
    }

    public void setMarketCode(String marketCode) {
        this.marketCode = marketCode;
    }

    public String getProcessInputClass() {
        return processInputClass;
    }

    public void setProcessInputClass(String processInputClass) {
        this.processInputClass = processInputClass;
    }

    public String getFeedletClass() {
        return feedletClass;
    }

    public void setFeedletClass(String feedletClass) {
        this.feedletClass = feedletClass;
    }

    public List<SqlLoaderTable> getTables() {
        return tables;
    }

    public void setTables(List<SqlLoaderTable> tables) {
        this.tables = tables;
    }

    public String getJavaPackage() {
        return javaPackage;
    }

    public void setJavaPackage(String javaPackage) {
        this.javaPackage = javaPackage;
    }

    @Override
    public String toString() {
        return "SqlLoaderSchema{" +
                "marketCode='" + marketCode + '\'' +
                ", processInputClass='" + processInputClass + '\'' +
                ", feedletClass='" + feedletClass + '\'' +
                ", javaPackage='" + javaPackage + '\'' +
                ", tables=" + tables +
                '}';
    }
}
