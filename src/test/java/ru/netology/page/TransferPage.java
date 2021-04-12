package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    private SelenideElement amountField = $("[data-test-id=\"amount\"] input");
    private SelenideElement fromField = $("[data-test-id=from] input");
    private SelenideElement toField = $("[data-test-id=to] input");
    private SelenideElement transferButton = $("[data-test-id=action-transfer]");
    private SelenideElement cancelButton = $("[data-test-id=action-cancel]");

    public void TransferCard(DataHelper.CardInfo fromCardInfo) {
        String amountToAddForTest = DataHelper.amountToAddForTest();
        amountField.setValue(amountToAddForTest); //сумма перевода
        fromField.setValue(fromCardInfo.getCardNumber()); //с какой карты
        transferButton.click();
    }


}
