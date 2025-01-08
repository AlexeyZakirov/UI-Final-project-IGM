import api.likesprofile.ProfileLikedGames;
import helpers.WithLogin;
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

@Tag("catalog")
public class CatalogTests extends TestBase {
    CatalogPage catalogPage = new CatalogPage();
    NotificationComponent notificationComponent = new NotificationComponent();
    HeaderComponent headerComponent = new HeaderComponent();
    ProfileLikedGames profileLikedGames = new ProfileLikedGames();
    FavoritesPage favoritesPage = new FavoritesPage();

    @CsvSource(value = {
            "10 , 7599",
            "11 , 7598",
            "0 , 1000000"
    })
    @ParameterizedTest
    public void setPriceRangeTest(int lowerBound, int upperBound) {
        catalogPage.openCatalogPage()
                .setPriceRange(lowerBound, upperBound)
                .checkPriceIsInRange(lowerBound, upperBound);
    }

    @EnumSource(CatalogPriceFilterRadioButtons.class)
    @ParameterizedTest
    public void setPriceRangeByButtonTest(CatalogPriceFilterRadioButtons radioButton){
        catalogPage.openCatalogPage()
                .clickOnPriceRangeRadioButton(radioButton)
                .checkPriceIsInRange(radioButton.getLowerBound(), radioButton.getUpperBound());

    }

    @Test
    public void removeAllFiltersManuallyTest(){
        catalogPage.openCatalogPage()
                .clickOnRandomRadioButtonPrice()
                .clickOnRandomCategory()
                .clickOnToggle("Скидки")
                .clickOnToggle("Бандл")
                .clickOnToggle("Только в наличии")
                .removeAllFiltersManually();
    }

    @Test
    public void removeAllFiltersByButtonTest(){
        catalogPage.openCatalogPage()
                .clickOnRandomRadioButtonPrice()
                .clickOnRandomCategory()
                .clickOnToggle("Скидки")
                .clickOnToggle("Бандл")
                .clickOnToggle("Только в наличии")
                .removeAllFiltersByButton();
    }

    @Test
    public void removeSelectedFilterTest(){
        catalogPage.openCatalogPage()
                .clickOnRandomRadioButtonPrice()
                .clickOnRandomCategory()
                .clickOnToggle("Скидки")
                .clickOnToggle("Бандл")
                .clickOnToggle("Только в наличии")
                .removeSelectedFilter("Скидки");
    }

    @Test
    public void clickLikeGameWhenNotAuthorized(){
        catalogPage.openCatalogPage().addToFavoriteFirstGameInCatalog();
        notificationComponent.checkNotificationText("Авторизуйтесь");
        notificationComponent.checkNotificationText(", чтобы добавлять игры в желаемое");
    }

    @Test
    @WithLogin
    public void clickLikeGameWhenAuthorized(){
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
