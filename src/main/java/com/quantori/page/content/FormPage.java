package com.quantori.page.content;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Random;

public class FormPage extends ContentBasePage{

    @FindBy(id = "firstName")
    private WebElement firstNameInput;

    @FindBy(id = "lastName")
    private WebElement lastNameInput;

    @FindBy(id = "userNumber")
    private WebElement mobilePhoneInput;

    @FindBy(css = "[id='submit']")
    private WebElement submitButton;

    @FindBy(css = "[id='userEmail']")
    private static WebElement emailInput;

    @FindBy(css = "[class='modal-content']")
    private static WebElement thanksForm;

    public FormPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public FormPage openUrl() {
        driver.get(baseUrl + "/automation-practice-form");
        return this;
    }

    public FormPage setGenderRadioButton (String name) {
        WebElement radioBtn = driver.findElement(By.xpath("//input[@name='gender'] [@value='" + name + "']/following-sibling::label"));
        wait5second.until(ExpectedConditions.elementToBeClickable(radioBtn));
        radioBtn.click();
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
        boolean clickable = false;
        double zoom = 0.9;

        while (!clickable) {
            try {
                submitButton.click();
                clickable = true;
            } catch (ElementClickInterceptedException e) {
                decreaseZoom(zoom);
                zoom -= 0.1;
            }

        }

        wait5second.until(ExpectedConditions.visibilityOf(thanksForm));
        return this;
    }
    public FormPage jsClickBySubmitButton() {
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();", submitButton);

        wait5second.until(ExpectedConditions.visibilityOf(thanksForm));
        return this;
    }

    @Override
    public FormPage decreaseZoom(double zoom) {
        super.decreaseZoom(zoom);
        return this;
    }

    public enum Gender{
        Male("Male"),
        Female("Female"),
        Other("Other");

        private String name;

        Gender(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public static String getRandomGender() {

            Random random = new Random();

            Gender[] genders = Gender.values();
            return genders[random.nextInt(genders.length)].getName();

        }
    }
}
