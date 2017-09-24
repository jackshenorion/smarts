package com.jackshenorion.smarts.unittest;

public class ClassUnderTest {


    private int privateCount = 0;

    private int privateMethodWithoutArg() {
        System.out.println("privateMethodWithoutArg get called");
        return privateCount++;
    }

    private int privateMethodWithArg(int offset) {
        System.out.println("privateMethodWithArg get called");
        return privateCount++ + offset;
    }

    private int privateMethodWithException() {
        System.out.println("privateMethodWithException get called");
        throw new RuntimeException("privateMethodWithArg throws an Exception");
    }

    final int finalMethod() {
        return privateCount++;
    }

    int normalMethod() {
        return privateCount++;
    }

    public String callSelfPrivateMethod() {
        return "Result = " + privateMethodWithoutArg();
    }

    public String callSelfPrivateMethod(int offset) {
        return "Result = " + privateMethodWithArg(offset);
    }

    public String callSelfPrivateMethodWithException() {
        return "Result = " + privateMethodWithException();
    }

    public String callDOCStaticMethod() {
        return "Result = " + DOC.staticMethod();
    }

    public String callDOCNormalMethod() {
        return "Result = " + new DOC().normalMethod();
    }

    public String callSelfFinalMethod() {
        return "Result = " + finalMethod();
    }

    public String callSelfNormalMethod() {
        return "Result = " + normalMethod();
    }

}
