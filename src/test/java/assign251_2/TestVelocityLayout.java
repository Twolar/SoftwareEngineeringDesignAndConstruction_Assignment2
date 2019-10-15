package assign251_2;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for VelocityLayout Class
 */
public class TestVelocityLayout {

    Path fileAppenderOutputPath = null;
    File testVelocityLayoutLogFile = null;

    @BeforeEach
    void initialise() {
        testVelocityLayoutLogFile = new File("testVelocityLayoutOutputLogs.txt");
    }

    @Test
    void testVelocityLayoutOutput() throws Exception {
        List<String> memAppenderEventStringList = null;
        List<String> fileAppenderEventStringList = null;
        String memAppenderEventString = null;
        String fileAppenderEventString = null;
        Logger testLogger = Logger.getLogger("MyTestLogger");
        MemAppender testAppender = MemAppender.getInstance(new VelocityLayout("[$p] $c $d: $m"));

        testLogger.addAppender(
            new FileAppender(new VelocityLayout("[$p] $c $d: $m"), "testVelocityLayoutOutputLogs.txt")
            );

        testLogger.addAppender(testAppender);

        testLogger.info("Test info 1");

        memAppenderEventStringList = testAppender.getEventStrings();
        memAppenderEventString = memAppenderEventStringList.get(0);

        Path fileAppenderOutputPath = Paths.get("testVelocityLayoutOutputLogs.txt");
        fileAppenderEventStringList = Files.readAllLines(fileAppenderOutputPath);
        fileAppenderEventString = fileAppenderEventStringList.get(0);

        assertNotEquals(null, memAppenderEventString, "memAppenderEventString is null");
        assertNotEquals(null, fileAppenderEventString, "fileAppenderEventString is null");
        assertTrue(memAppenderEventString.equals(fileAppenderEventString), "Logging Event Strings don't match");
    } 

    @AfterEach
    void nullifyAndDelete () throws FileNotFoundException {
        // Clear output log file contents.
        PrintWriter writer = new PrintWriter(testVelocityLayoutLogFile);
        writer.print("");
        writer.close();
    }
    
}