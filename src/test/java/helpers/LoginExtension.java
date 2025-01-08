package helpers;

import api.authorization.AuthorizationApi;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class LoginExtension implements BeforeEachCallback {

    @Step("Добавить token в браузер")
    @Override
    public void beforeEach(ExtensionContext extensionContext) throws Exception {
        Response authResponse = AuthorizationApi.getAuthorizationResponse();
        String accessToken = extractAccessToken(authResponse.getHeader("set-cookie"));

        open("/images/igmFavicon.ico");
        getWebDriver().manage().addCookie(new Cookie("access-token", accessToken));
    }

    private static String extractAccessToken(String cookieHeader) {
        String[] cookies = cookieHeader.split("; ");
        for (String cookie : cookies) {
            if (cookie.startsWith("access-token=")) {
                return cookie.substring("access-token=".length());
            }
        }
        return null;
    }
}
