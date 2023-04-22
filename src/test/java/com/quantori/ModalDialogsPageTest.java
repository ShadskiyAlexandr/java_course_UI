package com.quantori;

import com.quantori.base.BaseTest;
import com.quantori.page.content.ModalDialogsPage;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class ModalDialogsPageTest extends BaseTest {

    @Test
    void checkTextFromSmallModalWinodw() {
        loadProperties();
        String actualValue = new ModalDialogsPage(driver)
                .openUrl()
                .clickShowSmallModalWindow()
                .getModalWindowText();

        assertThat(actualValue, is(equalTo(props.getProperty("small.modal.text"))));
    }
    @Test
    void checkTextFromLargeModalWinodw() {
        loadProperties();
        String actualValue = new ModalDialogsPage(driver)
                .openUrl()
                .clickShowLargeModalWindow()
                .getModalWindowText();

        assertThat(actualValue, is(equalTo(props.getProperty("large.modal.text"))));
    }
}
