package com.jackshenorion.smarts.util.ini;

import org.ini4j.Ini;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class IniUtil {
    public static IniReader read(File file) throws IOException {
        Ini ini = new Ini();
        ini.load(new FileReader(file));
        return new Ini4jReader(ini);
    }

}
