package pages.data;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

@Getter
public enum CatalogCategories {
    PRESALES($(".Filters_filters__hxo07").$(byText("Предзаказы")),
            "Категория: Предзаказы"),
    SALES_LEADERS($(".Filters_filters__hxo07").$(byText("Лидеры продаж")),
            "Категория: Лидеры продаж");

    @Override
    public String toString() {
        return filterText;
    }

    //private final SelenideElement categoryBlock = $(".FilterBlock_container__XE8H1").$(byText("Лидеры продаж"))
    private final SelenideElement element;
    private final String filterText;

    CatalogCategories(SelenideElement element, String filterText) {
        this.element = element;
        this.filterText = filterText;
    }

    public void clickOnButton(){
        this.element.click();
    }
}
