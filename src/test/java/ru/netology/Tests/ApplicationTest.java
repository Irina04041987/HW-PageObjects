package ru.netology.Tests;


import lombok.val;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class ApplicationTest {
    int amountToAddForTest = Integer.parseInt(DataHelper.amountToAddForTest());

    @Test
    void shouldCheckTransactionIsOkFromSecondToFirst() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCode(authInfo);
        val dashboardPage = verificationPage.verify(verificationCode);
        int balanceOfFirstCardBefore = DashboardPage.getCurrentBalanceOfFirstCard();
        int balanceOfSecondCardBefore = DashboardPage.getCurrentBalanceOfSecondCard();
        val TransferPage = dashboardPage.chooseFirstCardToTransfer();
        val cardInfo = DataHelper.getSecondCardInformation(); //с какой карты
        TransferPage.TransferCard(cardInfo);
        int balanceAfterTransactionOnTransfer = DataHelper.BalanceOfCardToTransfer(balanceOfFirstCardBefore, amountToAddForTest);
        int balanceAfterTransaction = DataHelper.BalanceOfCardFromTransfer(balanceOfSecondCardBefore, amountToAddForTest);
        int balanceOfFirstCardAfter = DashboardPage.getCurrentBalanceOfFirstCard();
        int balanceOfSecondCardAfter = DashboardPage.getCurrentBalanceOfSecondCard();
        assertEquals(balanceAfterTransactionOnTransfer,balanceOfFirstCardAfter );
        assertEquals(balanceAfterTransaction,balanceOfSecondCardAfter );
        assertEquals(balanceAfterTransactionOnTransfer,DashboardPage.getCurrentBalanceOfFirstCard() );
        assertEquals(balanceAfterTransaction,DashboardPage.getCurrentBalanceOfSecondCard() );
    }
    @Test
    void shouldCheckTransactionIsOkFromFirstToSecond() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCode(authInfo);
        val dashboardPage = verificationPage.verify(verificationCode);
        int balanceOfFirstCardBefore = DashboardPage.getCurrentBalanceOfFirstCard();
        int balanceOfSecondCardBefore = DashboardPage.getCurrentBalanceOfSecondCard();
        val TransferPage = dashboardPage.chooseSecondCardToTransfer();
        val cardInfo = DataHelper.getFirstCardInformation();
        TransferPage.TransferCard(cardInfo);
        int balanceAfterTransactionOnTransfer = DataHelper.BalanceOfCardToTransfer(balanceOfSecondCardBefore, amountToAddForTest);
        int balanceAfterTransaction = DataHelper.BalanceOfCardFromTransfer(balanceOfFirstCardBefore, amountToAddForTest);
        int balanceOfFirstCardAfter = DashboardPage.getCurrentBalanceOfSecondCard();
        int balanceOfSecondCardAfter = DashboardPage.getCurrentBalanceOfFirstCard();
        assertEquals(balanceAfterTransactionOnTransfer,balanceOfFirstCardAfter );
        assertEquals(balanceAfterTransaction,balanceOfSecondCardAfter );

    }
}