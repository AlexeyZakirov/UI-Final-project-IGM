import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.MainPage;
import pages.components.HeaderComponent;

import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

@DisplayName("Тесты на компонент хэдер")
@Tag("header")
public class HeaderTests extends TestBase {
    private final HeaderComponent header = new HeaderComponent();
    private final MainPage mainPage = new MainPage();

    @DisplayName("Наличие всех элементов в хэдере")
    @Test
    public void headerShouldHaveAllElementsBeVisibleTest() {
        mainPage.openMainPage();
        step("Проверить присутствие в хэдере элемента 'Catalog'", () -> {
            header.checkElementIsVisibleInHeader(header.getCatalogButton());
        });
        step("Проверить присутствие в хэдере элемента 'Пополни Steam'", () -> {
            header.checkElementIsVisibleInHeader(header.getRechargeSteamButton());
        });
        step("Проверить присутствие в хэдере элемента logo", () -> {
            header.checkElementIsVisibleInHeader(header.getLogoElement());
        });
        step("Проверить присутствие в хэдере элемента 'Желаемое'", () -> {
            header.checkElementIsVisibleInHeader(header.getLikeButton());
        });
        step("Проверить присутствие в хэдере элемента 'Корзина'", () -> {
            header.checkElementIsVisibleInHeader(header.getCartButton());
        });
        step("Проверить присутствие в хэдере элемента 'Поиск'", () -> {
            header.checkElementIsVisibleInHeader(header.getSearchInput());
        });
        step("Проверить присутствие в хэдере элемента 'Войти'", () -> {
            header.checkElementIsVisibleInHeader(header.getLoginElement());
        });
    }
}
