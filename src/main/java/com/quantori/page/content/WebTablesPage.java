package com.quantori.page.content;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class WebTablesPage extends ContentBasePage {

    @FindBy(xpath = "//div[@class='rt-tr-group']/div")
    private List<WebElement> listOfTableRow;

    @FindBy(css = "[id='userForm']")
    private WebElement userForm;

    @FindBy(id = "addNewRecordButton")
    private WebElement addButton;

    @FindBy(css = "[id='submit']")
    private WebElement submitFormButton;

    private Faker faker;

    private String newFirstName;
    private String newLastName;
    private String newAge;
    private String newUserEmail;
    private String newSalary;
    private String newDepartment;

    public WebTablesPage(WebDriver driver) {
        super(driver);
        faker = new Faker();
    }

    @Override
    public WebTablesPage openUrl() {
        driver.get(baseUrl + "/webtables");
        return this;
    }

    private WebElement targetRow;
    private Optional<WebElement> searchResult;
    private List<WebElement> listRowsWithValue;

    public WebTablesPage clickEditRow(FormInputName inputName, String name) {
        targetRow = returnRowByValue(inputName, name);
        targetRow
                .findElement(By.cssSelector("[title='Edit']"))
                .click();
        wait10second.until(ExpectedConditions.visibilityOf(userForm));
        return this;
    }

    public WebTablesPage clickDeleteRow(FormInputName inputName, String name) {
        targetRow = returnRowByValue(inputName, name);
        targetRow
                .findElement(By.cssSelector("[title='Delete']"))
                .click();
        return this;
    }

    public WebTablesPage clickAddRow() {
        addButton.click();
        wait10second.until(ExpectedConditions.visibilityOf(userForm));
        return this;
    }

    public WebTablesPage fillInputOfForm(FormInputName inputName, String value) {
        returnInputByInputName(inputName).sendKeys(value);
        return this;
    }

    public WebTablesPage fillAllInputOfForm() {
        String value = null;
        for (FormInputName input : FormInputName.values()) {
            switch (input) {
                case firstName:
                    value = faker.name().firstName();
                    newFirstName = value;
                    break;
                case lastName:
                    value = faker.name().lastName();
                    newLastName = value;
                    break;
                case age:
                    value = Integer.toString(faker.random().nextInt(5, 99));
                    newAge = value;
                    break;
                case userEmail:
                    value = faker.internet().emailAddress();
                    newUserEmail = value;
                    break;
                case salary:
                    value = Integer.toString(faker.random().nextInt(500, 5000));
                    newSalary = value;
                    break;
                case department:
                    value = faker.job().title();
                    newDepartment = value;
                    break;
            }
            returnInputByInputName(input).sendKeys(value);
        }
        return this;
    }

    public WebTablesPage updateInputOfForm(FormInputName inputName, String value) {
        returnInputByInputName(inputName).sendKeys(Keys.chord(Keys.CONTROL, "a"));
        fillInputOfForm(inputName, value);
        return this;
    }

    public WebTablesPage clickSubmitFormButton() {
        submitFormButton.click();
        return this;
    }

    private WebElement returnInputByInputName(FormInputName inputName) {
        return userForm
                .findElement(By.cssSelector("[id='" + inputName + "-wrapper']"))
                .findElement(By.cssSelector("input"));
    }

    public WebElement returnRowByValue(FormInputName inputName, String name) {
        return returnCellByValue(inputName, name)
                .findElement(By.xpath("./.."));
    }

    public WebElement returnCellByValue(FormInputName inputName, String value) {
        checkIfCellWithValueExist(inputName, value);

        assertThat(String.format("Row with %s %s wasn't found", inputName, value), searchResult.isPresent(), is(true));
        return searchResult.get();
    }

    public boolean checkIfCellWithValueExist(FormInputName inputName, String value) {
        int order = inputName.ordinal() + 1;

        searchResult = listOfTableRow.stream()
                .map(row -> row.findElement(By.cssSelector(String.format(".rt-td:nth-child(%d)", order))))
                .filter(cell -> cell.getText().equals(value))
                .findFirst();

        return searchResult.isPresent();
    }

    public String getValueFromTargetRow(FormInputName inputName) {
        int order = inputName.ordinal() + 1;
        return targetRow.findElement(By.cssSelector(String.format(".rt-td:nth-child(%d)", order))).getText();
    }

    public void checkDataInNewRow() {
        getAllRowWithValue();
        targetRow = listOfTableRow.get(listRowsWithValue.size() - 1);
        for (FormInputName inputName : FormInputName.values()) {
            String actualValue = null;
            String expectedValue = null;

            switch (inputName) {
                case firstName:
                    actualValue = getValueFromTargetRow(FormInputName.firstName);
                    expectedValue = newFirstName;
                    break;
                case lastName:
                    actualValue = getValueFromTargetRow(FormInputName.lastName);
                    expectedValue = newLastName;
                    break;
                case age:
                    actualValue = getValueFromTargetRow(FormInputName.age);
                    expectedValue = newAge;
                    break;
                case userEmail:
                    actualValue = getValueFromTargetRow(FormInputName.userEmail);
                    expectedValue = newUserEmail;
                    break;
                case salary:
                    actualValue = getValueFromTargetRow(FormInputName.salary);
                    expectedValue = newSalary;
                    break;
                case department:
                    actualValue = getValueFromTargetRow(FormInputName.department);
                    expectedValue = newDepartment;
                    break;
            }

            System.out.printf("Expected value %s in %s but actual is %s%n", expectedValue, inputName, actualValue);

            assertThat(String.format("Expected value %s in %s but actual is %s", expectedValue, inputName, actualValue), expectedValue, is(equalTo(actualValue)));
        }
    }

    private void getAllRowWithValue() {
        listRowsWithValue = listOfTableRow.stream()
                .map(row -> row.findElement(By.cssSelector(".rt-td:nth-child(1)")))
                .filter(cell -> !cell.getText().trim().isEmpty())
                .collect(Collectors.toList());
    }

    public enum FormInputName {
        firstName,
        lastName,
        age,
        userEmail,
        salary,
        department;

    }
}
