package pages.components;

import com.codeborne.selenide.ElementsCollection;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$$;

@Getter
public class CardContainerComponent {
    private final ElementsCollection collectionGameCardPrices =
            $$(".CardGameSimple_card-container__YT3SJ .Price_price__price-text__MpdHL"),
            collectionGameCards = $$(".CardGameSimple_card-container__YT3SJ"),
            collectionGameCardsImages = $$(".CardGameSimple_card__image__u1ij1");
}
