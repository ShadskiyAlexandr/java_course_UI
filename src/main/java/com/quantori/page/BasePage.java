package com.quantori.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

abstract public class BasePage {

    protected static Properties props = new Properties();

    protected WebDriver driver;
    protected String baseUrl;

    public BasePage(WebDriver driver) {
        loadProperties();
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
