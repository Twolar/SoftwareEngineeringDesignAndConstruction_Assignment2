package assign251_2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggingEvent;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit test for simple MemAppender & VelocityLayout
 */
public class AppTest 
{

    private List<LoggingEvent> loggingEventList;

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
    void testGetInstanceNoVariables(){
        Logger testLogger = Logger.getLogger("MyTestLogger");

        MemAppender testAppender = MemAppender.getInstance();
        testLogger.addAppender(testAppender);

        testLogger.info("Test info 1");
        testLogger.warn("Test warn 1");
        testLogger.debug("Test debug 1");
        testLogger.error("Test error 1");
        System.out.println("Hello World");
    }

    @Test
    void testGetInstanceWithLayout(){
        Logger testLogger = Logger.getLogger("MyTestLogger");

        MemAppender testAppender = MemAppender.getInstance();
        testLogger.addAppender(testAppender);

        testLogger.info("Test info 1");
        testLogger.warn("Test warn 1");
        testLogger.debug("Test debug 1");
        testLogger.error("Test error 1");
        System.out.println("Hello World");
    }
    @Test
    void testGetInstanceWithUserList(){
        Logger testLogger = Logger.getLogger("MyTestLogger");

        MemAppender testAppender = MemAppender.getInstance();
        testLogger.addAppender(testAppender);

        testLogger.info("Test info 1");
        testLogger.warn("Test warn 1");
        testLogger.debug("Test debug 1");
        testLogger.error("Test error 1");
        System.out.println("Hello World");
    }
    @Test
    void testGetInstanceWithLayoutAndUserList(){
        Logger testLogger = Logger.getLogger("MyTestLogger");

        MemAppender testAppender = MemAppender.getInstance();
        testLogger.addAppender(testAppender);

        testLogger.info("Test info 1");
        testLogger.warn("Test warn 1");
        testLogger.debug("Test debug 1");
        testLogger.error("Test error 1");
        System.out.println("Hello World");
    }

    @AfterEach
    void nullify(){
    }
    
}
