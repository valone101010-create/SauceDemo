package tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.AuthPage;
import pages.CartPage;
import pages.InventoryPage;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;
import java.time.Duration;

public class SauceDemoTests {
    private WebDriver driver;
    private WebDriverWait wait;
    private AuthPage authPage;
    private InventoryPage inventoryPage;
    private CartPage cartPage;

    private static final String URL = "https://www.saucedemo.com/";
    private static final String VALID_USER = "standard_user";
    private static final String VALID_PASS = "secret_sauce";

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.password_manager_leak_detection", false);
        options.setExperimentalOption("prefs", prefs);
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        authPage = new AuthPage(driver);
        authPage.open(URL);

    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }


    @Test
    public void testSuccessfulLogin() {
        authPage.loginAs(VALID_USER, VALID_PASS);
        wait.until(d -> d.getCurrentUrl().contains("inventory.html"));
        Assert.assertTrue("URL должен содержать inventory.html",
                driver.getCurrentUrl().contains("inventory.html"));
    }


    @Test
    public void testEmptyUsernameShowsError() {
        authPage.enterPassword(VALID_PASS);
        authPage.clickLogin();
        Assert.assertTrue("Должно отображаться сообщение об ошибке",
                authPage.isErrorDisplayed());
    }


    @Test
    public void testWrongPasswordShowsError() {
        authPage.loginAs(VALID_USER, "wrong_password");
        Assert.assertTrue("Должно отображаться сообщение об ошибке",
                authPage.isErrorDisplayed());
    }

    @Test
    public void testAddToCartAndVerifyItemInCart() {
        authPage.loginAs(VALID_USER, VALID_PASS);
        wait.until(d -> d.getCurrentUrl().contains("inventory.html"));

        inventoryPage = new InventoryPage(driver);
        String expectedName = inventoryPage.getItemNameByIndex(0);
        String expectedPrice = inventoryPage.getItemPriceByIndex(0);

        inventoryPage.addItemToCartByIndex(0);
        Assert.assertEquals("Счётчик корзины должен быть 1",
                "1", inventoryPage.getCartBadgeCount());

        inventoryPage.goToCart();
        cartPage = new CartPage(driver);

        Assert.assertEquals("Количество товаров в корзине", 1, cartPage.getCartItemCount());
        Assert.assertEquals("Имя товара в корзине", expectedName, cartPage.getItemNameByIndex(0));
        Assert.assertEquals("Цена товара в корзине", expectedPrice, cartPage.getItemPriceByIndex(0));
    }

    @Test
    public void testRemoveItemFromCart() {
        authPage.loginAs(VALID_USER, VALID_PASS);
        wait.until(d -> d.getCurrentUrl().contains("inventory.html"));

        inventoryPage = new InventoryPage(driver);
        inventoryPage.addItemToCartByIndex(0);
        inventoryPage.goToCart();

        cartPage = new CartPage(driver);
        Assert.assertEquals(1, cartPage.getCartItemCount());
        cartPage.removeItemByIndex(0);
        Assert.assertEquals("Корзина должна быть пуста после удаления",
                0, cartPage.getCartItemCount());
    }

    @Test
    public void testContinueShoppingReturnsToInventory() {
        authPage.loginAs(VALID_USER, VALID_PASS);
        wait.until(d -> d.getCurrentUrl().contains("inventory.html"));

        inventoryPage = new InventoryPage(driver);
        inventoryPage.addItemToCartByIndex(0);
        inventoryPage.goToCart();

        cartPage = new CartPage(driver);
        cartPage.clickContinueShopping();
        wait.until(d -> d.getCurrentUrl().contains("inventory.html"));
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"));
    }
}
