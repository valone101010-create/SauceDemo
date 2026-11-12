import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SauceDemoLocators {

    private WebDriver driver;

    public SauceDemoLocators(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getUsernameField() {
        return driver.findElement(By.id("user name"));
    }

    public WebElement getPasswordField() {
        return driver.findElement(By.id("password"));
    }

    public WebElement getLoginButton() {
        return driver.findElement(By.id("login-button"));

    }

    public WebElement getFirstNameFieldCheckout() {
        return driver.findElement(By.id("first-name"));
    }

    WebElement getContinueButtonCheckout() {
        return driver.findElement(By.id("continue"));
    }

    public WebElement getUsernameFieldByName() {
        return driver.findElement(By.name("user-name"));
    }

    public WebElement getLoginContainer() {
        return driver.findElement(By.className("login_container"));
    }

    public WebElement getInventoryList() {
        return driver.findElement(By.className("inventory_list"));
    }

    public WebElement getErrorContainer() {
        return driver.findElement(By.className("error-message-container"));
    }

    public WebElement getShoppingCartLink() {
        return driver.findElement(By.className("shopping_cart_link"));
    }

    public WebElement getFirstInputField() {
        return driver.findElement(By.tagName("input"));
    }

    public WebElement getFirstButton() {
        return driver.findElement(By.tagName("button"));
    }

    public WebElement getPageTitleHeader() {
        return driver.findElement(By.tagName("h3"));
    }

    public WebElement getResetAppStateLink() {
        return driver.findElement(By.linkText("Reset App State"));
    }

    public WebElement getAllItemsLink() {
        return driver.findElement(By.linkText("All Items"));
    }

    public WebElement getLogoutLink() {
        return driver.findElement(By.linkText("Logout"));
    }

    public WebElement getLinkByPartialText() {
        return driver.findElement(By.partialLinkText("App"));
    }

    public WebElement getLogoutLinkPartial() {
        return driver.findElement(By.partialLinkText("Log"));
    }
}
