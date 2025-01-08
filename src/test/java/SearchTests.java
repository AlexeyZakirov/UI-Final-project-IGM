import com.github.javafaker.Faker;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.GamePage;
import pages.MainPage;
import pages.components.SearchComponent;

@Tag("search")
public class SearchTests extends TestBase {
    private final SearchComponent search = new SearchComponent();
    private final MainPage mainPage = new MainPage();
    private final GamePage gamePage = new GamePage();
    private final Faker faker = new Faker();

    private final String randomQueryText = faker.letterify("?????");

    @Test
    public void searchExistGameTest() {
        String searchText = "witcher";
        String expectedGameName = "Ведьмак 3: Дикая Охота — Полное издание";

        mainPage.openMainPage();
        search.setValueOnSearchInput(searchText)
                .checkExpectedGameIsDisplayed(expectedGameName);
        String price = search.getPriceForSelectedGame(expectedGameName);
        search.clickOnSelectedGame(expectedGameName);

        gamePage.checkGameName(expectedGameName)
                .checkGamePrice(price);
    }

    @Test
    public void searchNotExistGameTest() {
        String messageText = "По вашему запросу ничего не найдено.\n" +
                "Попробуйте поискать еще";

        mainPage.openMainPage();
        search.setValueOnSearchInput(randomQueryText)
                .checkResultText(messageText);
    }

    @Test
    public void resetSearchQueryTest(){
        mainPage.openMainPage();
        search.setValueOnSearchInput(randomQueryText)
                .resetSearchQuery()
                .checkModalWindowIsNotVisible();
    }
}
