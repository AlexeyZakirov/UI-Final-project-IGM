package pages.components;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;
import pages.FavoritesPage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

@Getter
public class HeaderComponent {
    private final SearchComponent searchComponent = new SearchComponent();
    private final FavoritesPage favoritesPage = new FavoritesPage();

    private final SelenideElement catalogButton = $("#__HEADER_CONTENT__ a[href='/catalog']"),
            rechargeSteamButton = $("#__HEADER_CONTENT__ a[href='/steam']"),
            logoElement = $("#__HEADER_CONTENT__ .Logo_logo__gLQw4"),
            likeButton = $("#__HEADER_CONTENT__ [aria-label='Желаемое']"),
            cartButton = $("#__HEADER_CONTENT__ [href='/cart']"),
            searchInput = searchComponent.getSearchInput(),
            loginElement = $("#__HEADER_CONTENT__").$(byText("Войти"));

    public void checkElementIsVisibleInHeader(SelenideElement element) {
        element.shouldBe(visible);
    }

    @Step("Перейти в раздел Желаемое")
    public void goToFavoritesPage(){
        likeButton.click();
        favoritesPage.getFavoritesButton().shouldBe(visible);
    }
}
