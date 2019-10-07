package assign251_2;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Layout;
import org.apache.log4j.spi.LoggingEvent;

public class MemAppender extends AppenderSkeleton {

    private static MemAppender memAppender = null;
    private static int maxSize;
    private static List<LoggingEvent> loggingEvents = null;
    private static Layout memAppenderLayout = null;

    private MemAppender() {
        if (loggingEvents == null) {
            loggingEvents = new ArrayList<LoggingEvent>();
        }
        if (memAppenderLayout != null) { // Come back to double check that there is another way of assigning this!
            setLayout(memAppenderLayout);
        }
    }

    public static MemAppender getInstance() {
        if (memAppender == null) {
            memAppender = new MemAppender();
        }
        return memAppender;
    }

    public static MemAppender getInstance(Layout layout) {
        memAppenderLayout = layout;
        memAppender = getInstance();
        return memAppender;
    }

    public static MemAppender getInstance(List<LoggingEvent> events){
        loggingEvents = events;
        memAppender = getInstance();
        return memAppender;
    }

    public static MemAppender getInstance(Layout layout, List<LoggingEvent> events){
        getInstance(layout);
        getInstance(events);
        return memAppender;
    }

    @Override
    public void close() {
    }

    @Override
    public boolean requiresLayout() {
        return true;
    }

    @Override
    protected void append(LoggingEvent event) {
        loggingEvents.add(event);
    }

}
