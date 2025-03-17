package com.tmobile.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DevicesPage extends BasePage {

    private final By smartwatchesOption = By.cssSelector(".menu > li:nth-child(1) > div:nth-child(2) > div:nth-child(1) > ul:nth-child(1) > li:nth-child(4) > ul:nth-child(2) > li:nth-child(2) > a:nth-child(1) > div:nth-child(1) > span:nth-child(1)");

    public DevicesPage(WebDriver driver) {
        super(driver);
    }

    public void selectSmartwatches() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement smartwatchesButton = wait.until(ExpectedConditions.elementToBeClickable(smartwatchesOption));
        smartwatchesButton.click();
    }
}
