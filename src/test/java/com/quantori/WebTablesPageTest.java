package com.quantori;

import com.quantori.base.BaseTest;
import com.quantori.page.content.WebTablesPage;
import org.junit.jupiter.api.Test;

import static com.quantori.page.content.WebTablesPage.FormInputName.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class WebTablesPageTest extends BaseTest {

    @Test
    void changeCierraLastNameTest() {
        String nameForSearch = "Cierra";

        String newLastName = faker.name().lastName();

        String actualLastName = new WebTablesPage(driver)
                .openUrl()
                .clickEditRow(firstName, nameForSearch)
                .updateInputOfForm(lastName, newLastName)
                .clickSubmitFormButton()
                .getValueFromTargetRow(lastName);

        assertThat(actualLastName, is(equalTo(newLastName)));
    }

    @Test
    void addNewUserTest() {

        new WebTablesPage(driver)
                .openUrl()
                .clickAddRow()
                .fillAllInputOfForm()
                .clickSubmitFormButton()
                .checkDataInNewRow();
    }

    @Test
    void deleteRowByEmailTest() {
        String emailForSearch = "alden@example.com";

        boolean existing = new WebTablesPage(driver)
                .openUrl()
                .clickDeleteRow(userEmail, emailForSearch)
                .checkIfCellWithValueExist(userEmail, emailForSearch);

        assertThat(String.format("Row with %s %s wasn't deleted, but should", userEmail, emailForSearch), existing, is(equalTo(false)));
    }

}
