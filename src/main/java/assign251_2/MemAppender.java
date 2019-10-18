package assign251_2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Layout;
import org.apache.log4j.spi.LoggingEvent;

public class MemAppender extends AppenderSkeleton {

    private int maxSize = 100;
    private static MemAppender memAppender = null;
    private long discardedLogCount = 0;
    private static List<LoggingEvent> loggingEvents = null;
    private static Layout memAppenderLayout = null;

    private MemAppender() {
        if (loggingEvents == null) {
            loggingEvents = new ArrayList<LoggingEvent>();
        }
        setLayout(memAppenderLayout);
    }

    public static MemAppender getInstance() {
        if (memAppender == null) {
            memAppender = new MemAppender();
        }
        return memAppender;
    }

    public static MemAppender getInstance(Layout layout) {
        memAppenderLayout = layout;
        getInstance();
        return memAppender;
    }

    public static MemAppender getInstance(List<LoggingEvent> events){
        loggingEvents = events;
        getInstance();
        return memAppender;
    }

    public static MemAppender getInstance(Layout layout, List<LoggingEvent> events){
        getInstance(layout);
        getInstance(events);
        return memAppender;
    }

    public void setMaxSize(int newMaxSize) {
        maxSize = newMaxSize;
    }

    @Override
    public void close() {
        discardedLogCount = 0;
        if(loggingEvents.size() != 0){
            loggingEvents.clear();
        }
    }

    @Override
    public boolean requiresLayout() {
        return true;
    }

    @Override
    protected void append(LoggingEvent event) {
        if((loggingEvents.size() == maxSize) && (loggingEvents.size() != 0) && (maxSize != 0)){
            loggingEvents.remove(0);
            discardedLogCount++;
            loggingEvents.add(event);
        } else {
            loggingEvents.add(event);
        }
    }

    public List<LoggingEvent> getCurrentLogs() {
        List<LoggingEvent> immutableLoggingEventList = 
            Collections.unmodifiableList(loggingEvents);

        return immutableLoggingEventList;
    }

    public List<String> getEventStrings() throws Exception {
        List<String> formattedLoggingEventStringList = new ArrayList<String>();
        
        if(memAppenderLayout != null){
            // Add formatted events to StringList
            for (LoggingEvent event : loggingEvents){
                formattedLoggingEventStringList.add(memAppenderLayout.format(event));
            }
        } else {
            throw new Exception(
                "Cannot perform action that includes formatting if no layout has been supplied"
            );
        }

        List<String> immutableFormattedLoggingEventStringList = 
            Collections.unmodifiableList(formattedLoggingEventStringList);
        return immutableFormattedLoggingEventStringList;
    }

    public void printLogs() throws Exception {
        for (String event : getEventStrings()) {
            System.out.println(event);
        }

        loggingEvents.clear();
    }

    public long getDiscardedLogCount(){
        return discardedLogCount;
    }

}
