import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import specs.AuthConfig;
import specs.ConfigReader;
import specs.ProjectConfiguration;
import specs.web.WebDriverConfig;

public class TestBase {

    private static final WebDriverConfig webDriverConfig = ConfigReader.INSTANCE.readWebConfig();
    private static final AuthConfig authConfig = ConfigReader.INSTANCE.readAuthConfig();


    @BeforeAll
    public static void setUp() {
        ProjectConfiguration projectConfiguration = new ProjectConfiguration(webDriverConfig, authConfig);
        projectConfiguration.apiConfig();
        projectConfiguration.webConfig();
    }

    @BeforeEach
    void beforeEach() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterEach
    public void tearDown() {
        if (Configuration.browser.equals("firefox")) {
            byte[] lastScreenshots = Attach.screenshotAs("Last screenshot");
            Attach.pageSource();
            Attach.addVideo();
        } else {
            Attach.screenshotAs("Last screenshot");
            Attach.pageSource();
            Attach.browserConsoleLogs();
            Attach.addVideo();
        }
        Selenide.closeWebDriver();
    }
}
