package rootenginear.setspawner;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SetSpawner implements ModInitializer {
    public static final String MOD_ID = "setspawner";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("SetSpawner initialized.");
    }
}
