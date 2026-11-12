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

public class InventoryPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(css = ".inventory_item")
    private List<WebElement> inventoryItems;

    @FindBy(css = ".shopping_cart_link")
    private WebElement cartLink;

    @FindBy(css = ".shopping_cart_badge")
    private WebElement cartBadge;

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public int getItemCount() {
        return inventoryItems.size();
    }

    public String getItemNameByIndex(int index) {
        return inventoryItems.get(index)
                .findElement(By.cssSelector(".inventory_item_name"))
                .getText();
    }

    public String getItemPriceByIndex(int index) {
        return inventoryItems.get(index)
                .findElement(By.cssSelector(".inventory_item_price"))
                .getText();
    }

    public void addItemToCartByIndex(int index) {
        inventoryItems.get(index)
                .findElement(By.cssSelector("button.btn_inventory"))
                .click();
    }

    public void goToCart() {
        cartLink.click();
        wait.until(ExpectedConditions.urlContains("cart.html"));
    }

    public String getCartBadgeCount() {
        try {
            return cartBadge.getText();
        } catch (Exception e) {
            return "0";
        }
    }
}
