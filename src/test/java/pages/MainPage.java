package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;
import io.qameta.allure.Step;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {
    private final String pathToMainPage = "/";
    private final SelenideElement activeIconTopGamesSlider = $("[class*='CardGameSimple_card-container_active__RdW_f'] .CardGameSimple_card__image__u1ij1"),
            nextButtonTopGamesSlider = $(".TopGamesSlider_top-games-slider__buttons-container__87rxQ button[aria-label='Следующий слайд']"),
            previousButtonTopGameSlider = $(".TopGamesSlider_top-games-slider__buttons-container__87rxQ button[aria-label='Предыдущий слайд']"),
            mainGameLinkElementTopGamesSlider = $("a.Link_link__next-link__6FqGj");
    private final ElementsCollection iconsCollectionTopGamesSlider = $$(".TopGamesSlider_top-games-slider__list__mWmCh .CardGameSimple_card-container__YT3SJ");

    private List<SelenideElement> visibleElementsList() {
        return iconsCollectionTopGamesSlider.stream().
                filter(SelenideElement::isDisplayed).collect(Collectors.toList()
                );
    }

    @Step("Открыть главную страницу магазина")
    public MainPage openMainPage() {
        open(pathToMainPage);
        activeIconTopGamesSlider.shouldBe(visible);
        return this;
    }

    @Step("Переключить активную иконку на следующую в разделе Top Games через кнопку")
    public MainPage switchToNextIconTopGamesSlider() {
        nextButtonTopGamesSlider.click();
        return this;
    }

    @Step("Переключить активную иконку на предыдущую в разделе Top Games через кнопку")
    public MainPage switchToPreviousIconTopGamesSlider() {
        previousButtonTopGameSlider.click();
        return this;
    }

    @Step("Получить строковое значение активной иконки в разделе Top Games")
    public String getActiveIconNameTopGamesSlider() {
        return activeIconTopGamesSlider.attr("alt");
    }

    @Step("Получить строковое значение названия отображаемой игры в разделе Top Games")
    public String getNameOfActiveGameTopGamesSlider() {
        return mainGameLinkElementTopGamesSlider.text();
    }

    @Step("Кликнуть на случайную отображаемую иконку в разделе Top Games")
    public MainPage clickOnRandomDisplayedIconTopGamesSlider() {
        List<SelenideElement> elementList = visibleElementsList();
        Faker faker = new Faker();
        elementList.get(faker.number().numberBetween(0, elementList.size() - 1)).click();
        return this;
    }
}
