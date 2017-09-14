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

    public SqlLoaderSchema setMarketCode(String marketCode) {
        this.marketCode = marketCode;
        return this;
    }

    public String getProcessInputClass() {
        return processInputClass;
    }

    public SqlLoaderSchema setProcessInputClass(String processInputClass) {
        this.processInputClass = processInputClass;
        return this;
    }

    public String getFeedletClass() {
        return feedletClass;
    }

    public SqlLoaderSchema setFeedletClass(String feedletClass) {
        this.feedletClass = feedletClass;
        return this;
    }

    public String getJavaPackage() {
        return javaPackage;
    }

    public SqlLoaderSchema setJavaPackage(String javaPackage) {
        this.javaPackage = javaPackage;
        return this;
    }

    public List<SqlLoaderTable> getTables() {
        return tables;
    }

    public SqlLoaderSchema setTables(List<SqlLoaderTable> tables) {
        this.tables = tables;
        return this;
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
