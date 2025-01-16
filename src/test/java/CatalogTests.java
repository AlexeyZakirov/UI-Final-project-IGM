import api.likesprofile.ProfileLikedGames;
import helpers.WithLogin;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import pages.CatalogPage;
import pages.FavoritesPage;
import pages.components.HeaderComponent;
import pages.components.NotificationComponent;
import pages.data.CatalogPriceFilterRadioButtons;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Тесты на раздел 'Каталог'")
@Owner("Alexey Zakirov")
@Tag("catalog")
public class CatalogTests extends TestBase {
    private final CatalogPage catalogPage = new CatalogPage();
    private final NotificationComponent notificationComponent = new NotificationComponent();
    private final HeaderComponent headerComponent = new HeaderComponent();
    private final ProfileLikedGames profileLikedGames = new ProfileLikedGames();
    private final FavoritesPage favoritesPage = new FavoritesPage();

    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Установка диапазона цены предлагаемых игр вручную")
    @CsvSource(value = {
            "10 , 7599",
            "11 , 7598",
            "0 , 1000000"
    })
    @ParameterizedTest(name = "Нижняя граница = {0}, верхняя граница = {1}")
    public void setPriceRangeManuallyTest(int lowerBound, int upperBound) {
        catalogPage.openCatalogPage()
                .setPriceRange(lowerBound, upperBound)
                .checkPriceIsInRange(lowerBound, upperBound);
    }

    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Установка диапазона цены предлагаемых игр через радио баттоны")
    @EnumSource(CatalogPriceFilterRadioButtons.class)
    @ParameterizedTest(name = "Радио баттон - {0}")
    public void setPriceRangeByButtonTest(CatalogPriceFilterRadioButtons radioButton) {
        catalogPage.openCatalogPage()
                .clickOnPriceRangeRadioButton(radioButton)
                .checkPriceIsInRange(radioButton.getLowerBound(), radioButton.getUpperBound());

    }

    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Сброс всех установленных фильтров вручную")
    @Test
    public void removeAllFiltersManuallyTest() {
        catalogPage.openCatalogPage()
                .clickOnRandomRadioButtonPrice()
                .clickOnRandomCategory()
                .clickOnToggle("Скидки")
                .clickOnToggle("Бандл")
                .clickOnToggle("Только в наличии")
                .removeAllFiltersManually();
    }

    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Сброс всех установленных фильтров через кнопку")
    @Test
    public void removeAllFiltersByButtonTest() {
        catalogPage.openCatalogPage()
                .clickOnRandomRadioButtonPrice()
                .clickOnRandomCategory()
                .clickOnToggle("Скидки")
                .clickOnToggle("Бандл")
                .clickOnToggle("Только в наличии")
                .removeAllFiltersByButton();
    }

    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Сброс только выбранного фильтра")
    @Test
    public void removeSelectedFilterTest() {
        catalogPage.openCatalogPage()
                .clickOnRandomRadioButtonPrice()
                .clickOnRandomCategory()
                .clickOnToggle("Скидки")
                .clickOnToggle("Бандл")
                .clickOnToggle("Только в наличии")
                .removeSelectedFilter("Скидки");
    }

    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Добавить игру в раздел 'Желаемое', будучи не авторизованным")
    @Test
    public void clickLikeGameWhenNotAuthorized() {
        catalogPage.openCatalogPage().addToFavoriteFirstGameInCatalog();
        notificationComponent.checkNotificationText("Авторизуйтесь");
        notificationComponent.checkNotificationText(", чтобы добавлять игры в желаемое");
    }

    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Добавить игру в раздел 'Желаемое', будучи авторизованным")
    @Test
    @WithLogin
    public void clickLikeGameWhenAuthorized() {
        profileLikedGames.removeGamesFromLikedListProfile();
        catalogPage.openCatalogPage().addToFavoriteFirstGameInCatalog();
        String addedGameName = catalogPage.getFirstCatalogGameName();

        headerComponent.goToFavoritesPage();
        String gameNameInFavorites = favoritesPage.getNameFirstGameInFavorites();

        step("Проверить, что игра, которая добавлялась в 'Желаемое', отображается в этом разделе",
                () -> {
                    assertThat(addedGameName).isEqualTo(gameNameInFavorites);
                });
    }
}
