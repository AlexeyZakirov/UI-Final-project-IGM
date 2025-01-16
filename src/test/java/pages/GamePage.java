package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class GamePage {
    private final SelenideElement mainGameName = $("[class*=main-info__name]"),
            mainGamePrice = $("[class*=main-info__price] [class*=price-text]");

    @Step("Проверить, что название игры - {0}")
    public GamePage checkGameName(String gameName) {
        mainGameName.shouldHave(text(gameName));
        return this;
    }

    @Step("Проверить, что цена игры равна = {0}")
    public GamePage checkGamePrice(String gamePrice) {
        mainGamePrice.shouldHave(text(gamePrice));
        return this;
    }
}
