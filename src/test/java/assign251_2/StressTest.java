package assign251_2;

import java.util.ArrayList;
import java.util.LinkedList;

import org.apache.log4j.FileAppender;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Stress tests for MemAppender & VelocityLayout
 */
public class StressTest {
    private long numOfLogEvents = 0;
    private LinkedList linkedList = null;
    private ArrayList arrayList = null;
    private MemAppender testArrayListMemAppender = null;
    private Logger testLogger = null;


    @BeforeEach
    void initialisation() {
        testLogger = Logger.getLogger("MyTestLogger");
        testArrayListMemAppender = MemAppender.getInstance(new VelocityLayout("[$p] $c $d: $m$n"));
        testLogger.addAppender(testArrayListMemAppender);
    }
    
    @Test
    void stressTest() {
        numOfLogEvents = 1000;

        for(int i = 0; i < numOfLogEvents; i++){
            testLogger.info("Test INFO " + i);
        }

        


    }

    @AfterEach
    void nullifyAndDelete() {
        numOfLogEvents = 0;
        testLogger = null;
        testArrayListMemAppender.close();
        LogManager.shutdown();
    }

}