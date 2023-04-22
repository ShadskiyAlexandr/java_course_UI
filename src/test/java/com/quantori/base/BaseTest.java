package com.quantori.base;

import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Properties;

abstract public class BaseTest {

    protected WebDriver driver;

    protected static Faker faker;

    protected static Properties props;

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        faker = new Faker();
        props = new Properties();
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    protected static void loadProperties() {
        try {
            props.load(Files.newInputStream(Paths.get("src/test/resources/application.properties")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
