package com.quantori;

import com.quantori.base.BaseTest;
import com.quantori.page.content.FormPage;
import org.junit.jupiter.api.Test;

import static com.quantori.page.content.FormPage.Gender.Male;

public class FormPageTest extends BaseTest {

    @Test
    void submitOnlyMandatoryFieldsTest() {
        new FormPage(driver)
                .openUrl()
                .enterMobilePhone(faker.phoneNumber().subscriberNumber(10))
                .enterFirstName(faker.name().firstName())
                .enterLastName(faker.name().lastName())
                .setGenderRadioButton(Male.getName())
                .jsClickBySubmitButton();
    }

    @Test
    void submitFieldsTest() {
        new FormPage(driver)
                .openUrl()
                .enterFirstName(faker.name().firstName())
                .enterLastName(faker.name().lastName())
                .setGenderRadioButton(Male.getName())
                .enterMobilePhone(faker.phoneNumber().subscriberNumber(10))
                .enterEmail(faker.internet().emailAddress())
                .jsClickBySubmitButton();
    }
}
