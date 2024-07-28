package Exercise1_Design_Patterns.Behavioural.Observer_Pattern;

import java.util.logging.Logger;

public class FertilizerSystem implements Observer {
    private static final Logger logger = Logger.getLogger(FertilizerSystem.class.getName());

    @Override
    public void update() {
        logger.info("Fertilizer system activated due to low nutrients.");
    }
}
