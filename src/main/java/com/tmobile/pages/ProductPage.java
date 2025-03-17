package com.tmobile.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;

public class ProductPage extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger(ProductPage.class);

    private final By firstProduct  = By.cssSelector("[data-qa='LST_ProductCard0']");
    private final By addToCartButton = By.cssSelector(".vertical_view > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > section:nth-child(1) > button:nth-child(1)");
    private final By priceNow = By.cssSelector(".vertical_view > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1)");
    private final By priceMonthly = By.cssSelector(".vertical_view > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1)");

    private String capturedPriceNow;
    private String capturedPriceMonthly;

    public ProductPage(WebDriver driver) {
        super(driver);
    }
    public void capturePrices() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement priceNowElement = wait.until(ExpectedConditions.visibilityOfElementLocated(priceNow));
        WebElement priceMonthlyElement = wait.until(ExpectedConditions.visibilityOfElementLocated(priceMonthly));

        capturedPriceNow = extractNumbers(priceNowElement.getText().trim());
        capturedPriceMonthly = extractNumbers(priceMonthlyElement.getText().trim());

        logger.info("Cena teraz: " + capturedPriceNow);
        logger.info("Cena miesięczna: " + capturedPriceMonthly);
    }

    public void selectFirstDevice() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement device = wait.until(ExpectedConditions.visibilityOfElementLocated(firstProduct));
        device.click();
        logger.info("Wybrano pierwszy smartwatch z listy.");
    }

    public void addToCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement addToCartAction = wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        addToCartAction.click();
    }

    public String getCapturedPriceNow() {
        return capturedPriceNow;
    }

    public String getCapturedPriceMonthly() {
        return capturedPriceMonthly;
    }

    private String extractNumbers(String text) {
        return text.replaceAll("[^0-9]", "");
    }
}
