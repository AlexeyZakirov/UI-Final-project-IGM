package api.authorization;

import api.models.LoginRequestModel;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import specs.AuthConfig;
import specs.ConfigReader;

import static io.restassured.RestAssured.given;

public class AuthorizationApi {

    public static Response getAuthorizationResponse() {
        AuthConfig authConfig = ConfigReader.INSTANCE.readAuthConfig();
        LoginRequestModel login = new LoginRequestModel(authConfig.email(), authConfig.password());

        return given()
                .body(login)
                .contentType(ContentType.JSON)
                .when()
                .post("/login");
    }

    private static String extractAccessTokenForApi(String cookieHeader) {
        String[] cookies = cookieHeader.split("; ");
        for (String cookie : cookies) {
            if (cookie.startsWith("access-token=")) {
                return cookie;
            }
        }
        return null;
    }

    public static String getAccessTokenForApi() {
        return extractAccessTokenForApi(getAuthorizationResponse().getHeader("set-cookie"));
    }

}
