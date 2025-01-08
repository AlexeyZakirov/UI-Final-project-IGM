package specs;

import org.aeonbits.owner.ConfigFactory;
import specs.web.WebDriverConfig;

public enum ConfigReader {
    INSTANCE;

    private static final WebDriverConfig webConfig =
            ConfigFactory.create(
                    WebDriverConfig.class,
                    System.getProperties()
            );

    private static final AuthConfig authConfig =
            ConfigFactory.create(
                    AuthConfig.class,
                    System.getProperties()
            );

    public WebDriverConfig readWebConfig() {
        return webConfig;
    }
    public AuthConfig readAuthConfig(){
        return authConfig;
    }
}
