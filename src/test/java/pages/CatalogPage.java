package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import pages.components.CardContainerComponent;
import pages.data.CatalogCategories;
import pages.data.CatalogPriceFilterRadioButtons;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.assertj.core.api.Assertions.assertThat;

public class CatalogPage {

    CardContainerComponent cardContainerComponent = new CardContainerComponent();
    Faker faker = new Faker();

    private final String pathToCatalogPage = "/catalog";

    private final SelenideElement priceInputIn = $("input[placeholder='10']"),
            priceInputOut = $("input[placeholder='7599']"),
            selectedFiltersArea = $(".SelectedFilters_selected-filters__mnSTQ"),
            filterBlock = $(".Filters_filters__hxo07"),
            searchElement = $(".Search_search__VHP8Z"),
            likeButton = $("button[aria-label='Добавить в желаемое']");

    private final ElementsCollection
            collectionButtonFilters = $$(".ButtonFilter_filter__text__vPSqY");

    private List<Integer> getCollectionGamesPrice() {
        cardContainerComponent.getCollectionGameCardPrices().get(0).shouldBe(visible);
        List<String> collectionPriceWithCurrency = cardContainerComponent.getCollectionGameCardPrices().texts();
        return collectionPriceWithCurrency.stream()
                .map(
                        e -> Integer.parseInt(e.replaceAll("[^\\d]", ""))
                ).toList();
    }

    @Step("Открыть страницу Catalog")
    public CatalogPage openCatalogPage() {
        open(pathToCatalogPage);
        searchElement.shouldBe(visible);
        return this;
    }

    @Step("Выставить значение нижней границы = {0}, значение верхней границы = {0}")
    public CatalogPage setPriceRange(Integer in, Integer out) {
        String lowerBound = String.valueOf(in);
        String upperBound = String.valueOf(out);
        priceInputIn.setValue(lowerBound);
        priceInputOut.setValue(upperBound);
        checkManualRangePriceButtonFilterIsVisible(in, out);
        return this;
    }

    @Step("Проверить, что цена игр в каталоге соответствует ценовому диапазону: {0} - {1}")
    public CatalogPage checkPriceIsInRange(Integer in, Integer out) {
        getCollectionGamesPrice().forEach((e -> assertThat(e).isBetween(in, out)));
        return this;
    }

    @Step("Проверить, что появилась кнопка фильтра с текстом \"Цена: от {0} до {1}\"")
    private void checkManualRangePriceButtonFilterIsVisible(int lowerBound, int upperBound) {
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.forLanguageTag("ru"));
        String formattedLowerBound = numberFormat.format(lowerBound);
        String formattedUpperBound = numberFormat.format(upperBound);
        String filterText;
        if (lowerBound != 0) {
            filterText = String.format("Цена: от %s до %s", formattedLowerBound, formattedUpperBound);
        } else {
            filterText = String.format("Цена: до %s", formattedUpperBound);
        }

        collectionButtonFilters.get(0).shouldBe(visible);
        collectionButtonFilters.get(0).shouldHave(text(filterText));
    }


    @Step("Кликнуть на кнопку выбора диапазона, где - {0}")
    public CatalogPage clickOnPriceRangeRadioButton(CatalogPriceFilterRadioButtons button) {
        button.clickOnButton();
        checkSelectedFilterIsVisible(button.getFilterText());
        return this;
    }

    @Step("Включить фильтр со скидками")
    public CatalogPage clickOnToggle(String toggleText) {
        filterBlock.$(byText(toggleText)).click();
        checkSelectedFilterIsVisible(toggleText);
        return this;
    }

    @Step("Включить фильтр с категорией - {0}")
    public CatalogPage clickOnCategory(CatalogCategories categories) {
        categories.clickOnButton();
        checkSelectedFilterIsVisible(categories.getFilterText());
        return this;
    }

    @Step("Включить фильтр со случайной категорией")
    public CatalogPage clickOnRandomCategory() {
        List<CatalogCategories> allCategories = new ArrayList<>(
                List.of(
                        CatalogCategories.PRESALES,
                        CatalogCategories.SALES_LEADERS
                )
        );
        int randomIndex = faker.number().numberBetween(0, allCategories.size() - 1);

        clickOnCategory(allCategories.get(randomIndex));
        return this;
    }

    @Step("Выбрать случайный диапазон цены")
    public CatalogPage clickOnRandomRadioButtonPrice() {
        List<CatalogPriceFilterRadioButtons> allPriceButtons = new ArrayList<>(
                List.of(
                        CatalogPriceFilterRadioButtons.PRICE_RANGE_UP_TO_1500,
                        CatalogPriceFilterRadioButtons.PRICE_RANGE_1500_3000,
                        CatalogPriceFilterRadioButtons.PRICE_RANGE_3000_7500,
                        CatalogPriceFilterRadioButtons.PRICE_RANGE_7500_AND_HIGHER
                )
        );
        int randomIndex = faker.number().numberBetween(0, allPriceButtons.size() - 1);

        clickOnPriceRangeRadioButton(allPriceButtons.get(randomIndex));
        return this;
    }

    @Step("Проверить, что отображается фильтр - {0}")
    private void checkSelectedFilterIsVisible(String filterText) {
        selectedFiltersArea.$(byText(filterText)).shouldBe(visible);
    }

    @Step("Удалить вручную все фильтры")
    public CatalogPage removeAllFiltersManually() {
        List<SelenideElement> list = new ArrayList<>(collectionButtonFilters.stream().toList());
        list.remove(list.size() - 1);
        collectionButtonFilters.forEach(element -> {
            if (element.isDisplayed())
                element.click();
        });
        checkFilterListIsEmpty();
        return this;
    }

    @Step("Удалить все фильтры через кнопку \"Очистить всё\"")
    public CatalogPage removeAllFiltersByButton() {
        selectedFiltersArea.$(byText("Очистить всё")).click();
        checkFilterListIsEmpty();
        return this;
    }

    @Step("Проверить, что фильтры отсутствуют")
    private void checkFilterListIsEmpty() {
        assertThat(collectionButtonFilters).isEmpty();
    }

    @Step
    public CatalogPage removeSelectedFilter(String filterText) {
        selectedFiltersArea.$(byText(filterText)).click();
        checkSelectedFilterIsDisabled(filterText);
        return this;
    }

    @Step("Проверить, что фильтр {0} не включен")
    private void checkSelectedFilterIsDisabled(String filterText) {
        selectedFiltersArea.shouldNotHave(text(filterText));
    }

    @Step("Добавить первую игру из каталога в список желаемого")
    public CatalogPage addToFavoriteFirstGameInCatalog() {
        cardContainerComponent.getCollectionGameCardPrices().get(0).shouldBe(visible);
        cardContainerComponent.getCollectionGameCardPrices().get(0).hover();
        likeButton.click();
        return this;
    }

    @Step("Получить значение первой игры из каталога")
    public String getFirstCatalogGameName() {
        return cardContainerComponent.getCollectionGameCardsImages().get(0).attr("alt");
    }
}
