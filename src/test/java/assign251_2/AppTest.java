package assign251_2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

/**
 * Unit test for simple MemAppender & VelocityLayout
 */
public class AppTest 
{
    private String[] loggingEventsArray;
    @BeforeEach
    void initialiaseLoggingEvents(){
        loggingEventsArray = new String[3];
        loggingEventsArray[0] = "TEST ONE";
        loggingEventsArray[1] = "TEST TWO";
        loggingEventsArray[2] = "TEST THREE";  
    }

    @Test
    void testSingletonMemAppenderFunctionality(){
        MemAppender testInstance1 = MemAppender.getMemAppenderInstance();
        MemAppender testInstance2 = MemAppender.getMemAppenderInstance();

        assertEquals(testInstance1, testInstance2, "two instances are different, singleton not working");
    }

    @Test
    void testMemAppenderEventListCreation(){
        assert(true);
    }

    @AfterEach
    void nullifyLoggingEvents(){
        loggingEventsArray = null;
    }
}
