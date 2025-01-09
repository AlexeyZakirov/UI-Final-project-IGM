package pages.components;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class NotificationComponent {
    private final SelenideElement notificationContent = $(".Notification_notification__content__hG16b");

    @Step("Проверить, что во всплывающем окне содержится текст {0}")
    public void checkNotificationText(String notifText) {
        notificationContent.shouldHave(text(notifText));
    }
}
