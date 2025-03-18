package com.tmobile.steps;

import com.tmobile.pages.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Steps {

    private WebDriver driver;
    private HomePage homePage;
    private DevicesPage devicesPage;
    private ProductPage productPage;
    private CartPage cartPage;
    private static final Logger logger = LoggerFactory.getLogger(Steps.class);

    @Before
    public void setUp() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--headless");
        options.addArguments("--width=1920", "--height=1080");
        driver = new FirefoxDriver(options);

        homePage = new HomePage(driver);
        devicesPage = new DevicesPage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("Użytkownik otwiera przeglądarkę i przechodzi na stronę {string}")
    public void openHomePage(String url) {
        homePage.open(url);
    }

    @When("Wybiera sekcję Urządzenia i wybiera Smartwatche w sekcji Bez abonamentu")
    public void selectDeviceCategory() {
        homePage.clickDevicesMenu();
        devicesPage.selectSmartwatches();
    }

    @When("Wybiera pierwszy smartwatch z listy")
    public void selectFirstSmartwatch() {
        productPage.selectFirstDevice();
    }

    @When("Dodaje smartwatch do koszyka")
    public void addSmartwatchToCart() {
        productPage.capturePrices();
        productPage.addToCart();
    }

    @Then("Koszyk zawiera wybrany telefon z odpowiednią ceną i ratą")
    public void verifyProductInCart() {
        String expectedPriceNow = productPage.getCapturedPriceNow();
        String expectedPriceMonthly = productPage.getCapturedPriceMonthly();

        String actualCartPriceNow = cartPage.getCartPriceNow();
        String actualCartPriceMonthly = cartPage.getCartPriceMonthly();

        logger.info("Oczekiwana cena teraz: " + expectedPriceNow);
        logger.info("Cena w koszyku: " + actualCartPriceNow);
        logger.info("Oczekiwana rata miesięczna: " + expectedPriceMonthly);
        logger.info("Rata w koszyku: " + actualCartPriceMonthly);

        assertEquals(expectedPriceNow, actualCartPriceNow, "Cena 'Do zapłaty teraz' nie zgadza się!");
        assertEquals(expectedPriceMonthly, actualCartPriceMonthly, "Cena 'Do zapłaty miesięcznie' nie zgadza się!");
        assertTrue(cartPage.isPriceVisible(), "Cena produktu nie jest widoczna.");
        assertTrue(cartPage.isMonthlyRateVisible(), "Rata miesięczna produktu nie jest widoczna.");
    }

    @Then("Użytkownik jest na stronie głównej")
    public void userIsOnHomePage() {
        assertTrue(homePage.isOnHomePage(), "Użytkownik NIE jest na stronie głównej!");
    }

    @Then("Użytkownik wraca na stronę główną")
    public void userReturnsToHomePage() {
        cartPage.returnToHomePage();
    }

    @Then("Ikona koszyka uległa zmianie")
    public void cartIconChanges() {
        assertTrue(homePage.isCartIconFilledVisible(), "Ikona koszyka NIE zmieniła się!");
    }
}
