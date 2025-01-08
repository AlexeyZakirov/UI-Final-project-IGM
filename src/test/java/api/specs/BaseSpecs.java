package api.specs;


import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static api.authorization.AuthorizationApi.getAccessTokenForApi;
import static helpers.CustomAllureListener.withCustomTemplates;

public class BaseSpecs {

    public static RequestSpecification requestWithToken = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .addHeader("Cookie", getAccessTokenForApi())
            .build()
            .filter(withCustomTemplates());
}
