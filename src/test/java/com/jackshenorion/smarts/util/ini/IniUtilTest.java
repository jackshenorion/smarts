package com.jackshenorion.smarts.util.ini;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class IniUtilTest {
    @Test
    public void read() throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        System.out.println(classLoader.getResource("com/jackshenorion/smarts/util/ini/test1.ini").getFile());
        File file = new File(classLoader.getResource("com/jackshenorion/smarts/util/ini/test1.ini").getFile());
        IniReader iniReader = IniUtil.read(file);
        iniReader.getSections().forEach(
                sectionName -> {
                    System.out.println("[" + sectionName + "]");
                    iniReader.getKeys(sectionName).forEach(key -> {
                        iniReader.getValues(sectionName, key).forEach(value -> {
                            System.out.println(key + "=" + value);
                        });
                    });
                }
        );
    }

}