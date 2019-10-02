package assign251_2;


public class MemAppender 
{
    private static MemAppender memAppender = null;
    public String test;

    private MemAppender(){
        test = "test singleton";
    }
    
    public static MemAppender getMemAppenderInstance(){
        if (memAppender == null){
            memAppender = new MemAppender();
        }
        return memAppender;
    }

    
}
