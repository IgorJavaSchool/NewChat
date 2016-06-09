package yanevskyy;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import static org.junit.Assert.*;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by MM on 05.06.2016.
 */
public class GetMessageTest {
    @Rule
    public final TextFromStandardInputStream systemInMock
            = emptyStandardInputStream();


    @Test
    public void readMessage() throws Exception {
        GetMessage getMessage = new GetMessage();
        systemInMock.provideLines("Hello");
        String result = getMessage.readMessage();
        assertEquals("Hello", result);
    }

}