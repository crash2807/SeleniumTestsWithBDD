package com.tmobile.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void acceptCookies() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement acceptButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#didomi-notice-agree-button")));
            acceptButton.click();
            System.out.println("Cookies zostały zaakceptowane.");
        } catch (Exception e) {
            System.out.println("Nie znaleziono okna cookies, kontynuuję test.");
        }
    }
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
