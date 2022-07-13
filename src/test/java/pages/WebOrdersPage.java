package pages;

import io.cucumber.datatable.DataTable;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Driver;

import java.util.List;

public class WebOrdersPage {

    WebDriver driver;

    public WebOrdersPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(css = "#ctl00_menu>li")
    public List<WebElement> webOrdersMenuItems;

    @FindBy(css = ".CheckUncheck>a")
    public List<WebElement> uncheckBox;

    @FindBy(css = "input[type='checkbox']")
    public List<WebElement> checkBox;

    @FindBy(css = "#ctl00_MainContent_fmwOrder_ddlProduct>option")
    public List<WebElement> productInformation;

    @FindBy(id = "ctl00_MainContent_fmwOrder_txtQuantity")
    public WebElement productQuantity;

    @FindBy(xpath = "(//ol)[2]//input")
    public List<WebElement> addressInputBox;

    @FindBy(css = "#ctl00_MainContent_fmwOrder_cardList input")
    public List<WebElement> creditCardRadioButton;

    @FindBy(css = "#ctl00_MainContent_fmwOrder_cardList label")
    public List<WebElement> creditCardRadioButtonText;

    @FindBy(id = "ctl00_MainContent_fmwOrder_TextBox6")
    public WebElement cardNumberInputBox;

    @FindBy(id = "ctl00_MainContent_fmwOrder_TextBox1")
    public WebElement expireDateInputBox;

    @FindBy(id = "ctl00_MainContent_fmwOrder_InsertButton")
    public WebElement processButton;

    @FindBy(css = "#ctl00_MainContent_orderGrid tr")
    public List<WebElement> addedNewOrder;


    @FindBy(css = "#ctl00_MainContent_orderGrid tr:nth-child(2) td")
    public List<WebElement> newOrderTableDetails;


    @FindBy(id = "ctl00_MainContent_btnDelete")
    public WebElement deleteButton;

    @FindBy(id = "ctl00_MainContent_orderMessage")
    public WebElement deleteOrderText;

    public void clickMenuItem(String text) {
        for (WebElement menuItem : webOrdersMenuItems) {
            if (menuItem.getText().equals(text)) {
                menuItem.click();
                break;
            }
        }
    }

    public void clickCheckUncheckButton(String text) {
        for (WebElement webElement : uncheckBox) {
            if (webElement.getText().equals(text)) {
                webElement.click();
                break;
            }
        }
    }

    public void clickProductOption(String text) {
        for (WebElement product : productInformation) {
            if (product.getText().equals(text)) {
                product.click();
                break;
            }
        }
    }

    public void clickCreditCardTypeRadioButton(DataTable dataTable) {
        for (int i = 0; i < creditCardRadioButton.size(); i++) {
            if (creditCardRadioButtonText.get(i).getText().equals(dataTable.asList().get(0))) {
                creditCardRadioButton.get(i).click();
                break;
            }
        }
    }

}
