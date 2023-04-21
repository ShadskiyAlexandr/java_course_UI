package com.quantori.page.content;

import com.quantori.page.BasePage;
import com.quantori.page.OpenUrl;
import com.quantori.view.LeftNavigation;
import org.openqa.selenium.WebDriver;

abstract public class ContentBasePage extends BasePage implements OpenUrl {

    protected LeftNavigation leftNavigation;

    public ContentBasePage(WebDriver driver) {
        super(driver);
        this.leftNavigation = new LeftNavigation(driver);
    }

    public LeftNavigation getLeftNavigation() {
        return leftNavigation;
    }
}
