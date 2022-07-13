package steps;

import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.LoginPage;
import pages.WebOrdersPage;
import utils.Driver;
import utils.Waiter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SmartBearSteps {

    WebDriver driver;
    LoginPage loginPage;
    WebOrdersPage webOrdersPage;

    @Before
    public void setup() {
        driver = Driver.getDriver();
        loginPage = new LoginPage();
        webOrdersPage = new WebOrdersPage();
    }


    @Given("user is on {string}")
    public void userIsOn(String url) {
        driver.get(url);
    }

    @When("user enters username as {string}")
    public void userEntersUsernameAs(String userName) {
        loginPage.usernameInputBox.sendKeys(userName);
    }

    @And("user enters password as {string}")
    public void userEntersPasswordAs(String password) {
        loginPage.passwordInputBox.sendKeys(password);
    }


    @Then("user should see {string} Message")
    public void userShouldSeeMessage(String text) {
        Assert.assertEquals(text, loginPage.invalidLoginAttemptText.getText());
    }

    @Then("user should be routed to {string}")
    public void userShouldBeRoutedTo(String url) {
        Assert.assertEquals(url, driver.getCurrentUrl());
    }

    @And("validate below menu items are displayed")
    public void validateBelowMenuItemsAreDisplayed(DataTable dataTable) {
        for (int i = 0; i < dataTable.asList().size(); i++) {
            Assert.assertEquals(dataTable.asList().get(i), webOrdersPage.webOrdersMenuItems.get(i).getText());
        }
    }

    @When("user clicks on {string} button")
    public void userClicksOnButton(String button) {
        switch (button) {
            case "Check All":
            case "Uncheck All":
                webOrdersPage.clickCheckUncheckButton(button);
                break;
            case "Process":
                webOrdersPage.processButton.click();
                break;
            case "Login":
                loginPage.loginButton.click();
                break;
            case "Delete Selected":
                webOrdersPage.deleteSelectedButton.click();
                break;
            default:
                System.out.println("The button is not defined correctly!!!");
        }

    }

    @Then("all rows should be checked")
    public void allRowsShouldBeChecked() {
        for (WebElement checkBox : webOrdersPage.checkBoxes) {
            Assert.assertTrue(checkBox.isSelected());
        }
    }

    @Then("all rows should be unchecked")
    public void allRowsShouldBeUnchecked() {
        for (WebElement checkBox : webOrdersPage.checkBoxes) {
            Assert.assertFalse(checkBox.isSelected());
        }
    }

    @When("user clicks on {string} menu item")
    public void userClicksOnMenuItem(String menuItem) {
        webOrdersPage.clickMenuItem(menuItem);
    }

    @And("user selects {string} as product")
    public void userSelectsAsProduct(String product) {
        webOrdersPage.clickProductOption(product);
    }

    @And("user enters {int} as quantity")
    public void userEntersAsQuantity(int num) {
        webOrdersPage.productInfoQuantity.sendKeys(Integer.toString(num));
    }

    @And("user enters all address information")
    public void userEntersAllAddressInformation(DataTable dataTable) {
        for (int i = 0; i < dataTable.asList().size(); i++) {
            webOrdersPage.addressInfoInputBoxes.get(i).sendKeys(dataTable.asList().get(i));
        }
    }

    @And("user enters all payment information")
    public void userEntersAllPaymentInformation(DataTable dataTable) {

        webOrdersPage.clickCreditCardTypeRadioButton(dataTable);
        webOrdersPage.cardNumberInputBox.sendKeys(dataTable.asList().get(1));
        webOrdersPage.expireDateInputBox.sendKeys(dataTable.asList().get(2));
    }

    @Then("user should see their order displayed in the List of All Orders table")
    public void userShouldSeeTheirOrderDisplayedInTheTable() {
        Assert.assertTrue(webOrdersPage.newOrderAdded.size() > 9);
    }

    @And("validate all information entered displayed correct with the order")
    public void validateAllInformationEnteredDisplayedCorrectWithTheOrder(DataTable dataTable) {
        for (int i = 1; i < webOrdersPage.newOrderDetailsTableInfo.size() - 1; i++) {
            if (i == 4){
                Assert.assertEquals(new SimpleDateFormat("MM/dd/yyyy").format(new Date()), webOrdersPage.newOrderDetailsTableInfo.get(i).getText());
            }
            else Assert.assertEquals(dataTable.asList().get(i - 1), webOrdersPage.newOrderDetailsTableInfo.get(i).getText());
        }
    }

    @Then("validate all orders are deleted from the List of All Orders")
    public void validateAllOrdersAreDeletedFromTheListOfAllOrders() {
        Assert.assertTrue(webOrdersPage.newOrderAdded.size() < 2);
    }

    @And("validate user sees {string} Message")
    public void validateUserSeesMessage(String deletedMessage) {
        Assert.assertTrue(webOrdersPage.orderDeletedText.getText().contains(deletedMessage));
    }
}