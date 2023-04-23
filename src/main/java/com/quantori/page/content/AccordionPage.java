package com.quantori.page.content;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AccordionPage extends ContentBasePage{

    @FindBy(css = "[id='section1Heading']")
    private WebElement whatAccordionExpander;

    @FindBy(css = "[id='section2Heading']")
    private WebElement whereAccordionExpander;

    @FindBy(css = "[id='section3Heading']")
    private WebElement whyAccordionExpander;

    private String headerSelector;
    private String contentSelector;

    public AccordionPage(WebDriver driver) {
        super(driver);
    }

    public AccordionPage expandAccordion(Accordion accordion) {
        headerSelector = getAccordionHeaderId(accordion);
        contentSelector = getAccordionContentId(accordion);

        driver.findElement(By.id(headerSelector)).click();

        wait5second
                .until(ExpectedConditions
                        .visibilityOfElementLocated(By.id(contentSelector)));
        return this;
    }

    public String getTextFromAccordion() {
        return driver.findElement(By.id(contentSelector)).getText();
    }

    @Override
    public AccordionPage openUrl() {
        driver.get(baseUrl + "/accordian");
        return this;
    }

    private String getAccordionHeaderId(Accordion accordion) {
        int order = accordion.ordinal() + 1;

        return String.format("section%dHeading", order);
    }

    private String getAccordionContentId(Accordion accordion) {
        int order = accordion.ordinal() + 1;

        return String.format("section%dContent", order);
    }

    public enum Accordion {
        What("What is Lorem Ipsum?"),
        Where("Where does it come from?"),
        Why("Why do we use it?");

        private String name;

        Accordion(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
