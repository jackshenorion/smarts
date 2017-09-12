package com.jackshenorion.smarts.entity;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

public class JobConfig {
    private String name;
    private String className;
    private List<String> subJobNames = Lists.newArrayList();
    private Map<String, List<String>> properties = Maps.newHashMap();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<String> getSubJobNames() {
        return subJobNames;
    }

    public void setSubJobNames(List<String> subJobNames) {
        this.subJobNames = subJobNames;
    }

    public Map<String, List<String>> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, List<String>> properties) {
        this.properties = properties;
    }

    @Override
    public String toString() {
        return "JobConfig{" +
                "name='" + name + '\'' +
                ", className='" + className + '\'' +
                ", subJobNames=" + subJobNames +
                ", properties=" + properties +
                '}';
    }
}
