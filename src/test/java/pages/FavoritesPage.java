package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;
import pages.components.CardContainerComponent;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

@Getter
public class FavoritesPage {
    private final CardContainerComponent cardContainerComponent = new CardContainerComponent();
    private final SelenideElement favoritesButton = $("[class^=Tabs_tabs]").$(byText("Список желаемого"));

    @Step("Получить значение первой добавленной игры в Желаемое")
    public String getNameFirstGameInFavorites() {
        return cardContainerComponent.getCollectionGameCardsImages().get(0).attr("alt");
    }
}
