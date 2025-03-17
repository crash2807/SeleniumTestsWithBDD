package com.tmobile.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage extends BasePage {

    private final By cartPriceNow = By.cssSelector("[data-qa='BKT_TotalupFront']");
    private final By cartPriceMonthly = By.cssSelector("[data-qa='BKT_TotalMonthly']");
    private final By goToHomePage = By.cssSelector(".logo-panel > img:nth-child(1)");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public String getCartPriceNow() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(cartPriceNow));
        return element.getText().trim();
    }

    public String getCartPriceMonthly() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(cartPriceMonthly));
        return element.getText().trim();
    }

    public boolean isPriceVisible() {
        return driver.findElement(cartPriceNow).isDisplayed();
    }

    public boolean isMonthlyRateVisible() {
        return driver.findElement(cartPriceMonthly).isDisplayed();
    }

    public void returnToHomePage(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement logoButton = wait.until(ExpectedConditions.elementToBeClickable(goToHomePage));
        logoButton.click();
    }
}
