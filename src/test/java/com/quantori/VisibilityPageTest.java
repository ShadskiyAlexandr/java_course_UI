package com.quantori;

import com.quantori.base.BaseTest;
import com.quantori.page.content.VisibilityPage;
import org.junit.jupiter.api.Test;

import static com.quantori.page.content.VisibilityPage.Button.Hide;

public class VisibilityPageTest extends BaseTest {
    @Test
    void hideAllButtonsTest(){
        new VisibilityPage(driver)
                .openUrl()
                .clickButton(Hide)
                .checkIfButtonInvisibleExceptHide();
    }
}
