package assign251_2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.spi.LoggingEvent;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * Stress tests for MemAppender & VelocityLayout
 */
public class StressTest {
    private int numOfLogEvents = 40000;
    private String velocityLayoutString = "[$p] $c $d: $m$n";
    private String patternLayoutString = "[%p] %c %d: %m%n";

    private Logger testMemAppenderLogger = null;
    private MemAppender testMemAppender = null;

    private Logger testFileAppenderLogger = null;
    private String fileAppenderOutputFileName = "stressTestFileAppenderOutputLogs.txt";
    private File stressTestFileAppenderFile = null;

    private Logger testConsoleAppenderLoggger = null;

    @BeforeEach
    void initialisation() throws Exception {

        testMemAppenderLogger = Logger.getLogger("TestLogger");


        // FileAppender & Logger Init
        stressTestFileAppenderFile = new File(fileAppenderOutputFileName);
        testFileAppenderLogger = Logger.getLogger("TestLogger");

        testConsoleAppenderLoggger = Logger.getLogger(" TestLogger");
        // Thread.sleep(5000); // USE THIS WHEN MEASURING ON VISUALVM
    }


    @ParameterizedTest
    @ValueSource(ints = {10000, 30000, 50000, 70000})
    void stressTestArrayListMemAppenderWithVelocityLayout(int maxSize) throws Exception {
    	
        testMemAppender = MemAppender.getInstance(new VelocityLayout(velocityLayoutString));
        testMemAppenderLogger.addAppender(testMemAppender);

        testMemAppender.setMaxSize(maxSize);
        
        for (int i = 0; i < numOfLogEvents; i++) {
            testMemAppenderLogger.info("Test INFO " + i);
        }
        testMemAppender.printLogs();
        
    }


    @ParameterizedTest
    @ValueSource(ints = {10000, 30000, 50000, 70000})
    void stressTestLinkedListMemAppenderWithVelocityLayout(int maxSize) throws Exception {
        
        testMemAppender = MemAppender.getInstance(new VelocityLayout(velocityLayoutString), new LinkedList<LoggingEvent>());
        testMemAppenderLogger.addAppender(testMemAppender);

        testMemAppender.setMaxSize(maxSize);

        for (int i = 0; i < numOfLogEvents; i++) {
            testMemAppenderLogger.info("Test INFO " + i);
        }
        testMemAppender.printLogs();
    }


    @Test
    void stressTestFileAppenderWithVelocityLayout() throws Exception {
    	
        testFileAppenderLogger.addAppender(
            new FileAppender(new VelocityLayout(velocityLayoutString), fileAppenderOutputFileName)
        );
        
        for (int i = 0; i < numOfLogEvents; i++) {
            testFileAppenderLogger.info("Test INFO " + i);
        }
        
    }


    @Test
    void stressTestConsoleAppenderWithVelocityLayout() {
        testConsoleAppenderLoggger.addAppender(new ConsoleAppender(new VelocityLayout(velocityLayoutString)));

        for (int i = 0; i < numOfLogEvents; i++) {
            testConsoleAppenderLoggger.info("Test INFO " + i);
        }
    }


    @ParameterizedTest
    @ValueSource(ints = {10000, 30000, 50000, 70000})
    void stressTestArrayListMemAppenderWithPatternLayout(int maxSize) throws Exception {
        testMemAppender = MemAppender.getInstance(new PatternLayout(patternLayoutString));
        testMemAppenderLogger.addAppender(testMemAppender);

        testMemAppender.setMaxSize(maxSize);

        for (int i = 0; i < numOfLogEvents; i++) {
            testMemAppenderLogger.info("Test INFO " + i);
        }
        testMemAppender.printLogs();
    }


    @ParameterizedTest
    @ValueSource(ints = {10000, 30000, 50000, 70000})
    void stressTestLinkedListMemAppenderWithPatternLayout(int maxSize) throws Exception {
        testMemAppender = MemAppender.getInstance(new PatternLayout(patternLayoutString), new LinkedList<LoggingEvent>());
        testMemAppenderLogger.addAppender(testMemAppender);

        testMemAppender.setMaxSize(maxSize);

        for (int i = 0; i < numOfLogEvents; i++) {
            testMemAppenderLogger.info("Test INFO " + i);
        }
        testMemAppender.printLogs();
    }


    @Test
    void stressTestFileAppenderWithPatternLayout() throws IOException {
        testFileAppenderLogger.addAppender(
            new FileAppender(new PatternLayout(patternLayoutString), fileAppenderOutputFileName)
        );
        
        for (int i = 0; i < numOfLogEvents; i++) {
            testFileAppenderLogger.info("Test INFO " + i);
        }
    }


    @Test
    void stressTestConsoleAppenderWithPatternLayout() {
        testConsoleAppenderLoggger.addAppender(new ConsoleAppender(new PatternLayout(patternLayoutString)));

        for (int i = 0; i < numOfLogEvents; i++) {
            testConsoleAppenderLoggger.info("Test INFO " + i);
        }
    }


    @AfterEach
    void nullifyAndDelete() throws Exception {
    	// Thread.sleep(5000); // USE THIS WHEN MEASURING ON VISUALVM
        // Clear output log file contents and nullify.
        PrintWriter writer = new PrintWriter(stressTestFileAppenderFile);
        writer.print("");
        writer.close();

        testMemAppenderLogger = null;
        testFileAppenderLogger = null;
        testConsoleAppenderLoggger = null;

        stressTestFileAppenderFile = null;
        if (testMemAppender != null) {
            testMemAppender.close();
        }
        LogManager.shutdown();
    }
    

}