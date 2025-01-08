package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class GamePage {
    private final SelenideElement mainGameName = $(".MainInfo_main-info__name__GI9N7"),
            mainGamePrice = $(".MainInfo_main-info__price__IHF8u .Price_price__price-text__MpdHL");

    @Step("Проверить, что название игры - {0}")
    public GamePage checkGameName(String gameName){
        mainGameName.shouldHave(text(gameName));
        return this;
    }

    @Step("Проверить, что цена игры равна = {0}")
    public GamePage checkGamePrice(String gamePrice){
        mainGamePrice.shouldHave(text(gamePrice));
        return this;
    }
}
