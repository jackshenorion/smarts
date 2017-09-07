package com.jackshenorion.smarts.util.mapper;


import com.google.common.base.Strings;

import java.util.HashMap;
import java.util.Map;

public class Translator {

    private Map<String, String> mapping = new HashMap<>();

    public Translator setMapping(Map<String, String> mapping) {
        this.mapping = mapping;
        return this;
    }

    public void translate(MapBinding mapBinding, Object msg) {
        String source = mapBinding.get(msg);
        String target = mapping.get(source);
        if (Strings.isNullOrEmpty(target)) {
            mapBinding.set(msg, source);
        } else {
            mapBinding.set(msg, target);
        }
    }
}
