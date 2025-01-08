import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.MainPage;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

@Tag("main")
public class MainPageTests extends TestBase {
    private final MainPage mainPage = new MainPage();

    @Test
    public void switchingActiveIconByButtonTest() {

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

        mainPage.switchToPreviousIconTopGamesSlider();
        String activeIconNameAfterSwitchToPrevious = mainPage.getActiveIconNameTopGamesSlider();
        String mainGameNameAfterSwitchToPrevious = mainPage.getNameOfActiveGameTopGamesSlider();
    }

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
