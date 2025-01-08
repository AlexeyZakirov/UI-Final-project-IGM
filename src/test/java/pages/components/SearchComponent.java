package pages.components;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

@Getter
public class SearchComponent {
    private final SelenideElement searchInput = $("#search_query"),
            resetSearchQueryButton = $(".SearchInput_search__wnFwa .SearchInput_search__clear-button__eFTHa"),
            searchResultWindow = $(".Games_games__9H5H_"),
            searchResultModalHeader = $("[class*='ResultModal_result-modal__header__cZ9vk']"),
            searchPriceElement = $(".SearchGameCard_card__content__UsGkZ [class*=Price_price__price-text__MpdHL]"),
            searchGameCard = $(".SearchGameCard_card__content__UsGkZ"),
            emptyResultContainer = $(".EmptyStub_container__content__tcyDU"),
            searchResultWindowHeader = $(".ResultModal_result-modal__header__cZ9vk");

    @Step("Ввести поисковый запрос {0}")
    public SearchComponent setValueOnSearchInput(String searchText) {
        searchInput.setValue(searchText);
        checkModalWindowIsVisible();
        checkSearchResultHeaderText(searchText);
        return this;
    }

    @Step("Проверить, что в поисковой выдаче отображено название искомой игры {0}")
    public SearchComponent checkExpectedGameIsDisplayed(String gameName) {
        searchResultWindow.shouldHave(text(gameName));
        return this;
    }

    @Step("Получить цену искомой игры {0} в поисковой выдаче")
    public String getPriceForSelectedGame(String gameName) {
        return searchResultWindow.$(byText(gameName)).closest(".SearchGameCard_card__content__UsGkZ").
                $(".SearchGameCard_card__content__UsGkZ [class*=Price_price__price-text__MpdHL]").text();
    }

    @Step("Перейти в карточку игры в поисковой выдаче")
    public void clickOnSelectedGame(String gameName) {
        searchResultWindow.$(byText(gameName)).click();
    }

    @Step("Проверить, что в поисковой выдаче отображен текст - {0}")
    public SearchComponent checkResultText(String text){
        emptyResultContainer.shouldHave(text(text));
        return this;
    }

    @Step("Проверить, что в хэдере поиской выдаче отображено - 'Найдены результаты по запросу: «{0}»'")
    private void checkSearchResultHeaderText(String queryText){
        String text = String.format("Найдены результаты по запросу: «%s»", queryText);
        searchResultWindowHeader.shouldHave(text(text));
    }
    @Step("Сбросить поисковой запрос")
    public SearchComponent resetSearchQuery(){
        resetSearchQueryButton.click();
        checkInputIsEmpty();
        return this;
    }

    @Step("Проверить, что модальное окно с результатами поиска отображено")
    private void checkModalWindowIsVisible(){
        searchResultWindow.shouldBe(visible);
    }
    @Step("Проверить, что модальное окно с результатами поиска не отображено")
    public SearchComponent checkModalWindowIsNotVisible(){
        searchResultWindow.shouldNotBe(visible);
        return this;
    }
    @Step("Проверить что поисковый input пустой")
    private void checkInputIsEmpty(){
        searchInput.shouldBe(empty);
    }
}
