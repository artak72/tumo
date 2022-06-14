package POM;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.logging.Logger;

public class BasePage {
    private static final int TIMEOUT = 45;
    public WebDriver driver;
    Logger logger = Logger.getLogger(String.valueOf(BasePage.class));

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        WebDriverWait wait = new WebDriverWait(driver, 40);
    }

    protected void waitForElementToBeClickable(WebElement element) {
        try {
            new WebDriverWait(driver, TIMEOUT).until(ExpectedConditions.elementToBeClickable(element));
        } catch (WebDriverException e) {
            throw new WebDriverException("Element is not clickable");
        }
    }

    public void waitForElementToBeAbsent(WebElement element) {
        int time = 0;
        try {
            while (element.isDisplayed()) {
                Thread.sleep(500L);
                time++;
                if (time == TIMEOUT * 2) {
                    throw new InterruptedException();
                }
            }
        } catch (StaleElementReferenceException | NoSuchElementException e) {
            return;
        } catch (InterruptedException e) {
            throw new RuntimeException("Element is not absent");
        }
    }

    private void pageReadyStateToComplete() {
        forceWait(200);
        ExpectedCondition<Boolean> pageLoadCondition = driver -> (boolean) ((JavascriptExecutor) driver)
                .executeScript("return document.readyState == 'complete'");
        try {
            new WebDriverWait(driver, TIMEOUT).until(pageLoadCondition);
        } catch (WebDriverException e) {
            throw new WebDriverException("Page is not loaded");
        }
    }

    protected void waitForElementToBeVisible(WebElement element) {
        try {
            new WebDriverWait(driver, TIMEOUT).until(ExpectedConditions.visibilityOf(element));
        } catch (WebDriverException e) {
            throw new WebDriverException("Element is not visible");
        }
    }

    private void ajaxJquery() {
        forceWait(200);
        ExpectedCondition<Boolean> pageLoadCondition = driver -> (boolean) ((JavascriptExecutor) driver)
                .executeScript("return window.jQuery == undefined ? true : window.jQuery.active == 0");
        try {
            new WebDriverWait(driver, TIMEOUT).until(pageLoadCondition);
        } catch (WebDriverException e) {
            throw new WebDriverException("Page is not loaded");
        }
    }

    public void waitForPageToLoad() {
        pageReadyStateToComplete();
        ajaxJquery();
    }

    protected void highlightElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid red'", element);
    }


    protected void unHighlightElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='0px'", element);
    }

    protected void forceWait(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected void click(WebElement element) {
        new WebDriverWait(driver, TIMEOUT).until(ExpectedConditions.elementToBeClickable(element));
        highlightElement(element);
        element.click();
//        unHighlightElement(element);
    }

    protected void type(WebElement element, String text) {
        waitForElementToBeVisible(element);
        element.clear();
        element.sendKeys(text, Keys.ENTER);
    }

}
