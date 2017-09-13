package com.jackshenorion.smarts.generator.sqlloader;

import java.util.List;

public class SqlLoaderPojo {
    private String className;
    private List<SqlLoaderPojoProperty> props;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<SqlLoaderPojoProperty> getProps() {
        return props;
    }

    public void setProps(List<SqlLoaderPojoProperty> props) {
        this.props = props;
    }

    @Override
    public String toString() {
        return "SqlLoaderPojo{" +
                "className='" + className + '\'' +
                ", props=" + props +
                '}';
    }
}
