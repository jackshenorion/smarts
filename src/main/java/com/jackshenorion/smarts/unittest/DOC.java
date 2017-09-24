package com.jackshenorion.smarts.unittest;

public class DOC {
    private static int staticCount = 0;

    public static int staticMethod() {
        return staticCount++;
    }

    public int normalMethod() {
        return staticCount++;
    }

}
