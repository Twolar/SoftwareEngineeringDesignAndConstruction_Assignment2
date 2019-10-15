package assign251_2;

import java.io.ObjectInputStream.GetField;
import java.nio.file.FileAlreadyExistsException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.sound.midi.Sequencer.SyncMode;
import javax.swing.text.html.HTML;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.HTMLLayout;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;
import org.apache.log4j.spi.LoggingEvent;

public class App {
        
    public static void main(String[] args) throws Exception {

        //BasicConfigurator.configure();
        Logger testLogger = Logger.getLogger("MyTestLogger");
        MemAppender testAppender = MemAppender.getInstance(new VelocityLayout("[$p] $c: $m"));
        Logger testFileLogger = Logger.getLogger("MyFileTestLogger");

        testFileLogger.addAppender(
            new FileAppender(new VelocityLayout("[$p] $c $d: $m"), "logs.txt")
            );

        testLogger.addAppender(testAppender);

        testLogger.info("Test info 1");
        testLogger.info("Test info 2");
        testLogger.info("Test info 3");
        testFileLogger.info("Test info 1");
        testFileLogger.info("Test info 2");
        testFileLogger.info("Test info 3");

        testAppender.printLogs();

        System.out.println(LocalDateTime.now().toString());
    }
}

