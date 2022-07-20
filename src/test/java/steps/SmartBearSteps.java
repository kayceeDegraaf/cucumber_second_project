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
import pages.SmartBearLoginPage;
import pages.SmartBearWebOrdersPage;
import utils.Driver;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SmartBearSteps {

    WebDriver driver;
    SmartBearLoginPage smartBearLoginPage;
    SmartBearWebOrdersPage smartBearWebOrdersPage;

    @Before
    public void setup() {
        driver = Driver.getDriver();
        smartBearLoginPage = new SmartBearLoginPage();
        smartBearWebOrdersPage = new SmartBearWebOrdersPage();
    }


    @Given("user is on {string}")
    public void userIsOn(String url) {
        driver.get(url);
    }

    @When("user enters username as {string}")
    public void userEntersUsernameAs(String userName) {
        smartBearLoginPage.usernameInputBox.sendKeys(userName);
    }

    @And("user enters password as {string}")
    public void userEntersPasswordAs(String password) {
        smartBearLoginPage.passwordInputBox.sendKeys(password);
    }


    @Then("user should see {string} Message")
    public void userShouldSeeMessage(String text) {
        Assert.assertEquals(text, smartBearLoginPage.invalidText.getText());
    }

    @Then("user should be routed to {string}")
    public void userShouldBeRoutedTo(String url) {
        Assert.assertEquals(url, driver.getCurrentUrl());
    }

    @And("validate below menu items are displayed")
    public void validateBelowMenuItemsAreDisplayed(DataTable dataTable) {
        for (int i = 0; i < dataTable.asList().size(); i++) {
            Assert.assertEquals(dataTable.asList().get(i), smartBearWebOrdersPage.webOrdersMenuItems.get(i).getText());
        }
    }

    @When("user clicks on {string} button")
    public void userClicksOnButton(String button) {
        switch (button) {
            case "Check All":
            case "Uncheck All":
                smartBearWebOrdersPage.clickCheckUncheckButton(button);
                break;
            case "Process":
                smartBearWebOrdersPage.processButton.click();
                break;
            case "Login":
                smartBearLoginPage.loginButton.click();
                break;
            case "Delete Selected":
                smartBearWebOrdersPage.deleteButton.click();
                break;
            default:
                System.out.println("Link text is not properly defined in the feature file!!!");
        }

    }

    @Then("all rows should be checked")
    public void allRowsShouldBeChecked() {
        for (WebElement checkBox : smartBearWebOrdersPage.checkBox) {
            Assert.assertTrue(checkBox.isSelected());
        }
    }

    @Then("all rows should be unchecked")
    public void allRowsShouldBeUnchecked() {
        for (WebElement checkBox : smartBearWebOrdersPage.checkBox) {
            Assert.assertFalse(checkBox.isSelected());
        }
    }

    @When("user clicks on {string} menu item")
    public void userClicksOnMenuItem(String menuItem) {
        smartBearWebOrdersPage.clickMenuItem(menuItem);
    }

    @And("user selects {string} as product")
    public void userSelectsAsProduct(String product) {
        smartBearWebOrdersPage.clickProductOption(product);
    }

    @And("user enters {int} as quantity")
    public void userEntersAsQuantity(int num) {
        smartBearWebOrdersPage.productQuantity.sendKeys(Integer.toString(num));
    }

    @And("user enters all address information")
    public void userEntersAllAddressInformation(DataTable dataTable) {
        for (int i = 0; i < dataTable.asList().size(); i++) {
            smartBearWebOrdersPage.addressInputBox.get(i).sendKeys(dataTable.asList().get(i));
        }
    }

    @And("user enters all payment information")
    public void userEntersAllPaymentInformation(DataTable dataTable) {

        smartBearWebOrdersPage.clickCreditCardTypeRadioButton(dataTable);
        smartBearWebOrdersPage.cardNumberInputBox.sendKeys(dataTable.asList().get(1));
        smartBearWebOrdersPage.expireDateInputBox.sendKeys(dataTable.asList().get(2));
    }

    @Then("user should see their order displayed in the List of All Orders table")
    public void userShouldSeeTheirOrderDisplayedInTheTable() {
        Assert.assertTrue(smartBearWebOrdersPage.addedNewOrder.size() > 9);
    }

    @And("validate all information entered displayed correct with the order")
    public void validateAllInformationEnteredDisplayedCorrectWithTheOrder(DataTable dataTable) {
        for (int i = 1; i < smartBearWebOrdersPage.newOrderTableDetails.size() - 1; i++) {
            if (i == 4){
                Assert.assertEquals(new SimpleDateFormat("MM/dd/yyyy").format(new Date()), smartBearWebOrdersPage.newOrderTableDetails.get(i).getText());
            }
            else Assert.assertEquals(dataTable.asList().get(i - 1), smartBearWebOrdersPage.newOrderTableDetails.get(i).getText());
        }
    }

    @Then("validate all orders are deleted from the List of All Orders")
    public void validateAllOrdersAreDeletedFromTheListOfAllOrders() {
        Assert.assertTrue(smartBearWebOrdersPage.addedNewOrder.size() < 2);
    }

    @And("validate user sees {string} Message")
    public void validateUserSeesMessage(String deletedMessage) {
        Assert.assertTrue(smartBearWebOrdersPage.deleteOrderText.getText().contains(deletedMessage));
    }
}