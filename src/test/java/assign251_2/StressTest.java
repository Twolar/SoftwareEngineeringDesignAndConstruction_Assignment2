package assign251_2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import org.apache.log4j.FileAppender;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggingEvent;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Stress tests for MemAppender & VelocityLayout
 */
public class StressTest {
    private long numOfLogEvents = 100000;
    private String velocityLayoutString = "[$p] $c $d: $m$n";

    private Logger testMemAppenderLogger = null;
    private MemAppender testMemAppender = null;

    private Logger testFileAppenderLogger = null;
    private String fileAppenderOutputFileName = "stressTestFileAppenderOutputLogs.txt";
    private File stressTestFileAppenderFile = null;

    @BeforeEach
    void initialisation() throws IOException {

        testMemAppenderLogger = Logger.getLogger("MyTestLogger");

        // FileAppender & Logger Init
        stressTestFileAppenderFile = new File(fileAppenderOutputFileName);
        testFileAppenderLogger = Logger.getLogger("MyTestLogger");
        testFileAppenderLogger.addAppender(
            new FileAppender(new VelocityLayout(velocityLayoutString), fileAppenderOutputFileName)
        );
        
    }

    @Test
    void stressTestArrayListMemAppender() {
        testMemAppender = MemAppender.getInstance(new VelocityLayout(velocityLayoutString));
        testMemAppenderLogger.addAppender(testMemAppender);

        for (int i = 0; i < numOfLogEvents; i++) {
            testMemAppenderLogger.info("Test INFO " + i);
        }
    }

    @Test
    void stressTestLinkedListMemAppender() {
        testMemAppender = MemAppender.getInstance(new VelocityLayout(velocityLayoutString), new LinkedList<LoggingEvent>());
        testMemAppenderLogger.addAppender(testMemAppender);

        for (int i = 0; i < numOfLogEvents; i++) {
            testMemAppenderLogger.info("Test INFO " + i);
        }
    }

    @Test
    void stressTestFileAppender() {
        for (int i = 0; i < numOfLogEvents; i++) {
            testFileAppenderLogger.info("Test INFO " + i);
        }
    }

    @AfterEach
    void nullifyAndDelete() throws FileNotFoundException {
        // Clear output log file contents and nullify.
        PrintWriter writer = new PrintWriter(stressTestFileAppenderFile);
        writer.print("");
        writer.close();
        
        numOfLogEvents = 0;

        testMemAppenderLogger = null;
        testFileAppenderLogger = null;

        stressTestFileAppenderFile = null;
        if (testMemAppender != null) {
            testMemAppender.close();
        }
        LogManager.shutdown();
    }
    

}