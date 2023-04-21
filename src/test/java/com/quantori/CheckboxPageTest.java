package com.quantori;

import com.quantori.base.BaseTest;
import com.quantori.page.content.CheckboxPage;
import org.junit.jupiter.api.Test;

public class CheckboxPageTest extends BaseTest {

    @Test
    void SelectAllCheckboxes() {
        new CheckboxPage(driver)
                .openUrl()
                .selectHomeCheckbox()
                .clickExpandAllButton()
                .checkAllCheckboxSelected();
    }
    @Test
    void SelectAngularCheckboxe() {
        new CheckboxPage(driver)
                .openUrl()
                .clickExpandAllButton()
                .selectAngularCheckbox();
    }
}
