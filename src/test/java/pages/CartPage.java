package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CartPage {


    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(css = ".cart_item")
    private List<WebElement> cartItems;

    @FindBy(id = "continue-shopping")
    private WebElement continueShoppingButton;

    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public int getCartItemCount() {
        wait.until(ExpectedConditions.visibilityOfAllElements(cartItems));
        return cartItems.size();
    }

    public String getItemNameByIndex(int index) {
        return cartItems.get(index)
                .findElement(By.cssSelector(".inventory_item_name"))
                .getText();
    }

    public String getItemPriceByIndex(int index) {
        return cartItems.get(index)
                .findElement(By.cssSelector(".inventory_item_price"))
                .getText();
    }

    public void removeItemByIndex(int index) {
        cartItems.get(index)
                .findElement(By.cssSelector("button.cart_button"))
                .click();
    }

    public void clickContinueShopping() {
        continueShoppingButton.click();
    }

    public void clickCheckout() {
        checkoutButton.click();
    }
}
