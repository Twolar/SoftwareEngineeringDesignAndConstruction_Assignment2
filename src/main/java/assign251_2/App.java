package assign251_2;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;

public class App {

	public static void main(String[] args) {
        BasicConfigurator.configure();
        MemAppender testAppender = MemAppender.getInstance();
        
        testAppender.setLayout(new SimpleLayout());

        Logger testLogger = Logger.getLogger("MyTestLogger");

        testLogger.addAppender(testAppender);

        testLogger.info("Test info 1");
        testLogger.warn("Test warn 1");
        testLogger.debug("Test debug 1");
        testLogger.error("Test error 1");

        System.out.println("Hello World");

        
	}

}
