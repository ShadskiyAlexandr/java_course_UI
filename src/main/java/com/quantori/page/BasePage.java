package com.quantori.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Properties;

abstract public class BasePage {

    protected static Properties props = new Properties();

    protected WebDriver driver;
    protected String baseUrl;

    protected WebDriverWait wait10second;

    public BasePage(WebDriver driver) {
        loadProperties();
        this.wait10second = new WebDriverWait(driver, Duration.ofSeconds(10));
        baseUrl = props.getProperty("base.url");
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    protected static void loadProperties() {
        try {
            props.load(Files.newInputStream(Paths.get("src/main/resources/application.properties")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
