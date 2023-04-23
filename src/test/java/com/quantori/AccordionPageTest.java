package com.quantori;

import com.quantori.base.BaseTest;
import com.quantori.page.content.AccordionPage;
import org.junit.jupiter.api.Test;

import static com.quantori.page.content.AccordionPage.Accordion.Where;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class AccordionPageTest extends BaseTest {

    @Test
    void checkAccordionTest(){
        String searching = "Hampden-Sydney College";
        String text = new AccordionPage(driver)
                .openUrl()
                .expandAccordion(Where)
                .getTextFromAccordion();

        assertThat(String.format("Couldn't find '%s' text into '%s'", searching, text), text.contains(searching), is(true));
    }
}
