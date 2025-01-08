import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.components.HeaderComponent;

import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

@Tag("header")
public class HeaderTests extends TestBase {
    HeaderComponent header = new HeaderComponent();

    @Test
    public void headerShouldHaveAllElementsBeVisibleTest() {
        open("/");
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
