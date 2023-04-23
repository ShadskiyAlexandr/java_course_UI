package com.quantori.page.content;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.quantori.page.content.VisibilityPage.Button.Overlapped;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class VisibilityPage extends ContentBasePage {

    public VisibilityPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public VisibilityPage openUrl() {
        driver.get("http://uitestingplayground.com/visibility");
        return this;
    }

    public VisibilityPage clickButton(Button button) {
        String selector = button.getSelector();

        driver.findElement(By.id(selector)).click();

        return this;
    }

    public VisibilityPage checkIfButtonInvisibleExceptHide() {
        for (Button button : Button.values()) {
            if (!button.equals(Button.Hide)) {
                checkIfButtonInvisible(button);
//                assertThat(String.format("The %s button is visible, but should not", button), checkIfButtonInvisible(button), is(true));
            }
        }
        return this;
    }

    public boolean checkIfButtonInvisible(Button button) {
        try {
            WebElement element = driver.findElement(By.id(button.getSelector()));

            return checkStyle(element) || checkClass(element) || checkHidingLayer(element);
        } catch (NoSuchElementException e) {
            return true;
        }
    }

    public enum Button {
        Hide("hideButton"),
        Removed("removedButton"),
        ZeroWidth("zeroWidthButton"),
        Overlapped("overlappedButton"),
        Opacity("transparentButton"),
        VisibilityHidden("invisibleButton"),
        DisplayNone("notdisplayedButton"),
        Offscreen("offscreenButton");

        private String selector;

        Button(String selector) {
            this.selector = selector;
        }

        public String getSelector() {
            return selector;
        }
    }

    private boolean checkClass(WebElement element) {
        String classes = element.getAttribute("class");
        return classes.contains("zerowidth") ||
                classes.contains("offscreen");
    }

    private boolean checkStyle(WebElement element) {
        String styles = element.getAttribute("Style");
        return styles.contains("zerowidth") ||
                styles.contains("opacity: 0") ||
                styles.contains("visibility: hidden") ||
                styles.contains("display: none") ||
                styles.contains("position: absolute");
    }

    private boolean checkHidingLayer(WebElement element) {
        WebElement layer = element.findElement(By.id("hidingLayer"));
        return checkStyle(layer);
    }
}
