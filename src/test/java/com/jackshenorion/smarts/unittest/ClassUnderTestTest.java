package com.jackshenorion.smarts.unittest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.powermock.api.mockito.PowerMockito.*;

@RunWith(PowerMockRunner.class) // 1. must use PowerMockRunner to run test if you need to mock Static method
public class ClassUnderTestTest {
    @Before
    public void setUp() throws Exception {
    }

    @Test
    @PrepareForTest(DOC.class) // 2. must use PrepareForTest to tell PowerMock to meddle the creation of DOC
    public void callStaticMethod() throws Exception {

        mockStatic(DOC.class);
        when(DOC.staticMethod()).thenReturn(999);

        assertEquals("Result = 999", new ClassUnderTest().callDOCStaticMethod());
    }

    @Test
    // prepare the class because the private method is in this class
    @PrepareForTest(ClassUnderTest.class)
    public void callPrivateMethod() throws Exception {
        ClassUnderTest underTest = spy(new ClassUnderTest()); // spy is mandatory

        PowerMockito.when(underTest,"privateMethodWithoutArg").thenReturn(888);

        assertEquals("Result = 888", underTest.callSelfPrivateMethod());
        verifyPrivate(underTest, times(1)).invoke("privateMethodWithoutArg");
    }

    @Test
    @PrepareForTest(ClassUnderTest.class)
    public void mockPrivateMethodWithoutReallyCallIt() throws Exception {
        ClassUnderTest underTest = spy(new ClassUnderTest()); // spy is mandatory

        PowerMockito.doReturn(888).when(underTest, "privateMethodWithException");

        assertEquals("Result = 888", underTest.callSelfPrivateMethodWithException());
        verifyPrivate(underTest, times(1)).invoke("privateMethodWithException");
    }

    @Test(expected = Exception.class)
    @PrepareForTest(ClassUnderTest.class)
    public void mockPrivateMethodWithReallyCallIt() throws Exception {
        ClassUnderTest underTest = spy(new ClassUnderTest()); // spy is mandatory

        PowerMockito.when(underTest,"privateMethodWithException").thenReturn(888);

        underTest.callSelfPrivateMethodWithException(); // would throw an exception because private method is really called even it's mocked
    }

    @Test
    @PrepareForTest(ClassUnderTest.class)
    public void callPrivateMethodWithArg() throws Exception {
        ClassUnderTest underTest = spy(new ClassUnderTest());

        PowerMockito.when(underTest,"privateMethodWithArg", 10_000).thenReturn(10888);

        assertEquals("Result = 10888", underTest.callSelfPrivateMethod(10_000));
        verifyPrivate(underTest, times(1)).invoke("privateMethodWithArg", 10_000);
    }

    @Test
    public void callNormalMethodUsingNormalMock() throws Exception {
        ClassUnderTest underTest = spy(new ClassUnderTest());

        when(underTest.normalMethod()).thenReturn(666);

        assertEquals("Result = 666", underTest.callSelfNormalMethod());
        Mockito.verify(underTest, times(1)).normalMethod();
    }

    @Test(expected = Exception.class)
    public void callFinalMethodNotWorkUsingNormalMock() throws Exception {

        ClassUnderTest underTest = spy(new ClassUnderTest());

        when(underTest.finalMethod()).thenReturn(777);

        underTest.callSelfFinalMethod(); // will throw an exception because finalMethod can not be not mocked
    }

    @Test
    @PrepareForTest(ClassUnderTest.class) // use PrepareForTest to let PowerMock take effect
    public void callFinalMethodWorkUsingPrivateMock() throws Exception {

        ClassUnderTest underTest = spy(new ClassUnderTest());

        when(underTest.finalMethod()).thenReturn(777);

        assertEquals("Result = 777", underTest.callSelfFinalMethod());
    }

    @Test
    @PrepareForTest(ClassUnderTest.class) // use PrepareForTest to let PowerMock take effect
    public void canMockNewConstructorResult() throws Exception {

        DOC mockDOC = mock(DOC.class);

        whenNew(DOC.class).withNoArguments().thenReturn(mockDOC);

        when(mockDOC.normalMethod()).thenReturn(555);

        assertEquals("Result = 555", new ClassUnderTest().callDOCNormalMethod());
    }
}