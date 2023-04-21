package com.quantori.page.content;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FormPage extends ContentBasePage{

    @FindBy(id = "firstName")
    private WebElement firstNameInput;

    @FindBy(id = "lastName")
    private WebElement lastNameInput;

    @FindBy(id = "userNumber")
    private WebElement mobilePhoneInput;

    @FindBy(css = "[id='submit']")
    private WebElement submitButton;

    @FindBy(css = "[id='userEmail'")
    private static WebElement emailInput;

    public FormPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public FormPage openUrl() {
        driver.get(baseUrl + "/automation-practice-form");
        return this;
    }

    public FormPage setGenderRadioButton (String name) {
        driver.findElement(By.xpath("//input[@name='gender'] [@value='" + name + "']"));
        return this;
    }

    public FormPage enterFirstName(String text) {
        firstNameInput.sendKeys(text);
        return this;
    }

    public FormPage enterLastName(String text) {
        lastNameInput.sendKeys(text);
        return this;
    }

    public FormPage enterMobilePhone(String text) {
        mobilePhoneInput.sendKeys(text);
        return this;
    }

    public FormPage enterEmail(String text) {
       emailInput.sendKeys(text);
        return this;
    }

    public FormPage clickSubmitButton() {
        submitButton.click();
        return this;
    }

    public enum Gender{
        Male("Male");

        private String name;

        Gender(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
