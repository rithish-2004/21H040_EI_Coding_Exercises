package Exercise1_Design_Patterns.Behavioural.Observer_Pattern;

import java.util.logging.Logger;

public class IrrigationSystem implements Observer {
    private static final Logger logger = Logger.getLogger(IrrigationSystem.class.getName());

    @Override
    public void update() {
        logger.info("Irrigation system activated due to dry soil.");
    }
}
