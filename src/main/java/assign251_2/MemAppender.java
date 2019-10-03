package assign251_2;

import java.util.List;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;

public class MemAppender extends AppenderSkeleton {
    private static MemAppender memAppender = null;
    public List loggingEvents;

    private MemAppender() {
        // loggingEvents = new List();
    }

    private MemAppender(List userDefinedList) {
        loggingEvents = userDefinedList;
    }

    public static MemAppender getInstance() {
        if (memAppender == null) {
            memAppender = new MemAppender();
        }
        return memAppender;
    }

    @Override
    public void close() {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean requiresLayout() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    protected void append(LoggingEvent event) {
        // TODO Auto-generated method stub

    }

    
}
