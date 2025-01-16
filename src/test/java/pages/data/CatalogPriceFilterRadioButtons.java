package pages.data;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$;

@Getter
public enum CatalogPriceFilterRadioButtons {

    PRICE_RANGE_UP_TO_1500(0, 1500, "Цена: до 1 500",
            $("[class^=RadioButton][data-max='1500']")),

    PRICE_RANGE_1500_3000(1500, 3000, "Цена: от 1 500 до 3 000",
            $("[class^=RadioButton][data-min='1500'][data-max='3000']")),

    PRICE_RANGE_3000_7500(3000, 7500, "Цена: от 3 000 до 7 500",
            $("[class^=RadioButton][data-min='3000'][data-max='7500']")),

    PRICE_RANGE_7500_AND_HIGHER(7500, 1000000, "Цена: от 7 500",
            $("[class^=RadioButton][data-min='7500']"));

    @Override
    public String toString() {
        return filterText;
    }

    private final int lowerBound;
    private final int upperBound;
    private final String filterText;
    private final SelenideElement element;

    CatalogPriceFilterRadioButtons(int lowerBound, int upperBound, String filterText, SelenideElement element) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.filterText = filterText;
        this.element = element;
    }

    public void clickOnButton() {
        this.element.click();
    }
}
