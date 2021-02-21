package com.vytrack.pages;
// everything that is common among pages can go here
// for example top menu elements don't belong to specific page so we can keep them here
// top menu appears on every single page

import com.vytrack.utilities.BrowserUtils;
import com.vytrack.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class BasePage {
    @FindBy(css = "div[class='loader-mask shown']")
    public WebElement loaderMask;

    @FindBy(css = "h1[class='oro-subtitle']")
    public WebElement pageSubTitle;

    @FindBy(css = "#user-menu > a'")
    public WebElement userName;

    @FindBy(linkText = "Logout")
    public WebElement logOutLink;

    @FindBy(linkText = "My User")
    public WebElement myUser;

    public BasePage() {
        // this method requires to provide webdriver object for @FindBy
        // and page class
        // this means this page class
        PageFactory.initElements(Driver.get(), this);
    }

    public boolean waitUntilLoaderMaskDisappear() {
        WebDriverWait wait = new WebDriverWait(Driver.get(), 30);
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[class='loader-mask shown']")));
            return true;
        } catch (NoSuchElementException e) {
            System.out.println("Loader mask not found!");
            e.printStackTrace();
            return true; // no loader mask, all good, return true
        } catch (WebDriverException e) {
            e.printStackTrace();
        }
        return false;
    }

    /*
    This method stands for navigation in vytrack app
    provide module name: for example "Fleet" as a String and Submodule name: for example  "Vehicles" as a String
    based on this values , locators will be created
     @param moduleName
    @param subModuleName
    normalize-space()  same line .trim() in java
     */
    public void navigateTo(String moduleName, String subModuleName) {
        Actions actions= new Actions(Driver.get());
        String moduleLocator = "//*[normalize-space()='" + moduleName + "' and @class='title title-level-1']";
        String subModuleLocator = "//*[normalize-space()='" + subModuleName + "' and @class='title title-level-2']";


        WebDriverWait wait = new WebDriverWait(Driver.get(), 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(moduleLocator)));

        WebElement module = Driver.get().findElement(By.xpath(moduleLocator));
        wait.until(ExpectedConditions.visibilityOf(module));
        wait.until(ExpectedConditions.elementToBeClickable(module));

        waitUntilLoaderMaskDisappear();
        BrowserUtils.clickWithWait(module);
        // module.click(); // once we click on module submodule will be visible

        WebElement subModule = Driver.get().findElement(By.xpath(subModuleLocator));
        if (!subModule.isDisplayed()){
            actions.doubleClick(module).doubleClick().build().perform();
            try{
                wait.until(ExpectedConditions.visibilityOf(subModule));
            }catch (Exception ex){
                ex.printStackTrace();
                BrowserUtils.clickWithJS(module);
            }
        }
        BrowserUtils.clickWithWait(subModule);
        // after navigation wait until loader mask disappear
        BrowserUtils.waitForPageToLoad(10);

    }

    /*
     * @return page name, for example: Dashboard
     */
    public String getPageSubTitle() {
        //ant time we are verifying page name, or page subtitle, loader mask appears
        waitUntilLoaderMaskDisappear();
        BrowserUtils.waitForStaleElement(pageSubTitle);
        return pageSubTitle.getText();
    }

    public String getUserName() {
        waitUntilLoaderMaskDisappear();
        BrowserUtils.waitForVisibility(userName, 30);
        return userName.getText();
    }

    public void logOut() {
        BrowserUtils.wait(2);
        BrowserUtils.clickWithJS(userName);
        BrowserUtils.clickWithJS(logOutLink);
    }

    public void goToMyUser() {
        waitUntilLoaderMaskDisappear();
        BrowserUtils.waitForClickablility(userName, 30).click();
        BrowserUtils.waitForClickablility(myUser, 30).click();
    }
    public  void  waitForPageSubTitle (String pageSubTitleText) {
        new WebDriverWait(Driver.get(),10).until(ExpectedConditions.textToBe(By.cssSelector("h1[class='oro-subtitle'"), pageSubTitleText));
    }

}
