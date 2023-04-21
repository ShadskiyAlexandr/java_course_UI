package com.quantori.page.content;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CheckboxPage extends ContentBasePage{

    @FindBy(xpath = "//button[@title='Expand all']")
    private WebElement expandAllButton;

    @FindBy(className = "rct-checkbox")
    private List<WebElement> checkboxes;

    @FindBy(css = "[for='tree-node-angular'] .rct-checkbox")
    private WebElement angularCheckbox;

    @FindBy(css = "[for='tree-node-home'] .rct-checkbox")
    private WebElement homeCheckbox;

    public CheckboxPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public CheckboxPage openUrl() {
        driver.get(baseUrl + "/checkbox");
        return this;
    }

    public CheckboxPage clickExpandAllButton () {
        expandAllButton.click();
        return this;
    }

    public CheckboxPage checkAllCheckboxSelected() {
        checkboxes.forEach(this::checkCheckboxIsSelected);
        return this;
    }

    public CheckboxPage selectHomeCheckbox() {
        homeCheckbox.click();
        checkCheckboxIsSelected(homeCheckbox);
        return this;
    }

    public CheckboxPage selectAngularCheckbox() {
        angularCheckbox.click();
        checkCheckboxIsSelected(angularCheckbox);
        return this;
    }

    private void checkCheckboxIsSelected(WebElement element) {
        assertThat(element.findElement(By.cssSelector("svg"))
                .getAttribute("class").contains("rct-icon-check"), is(true));
    }
}
