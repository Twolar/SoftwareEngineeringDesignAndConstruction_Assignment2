package assign251_2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;
import org.apache.log4j.spi.LoggingEvent;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit test for simple MemAppender & VelocityLayout
 */
public class AppTest 
{

    @BeforeEach
    void initialiase() {
        BasicConfigurator.configure();
        
    }

    @Test
    void testMemAppenderSingleton() {
        MemAppender testInstance1 = MemAppender.getInstance();
        MemAppender testInstance2 = MemAppender.getInstance();

        assertEquals(testInstance1, testInstance2, "two instances are different, singleton not working");
    }

    @Test
    void testAppendFunction(){
        Logger testLogger = Logger.getLogger("MyTestLogger");
        MemAppender testAppender = MemAppender.getInstance(new SimpleLayout());
        testLogger.addAppender(testAppender);

        for(int i = 0; i < 8; i++) {
            testLogger.info("Test info " + i);
        }
        assertEquals(testAppender.getDiscardedLogCount(), 3, "Check max size initialisation is 5, if good, check append function");
    }

    @Test
    void testGetCurrentLogs(){
        Logger testLogger = Logger.getLogger("MyTestLogger");
        MemAppender testAppender = MemAppender.getInstance(new SimpleLayout());
        testLogger.addAppender(testAppender);

        LoggingEvent testLoggingEvent = new LoggingEvent(null, testLogger, null, "test", null);

        for(int i = 0; i < 8; i++) {
            testLogger.info("Test info " + i);
        }

        List<LoggingEvent> testList = testAppender.getCurrentLogs();
        
        // check if list is immutable 
        
        testList.add(testLoggingEvent);
    }

    @AfterEach
    void nullify(){
    }
    
}
