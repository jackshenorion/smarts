package com.jackshenorion.smarts.util.ini;

import java.util.List;
import java.util.Set;

public interface IniReader {
    public Set<String> getSections();
    public boolean containsSection(String sectionName);
    public Set<String> getKeys(String sectionName);
    public List<String> getValues(String sectionName, String key);
    public String getValue(String sectionName, String key);
    public String toJsonString();

}
