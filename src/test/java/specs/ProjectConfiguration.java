package specs;

import com.codeborne.selenide.Configuration;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.openqa.selenium.remote.DesiredCapabilities;
import specs.web.WebDriverConfig;

public class ProjectConfiguration {
    private final WebDriverConfig webDriverConfig;
    private final AuthConfig authConfig;

    public ProjectConfiguration(WebDriverConfig webDriverConfig, AuthConfig authConfig) {
        this.webDriverConfig = webDriverConfig;
        this.authConfig = authConfig;
    }

    public void apiConfig() {
        RestAssured.baseURI = webDriverConfig.baseUrl();
        RestAssured.defaultParser = Parser.JSON;
    }

    public void webConfig(){
        Configuration.baseUrl = webDriverConfig.baseUrl();
        Configuration.browser = webDriverConfig.browser().toString();
        Configuration.browserVersion = webDriverConfig.browserVersion();
        Configuration.browserSize = webDriverConfig.browserSize();
        Configuration.timeout = 30000;
        if (webDriverConfig.isRemote()) {
            Configuration.remote = String.format("https://%s:%s@%s/wd/hub",
                    authConfig.selenoidUser(), authConfig.selenoidPassword(), webDriverConfig.remoteUrl());
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("enableVNC", true);
            capabilities.setCapability("enableVideo", true);
        }
    }
}
