package com.jackshenorion.smarts.util.ini;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.ini4j.Ini;

import java.util.List;
import java.util.Set;

public class Ini4jReader implements IniReader {

    private Ini ini;

    public Ini4jReader(Ini ini) {
        this.ini = ini;
    }

    @Override
    public Set<String> getSections() {
        return ini.keySet();
    }

    @Override
    public boolean containsSection(String sectionName) {
        return ini.containsKey(sectionName);
    }

    @Override
    public Set<String> getKeys(String sectionName) {
        return ini.containsKey(sectionName) ? ini.get(sectionName).keySet() : Sets.newHashSet();
    }

    @Override
    public List<String> getValues(String sectionName, String key) {
        return ini.containsKey(sectionName) ? ini.get(sectionName).getAll(key) : Lists.newArrayList();
    }

    @Override
    public String getValue(String sectionName, String key) {
        return ini.containsKey(sectionName) ? ini.get(sectionName).get(key) : "";
    }

    @Override
    public String toJsonString() {
        return null;
    }
}
