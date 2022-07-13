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
    public List<WebElement> checkUncheckButton;

    @FindBy(css = "input[type='checkbox']")
    public List<WebElement> checkBoxes;

    @FindBy(css = "#ctl00_MainContent_fmwOrder_ddlProduct>option")
    public List<WebElement> productInfoProduct;

    @FindBy(id = "ctl00_MainContent_fmwOrder_txtQuantity")
    public WebElement productInfoQuantity;

    @FindBy(xpath = "(//ol)[2]//input")
    public List<WebElement> addressInfoInputBoxes;

    @FindBy(css = "#ctl00_MainContent_fmwOrder_cardList input")
    public List<WebElement> creditCardRadio;

    @FindBy(css = "#ctl00_MainContent_fmwOrder_cardList label")
    public List<WebElement> creditCardRadioText;

    @FindBy(id = "ctl00_MainContent_fmwOrder_TextBox6")
    public WebElement cardNumberInputBox;

    @FindBy(id = "ctl00_MainContent_fmwOrder_TextBox1")
    public WebElement expireDateInputBox;

    @FindBy(id = "ctl00_MainContent_fmwOrder_InsertButton")
    public WebElement processButton;

    @FindBy(css = "#ctl00_MainContent_orderGrid tr")
    public List<WebElement> newOrderAdded;
    //size should be greater than 9 if there is a new order
    //size should be zero if deleted

    @FindBy(css = "#ctl00_MainContent_orderGrid tr:nth-child(2) td")
    public List<WebElement> newOrderDetailsTableInfo;
    //don't use 0 and 13

    @FindBy(id = "ctl00_MainContent_btnDelete")
    public WebElement deleteSelectedButton;

    @FindBy(id = "ctl00_MainContent_orderMessage")
    public WebElement orderDeletedText;

    public void clickMenuItem(String text) {
        for (WebElement menuItem : webOrdersMenuItems) {
            if (menuItem.getText().equals(text)) {
                menuItem.click();
                break;
            }
        }
    }

    public void clickCheckUncheckButton(String text) {
        for (WebElement webElement : checkUncheckButton) {
            if (webElement.getText().equals(text)) {
                webElement.click();
                break;
            }
        }
    }

    public void clickProductOption(String text) {
        for (WebElement product : productInfoProduct) {
            if (product.getText().equals(text)) {
                product.click();
                break;
            }
        }
    }

    public void clickCreditCardTypeRadioButton(DataTable dataTable) {
        for (int i = 0; i < creditCardRadio.size(); i++) {
            if (creditCardRadioText.get(i).getText().equals(dataTable.asList().get(0))) {
                creditCardRadio.get(i).click();
                break;
            }
        }
    }

}
