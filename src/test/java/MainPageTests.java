import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.MainPage;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Тесты на главную страницу")
@Tag("main")
public class MainPageTests extends TestBase {
    private final MainPage mainPage = new MainPage();

    @DisplayName("Переключение активной игры на следующую в слайдере TopGames посредством кнопки 'Следующий слайд'")
    @Test
    public void switchingActiveIconToNextByButtonTest() {

        mainPage.openMainPage();
        String initialActiveIconName = mainPage.getActiveIconNameTopGamesSlider();
        String initialMainGameName = mainPage.getNameOfActiveGameTopGamesSlider();

        step("Проверить, что текущее значение иконки равно значению названия игры до переключения на следующую иконку",
                () -> {
                    assertThat(initialActiveIconName).isEqualTo(initialMainGameName);
                });

        mainPage.switchToNextIconTopGamesSlider();
        String activeIconNameAfterSwitchToNext = mainPage.getActiveIconNameTopGamesSlider();
        String mainGameNameAfterSwitchToNext = mainPage.getNameOfActiveGameTopGamesSlider();

        step("Проверить, что изначальное значение иконки не равно значению иконки после переключения",
                () -> {
                    assertThat(activeIconNameAfterSwitchToNext).isNotEqualTo(initialActiveIconName);
                });
        step("Проверить, что текущее значение иконки равно значению названия игры после переключения вперёд",
                () -> {
                    assertThat(activeIconNameAfterSwitchToNext).isEqualTo(mainGameNameAfterSwitchToNext);
                });
        step("Проверить. что текущее название игры не равно названию игры до переключения вперёд",
                () -> {
                    assertThat(mainGameNameAfterSwitchToNext).isNotEqualTo(initialMainGameName);
                });
    }

    @DisplayName("Переключение активной игры на предыдущую в слайдере TopGames посредством кнопки 'Предыдущий слайд'")
    @Test
    public void switchingActiveIconToPreviousByButtonTest() {

        mainPage.openMainPage();
        String initialActiveIconName = mainPage.getActiveIconNameTopGamesSlider();
        String initialMainGameName = mainPage.getNameOfActiveGameTopGamesSlider();

        step("Проверить, что текущее значение иконки равно значению названия игры до переключения на предыдущую иконку",
                () -> {
                    assertThat(initialActiveIconName).isEqualTo(initialMainGameName);
                });

        mainPage.switchToPreviousIconTopGamesSlider();
        String activeIconNameAfterSwitchToPrevious = mainPage.getActiveIconNameTopGamesSlider();
        String mainGameNameAfterSwitchToPrevious = mainPage.getNameOfActiveGameTopGamesSlider();

        step("Проверить, что изначальное значение иконки не равно значению иконки после переключения",
                () -> {
                    assertThat(activeIconNameAfterSwitchToPrevious).isNotEqualTo(initialActiveIconName);
                });

        step("Проверить, что текущее значение иконки равно значению названия игры после переключения назад",
                () -> {
                    assertThat(activeIconNameAfterSwitchToPrevious).isEqualTo(mainGameNameAfterSwitchToPrevious);
                });
        step("Проверить. что текущее название игры не равно названию игры до переключения назад",
                () -> {
                    assertThat(mainGameNameAfterSwitchToPrevious).isNotEqualTo(initialMainGameName);
                });
    }

    @DisplayName("Переключение активной игры в слайдере TopGames посредством рандомного клика на видимую иконку")
    @Test
    public void switchingActiveIconByRandomClickTest() {
        mainPage.openMainPage();
        String initialActiveIconName = mainPage.getActiveIconNameTopGamesSlider();
        String initialMainGameName = mainPage.getNameOfActiveGameTopGamesSlider();

        step("Проверить, что текущее значение иконки равно значению названия игры до переключения на другую иконку",
                () -> {
                    assertThat(initialActiveIconName).isEqualTo(initialMainGameName);
                });

        mainPage.clickOnRandomDisplayedIconTopGamesSlider();
        String activeIconNameAfterSwitchToRandom = mainPage.getActiveIconNameTopGamesSlider();
        String mainGameNameAfterSwitchToRandom = mainPage.getNameOfActiveGameTopGamesSlider();

        step("Проверить, что текущее значение иконки равно значению названия игры после переключения на другую иконку",
                () -> {
                    assertThat(activeIconNameAfterSwitchToRandom).isEqualTo(mainGameNameAfterSwitchToRandom);
                });
        step("Проверить, что значение иконки до переключения не равно значению иконки после переключения",
                () -> {
                    assertThat(initialActiveIconName).isNotEqualTo(activeIconNameAfterSwitchToRandom);
                });
        step("Проверить, что название игры до переключения не равно названию игры после переключения",
                () -> {
                    assertThat(initialMainGameName).isNotEqualTo(mainGameNameAfterSwitchToRandom);
                });
    }
}
