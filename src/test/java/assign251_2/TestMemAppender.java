package assign251_2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;
import org.apache.log4j.spi.LoggingEvent;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for MemAppender Class
 */
public class TestMemAppender {
    private Logger testLogger;
    private MemAppender testAppender;
    private MemAppender testAppender2;
    private LoggingEvent testLoggingEvent;

    @BeforeEach
    void initialiase() {
        testLogger = Logger.getLogger("MyTestLogger");
        testAppender = MemAppender.getInstance();
        testAppender2 = MemAppender.getInstance(new SimpleLayout());
        testLogger.addAppender(testAppender);
        testLoggingEvent = new LoggingEvent(null, testLogger, null, "test", null);
        
        // Tests below, Rely on max size being 5 and with 8 logs passed through.
        testAppender.setMaxSize(5);
        // Add 8 LoggingEvents
            for (int i = 0; i < 8; i++) {
                testLogger.info("Test info " + i);
            }
    }

    @Test
    void testMemAppenderSingleton() {
        assertEquals(testAppender, testAppender2, "two instances are different, singleton not working");
    }

    @Test
    void testAppendFunctionMaxSize() {
        assertEquals(5, testAppender.getCurrentLogs().size());
    }

    @Test
    void testDiscardedLogsCounter(){
        assertEquals(3, testAppender.getDiscardedLogCount());
    }

    /*
    * Tests that getEventStrings()
    *   returns an immutable list of LoggingEvents
    */
    @Test
    void testGetCurrentLogs() {
        try {
            List<LoggingEvent> testImuttableList = testAppender.getCurrentLogs();
            testImuttableList.add(testLoggingEvent);
            assertFalse(true, "UnsupportedOperationException should be thrown");
        } catch (UnsupportedOperationException e) {
            assertTrue(true, "UnsupportedOperationException was not caught");
        }
    }

    /*
    * Tests that getEventStrings()
    *   returns an immutable string list of LoggingEvents
    *   only if there is a layout present, otherwise exception thrown.
    */
    @Test
    void testGetEventStrings() {
        try {
            List<String> testImuttableStringList = testAppender.getEventStrings();
            testImuttableStringList.add(testLoggingEvent.toString());
            assertFalse(true, "UnsupportedOperationException should be thrown");
        } catch (UnsupportedOperationException e) {
            assertTrue(true, "UnsupportedOperationException was not caught");
        } catch (Exception e) {
            String exceptionMessage = e.toString();
            assertFalse(true, exceptionMessage);
        }
    }

    /*
    * Tests that printLogs()
    *   works only if there is a layout present, otherwise exception thrown.
    *   that it clears all events from list after console printing.
    */
    @Test
    void testPrintLogs(){
        try {
            testAppender.printLogs();
            List<LoggingEvent> testList = testAppender.getCurrentLogs();
            assertEquals(0, testList.size(), "There are still objects in the list");
        } catch (Exception e) {
            String exceptionMessage = e.toString();
            assertFalse(true, exceptionMessage);
        }
    }
    
    @AfterEach
    void nullifyAndDelete() throws Exception {
        if (testAppender != null) {
            testAppender.close();
        }
        LogManager.shutdown();
        testAppender = null;
        testAppender2 = null;
        testLoggingEvent = null;
        testLogger = null;
    }
}
