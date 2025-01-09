package api.likesprofile;

import api.models.RemoveGameRequestModel;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.List;

import static api.specs.BaseSpecs.requestWithToken;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class ProfileLikedGames {

    public List<Integer> getLikedGamesList() {
        Response response = given(requestWithToken)
                .when()
                .get("/api/profile/liked")
                .then()
                .statusCode(200)
                .extract().response();
        return response.jsonPath().getList("data.games.id");
    }

    @Step("Очистить раздел 'Желаемое'")
    public void removeGamesFromLikedListProfile() {
        List<Integer> likedGameIdsBeforeRemove = getLikedGamesList();
        if (!likedGameIdsBeforeRemove.isEmpty()) {
            for (Integer gameId : likedGameIdsBeforeRemove) {
                removeGameById(gameId);
            }
        }
        assertThat(getLikedGamesList().size()).isEqualTo(0);
    }

    @Step("Удалить игру с id = {0}")
    public void removeGameById(Integer gameId) {
        RemoveGameRequestModel requestModel = new RemoveGameRequestModel(gameId);
        given(requestWithToken)
                .body(requestModel)
                .post("/api/profile/remove_from_favorites")
                .then().statusCode(200);
    }
}
