package com.quantori.page.content;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ModalDialogsPage extends ContentBasePage{

    @FindBy(css = "[id='showSmallModal']")
    private WebElement showSmallModalButton;
    @FindBy(css = "[id='showLargeModal']")
    private WebElement showLargeModalButton;
    @FindBy(css = "[class='modal-content']")
    private WebElement modalWindow;
    @FindBy(css = "[class='modal-body']")
    private WebElement modalBody;

    public ModalDialogsPage(WebDriver driver) {
        super(driver);
    }

    public ModalDialogsPage clickShowSmallModalWindow() {
        showSmallModalButton.click();
        return this;
    }
    public ModalDialogsPage clickShowLargeModalWindow() {
        showLargeModalButton.click();
        wait5second.until(ExpectedConditions.visibilityOf(modalWindow));
        return this;
    }

    public String getModalWindowText() {
        return modalBody.getText();
    }

    @Override
    public ModalDialogsPage openUrl() {
        driver.get(baseUrl + "/modal-dialogs");
        return this;
    }
}
