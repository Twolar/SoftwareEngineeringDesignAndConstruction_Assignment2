package assign251_2;

import java.io.StringWriter;
import java.util.Date;

import org.apache.log4j.Layout;
import org.apache.log4j.spi.LoggingEvent;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

public class VelocityLayout extends Layout
{
    private String layoutTemplate = null;

    public VelocityLayout(){
    }

    public VelocityLayout(String desiredLayout){
        layoutTemplate = desiredLayout;
    }

    public void setVelocityLayout(String desiredLayout){
        layoutTemplate = desiredLayout;
    }
    
    public String format(LoggingEvent event){
        VelocityContext context = new VelocityContext();

        StringWriter sWriter = new StringWriter();

        // Convert event time stamp from milliseconds to a date
        Date eventDate = new Date(event.getTimeStamp());

        context.put("d", eventDate.toString()); // Date
        context.put("c", event.getLoggerName()); // Category
        context.put("m", event.getMessage()); // Message
        context.put("p", event.getLevel()); // Priority
        context.put("t", event.getThreadName()); // Thread
        context.put("n", System.lineSeparator()); // Line Separator

        Velocity.evaluate(context, sWriter, "VelocityLayout-Format", layoutTemplate);

        return sWriter.toString();
    }

    @Override
    public void activateOptions() {
    }

    @Override
    public boolean ignoresThrowable() {
        return false;
    }
}
