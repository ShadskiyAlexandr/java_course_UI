package com.quantori.view;

import com.quantori.page.BasePage;
import com.quantori.page.content.ContentBasePage;
import com.quantori.page.content.FormPage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class LeftNavigation extends BasePage {

    @FindBy(css = ".left-pannel .element-group")
    private List<WebElement> buttonList;

    public LeftNavigation(WebDriver driver) {
        super(driver);
    }

    @Step("Click by {buttonName}")
    public ContentBasePage clickButton(Button button) {
        ContentBasePage contentBasePage = null;

        switch (button) {
            case forms:
                buttonList.get(1).click();
                contentBasePage = new FormPage(driver);
                break;
        }
        return contentBasePage;
    }

    public enum Button {
        forms("Forms");

        private String name;

        Button(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
