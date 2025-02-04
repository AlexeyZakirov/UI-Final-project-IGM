import com.github.javafaker.Faker;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.GamePage;
import pages.MainPage;
import pages.components.SearchComponent;

@DisplayName("Тесты на поисковую строку")
@Owner("Alexey Zakirov")
@Tag("search")
public class SearchTests extends TestBase {
    private final SearchComponent search = new SearchComponent();
    private final MainPage mainPage = new MainPage();
    private final GamePage gamePage = new GamePage();
    private final Faker faker = new Faker();

    private final String randomNegativeQueryText = "bgdf" + faker.letterify("?????") + "123";

    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Поиск существующей игры с переходом на страницу игры")
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

    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Поиск несуществующей игры")
    @Test
    public void searchNotExistGameTest() {
        String messageText = "По вашему запросу ничего не найдено.\n" +
                "Попробуйте поискать еще";

        mainPage.openMainPage();
        search.setValueOnSearchInput(randomNegativeQueryText)
                .checkResultText(messageText);
    }

    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Сброс поискового запроса")
    @Test
    public void resetSearchQueryTest() {
        mainPage.openMainPage();
        search.setValueOnSearchInput(randomNegativeQueryText)
                .resetSearchQuery()
                .checkModalWindowIsNotVisible();
    }
}
