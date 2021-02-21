package com.vytrack.pages;

import com.vytrack.utilities.BrowserUtils;
import com.vytrack.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class CreateCarPage extends BasePage {

    @FindBy(css = "[id^='custom_entity_type_LicensePlate']")
    public WebElement licensePlateElement;

    @FindBy(name = "custom_entity_type[Driver]")
    public WebElement driverElement;

    @FindBy(name = "custom_entity_type[Location]")
    public WebElement locationElement;
    @FindBy(css = "[class='btn btn-success action-button']")
    public WebElement saveAndCloseButtonElement;

    @FindBy(xpath = "//li//button[contains(text(),'Save and New')]")
    public WebElement saveAndNew;
    @FindBy(xpath = "//li//button[contains(text(),'Save and Close')]")
    public WebElement saveAndCloseButtonFromMenuElement;

    @FindBy(css = "div[class='btn-group pull-right'] > a[data-toggle='dropdown']")
    public WebElement saveAndCloseToggle;


    @FindBy(css = "div[id*='FuelType']")
    public WebElement fuelTypeElement;
    //if this locator doesn't work, use [id^='uniform-custom_entity_type_Logo_file'] > span[class='action']
    @FindBy(name = "custom_entity_type[Logo][file]")
    public WebElement logoElement;

    @FindBy(name = "custom_entity_type[ModelYear]")
    public WebElement modelYearElement;

    @FindBy(name = "custom_entity_type[Color]")
    public WebElement colorElement;


    public WebElement selectTags(String tagName) {
        ////label[text()='Senior']/preceding-sibling: :input[@type='checkbox']
        String locator = "//label[text()='" + tagName + "']/ preceding-sibling::input[@type='checkbox']";
        WebElement checkBox = Driver.get().findElement(By.xpath(locator));
        BrowserUtils.waitForVisibility(checkBox, 30);
        BrowserUtils.waitForClickablility(checkBox, 30);
        if (!checkBox.isSelected()) {
            checkBox.click();
        }
        return checkBox;
    }

    public void selectFuelType(String fuelType) {
        String locator = "//div[@class='select2-result-label' and text()='" + fuelType + "']";
        BrowserUtils.waitForClickablility(fuelTypeElement, 30);
        fuelTypeElement.click();
        WebElement fuelTypeSelectionElement = Driver.get().findElement(By.xpath(locator));
        BrowserUtils.waitForClickablility(fuelTypeSelectionElement, 30);
        fuelTypeSelectionElement.click();
    }

    public void uploadLogo(String pathToTheFile) {
        BrowserUtils.waitForVisibility(logoElement, 30);
        logoElement.sendKeys(pathToTheFile);

    }

    public void clickSaveAndAddNew() {
        waitUntilLoaderMaskDisappear();
        BrowserUtils.clickWithWait(saveAndCloseToggle);
        BrowserUtils.waitForVisibility(saveAndNew, 5);
        BrowserUtils.clickWithWait(saveAndNew);
    }

    public void clickSaveAndClose() {
        waitUntilLoaderMaskDisappear();
        BrowserUtils.clickWithWait(saveAndCloseToggle);
        BrowserUtils.waitForVisibility(saveAndCloseButtonFromMenuElement, 5);
        BrowserUtils.clickWithWait(saveAndCloseButtonFromMenuElement);
    }

}
