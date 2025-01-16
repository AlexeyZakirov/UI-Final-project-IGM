package pages.components;

import com.codeborne.selenide.ElementsCollection;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$$;

@Getter
public class CardContainerComponent {
    private final ElementsCollection collectionGameCardPrices =
            $$("[class*=card-container] [class*=price-text]"),
            collectionGameCards = $$("[class^=CardGameSimple_card-container]"),
            collectionGameCardsImages = $$("[class^=CardGameSimple_card__image]");
}
