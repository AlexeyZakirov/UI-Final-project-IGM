import api.likesprofile.ProfileLikedGames;
import helpers.WithLogin;
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
@Tag("catalog")
public class CatalogTests extends TestBase {
    private final CatalogPage catalogPage = new CatalogPage();
    private final NotificationComponent notificationComponent = new NotificationComponent();
    private final HeaderComponent headerComponent = new HeaderComponent();
    private final ProfileLikedGames profileLikedGames = new ProfileLikedGames();
    private final FavoritesPage favoritesPage = new FavoritesPage();

    @CsvSource(value = {
            "10 , 7599",
            "11 , 7598",
            "0 , 1000000"
    })
    @ParameterizedTest(name = "Установка диапазона цены предлагаемых игр вручную (нижняя граница = {0}, верхняя граница = {1})")
    public void setPriceRangeManuallyTest(int lowerBound, int upperBound) {
        catalogPage.openCatalogPage()
                .setPriceRange(lowerBound, upperBound)
                .checkPriceIsInRange(lowerBound, upperBound);
    }

    @EnumSource(CatalogPriceFilterRadioButtons.class)
    @ParameterizedTest(name = "Установка диапазона цены предлагаемых игр через радио баттоны")
    public void setPriceRangeByButtonTest(CatalogPriceFilterRadioButtons radioButton) {
        catalogPage.openCatalogPage()
                .clickOnPriceRangeRadioButton(radioButton)
                .checkPriceIsInRange(radioButton.getLowerBound(), radioButton.getUpperBound());

    }

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

    @DisplayName("Добавить игру в раздел 'Желаемое', будучи не авторизованным")
    @Test
    public void clickLikeGameWhenNotAuthorized() {
        catalogPage.openCatalogPage().addToFavoriteFirstGameInCatalog();
        notificationComponent.checkNotificationText("Авторизуйтесь");
        notificationComponent.checkNotificationText(", чтобы добавлять игры в желаемое");
    }

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
