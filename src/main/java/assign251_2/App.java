package assign251_2;

import java.io.ObjectInputStream.GetField;
import java.util.List;

import javax.sound.midi.Sequencer.SyncMode;
import javax.swing.text.html.HTML;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.HTMLLayout;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;
import org.apache.log4j.spi.LoggingEvent;

public class App {
        
    public static void main(String[] args) throws Exception {

        //BasicConfigurator.configure();
        Logger testLogger = Logger.getLogger("MyTestLogger");
        MemAppender testAppender = MemAppender.getInstance();
        MemAppender testAppender2 = MemAppender.getInstance();
        MemAppender testAppender3 = MemAppender.getInstance();
        testLogger.addAppender(testAppender);

        for(int i = 0; i < 8; i++) {
            testLogger.info("Test info " + i);
        }

    }
}

