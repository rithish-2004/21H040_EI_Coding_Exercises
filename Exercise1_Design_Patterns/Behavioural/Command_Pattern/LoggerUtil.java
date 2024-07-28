package Exercise1_Design_Patterns.Behavioural.Command_Pattern;
import java.util.logging.Logger;

public class LoggerUtil {
    public static Logger getLogger() {
        return Logger.getLogger(LoggerUtil.class.getName());
    }
}