package com.tmobile.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class HomePage extends BasePage {

    private final By devicesMenu = By.xpath("//button[contains(text(),'Urządzenia')]");
    private final By logoIcon = By.cssSelector("div.container:nth-child(3) > div:nth-child(1) > a:nth-child(1) > svg:nth-child(1) > rect:nth-child(1)");
    private final By cartIconFilled = By.cssSelector("div.ml-auto:nth-child(3) > a:nth-child(1) > div:nth-child(3)");
    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void open(String url) {
        driver.get(url);
        acceptCookies();
    }

    public void clickDevicesMenu() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement menuButton = wait.until(ExpectedConditions.elementToBeClickable(devicesMenu));
        menuButton.click();
    }

    public boolean isOnHomePage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(logoIcon)).isDisplayed();
    }

    public boolean isCartIconFilledVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(cartIconFilled)).isDisplayed();
    }
}
