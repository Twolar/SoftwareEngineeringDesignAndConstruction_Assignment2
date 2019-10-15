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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for VelocityLayout Class
 */
public class TestVelocityLayout {

    private File testVelocityLayoutLogFile = null;
    private String velocityLayoutPattern = "[$p] $c $d: $m";
    private Logger testLogger = null;
    private String fileAppenderOutputFileName = "testVelocityLayoutOutputLogs.txt"; 

    @BeforeEach
    void initialise() {
        testVelocityLayoutLogFile = new File(fileAppenderOutputFileName);
        testLogger = Logger.getLogger("MyTestLogger");
    }

    @Test
    void testVelocityLayoutWithDifferentAppenders() throws Exception {
        MemAppender testMemAppender = MemAppender.getInstance(new VelocityLayout(velocityLayoutPattern));
        testLogger.addAppender(
            new FileAppender(new VelocityLayout(velocityLayoutPattern), fileAppenderOutputFileName)
            );

        testLogger.addAppender(testMemAppender);

        testLogger.info("Test info 1");

        List<String> memAppenderEventStringList = testMemAppender.getEventStrings();
        String memAppenderEventString = memAppenderEventStringList.get(0);

        Path fileAppenderOutputFilePath = Paths.get(fileAppenderOutputFileName);
        List<String> fileAppenderEventStringList = Files.readAllLines(fileAppenderOutputFilePath);
        String fileAppenderEventString = fileAppenderEventStringList.get(0);
        
        assertNotEquals(null, memAppenderEventString, "memAppenderEventString is null");
        assertNotEquals(null, fileAppenderEventString, "fileAppenderEventString is null");
        assertTrue(memAppenderEventString.equals(fileAppenderEventString), "Logging Event FileAppender & MemAppender Strings don't match");
    } 

    @AfterEach
    void nullifyAndDelete () throws FileNotFoundException {
        // Clear output log file contents and nullify.
        PrintWriter writer = new PrintWriter(testVelocityLayoutLogFile);
        writer.print("");
        writer.close();

        testVelocityLayoutLogFile = null;
        testLogger = null;
    }
    
}