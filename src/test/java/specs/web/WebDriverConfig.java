package specs.web;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.FIRST)
@Config.Sources({
        "classpath:properties/${env}.properties",
        "classpath:properties/local.properties"
})
public interface WebDriverConfig extends Config{

    @DefaultValue("chrome")
    Browser browser();

    @DefaultValue("124.0")
    String browserVersion();

    @DefaultValue("1920x1080")
    String browserSize();

    String baseUrl();

    boolean isRemote();

    String remoteUrl();
}
