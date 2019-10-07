package assign251_2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

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
    void initialiaseLoggingEvents() {
    }

    @Test
    void testMemAppenderSingleton() {
        MemAppender testInstance1 = MemAppender.getInstance();
        MemAppender testInstance2 = MemAppender.getInstance();

        assertEquals(testInstance1, testInstance2, "two instances are different, singleton not working");
    }

    @Test
    void testMemAppenderFunctionality(){
        
    }

    @AfterEach
    void nullifyLoggingEvents(){
    }
    
}
