package com.vytrack.pages;
// according to page object model design, we have to create  corresponded page class for each page of application
// login page= login page class
// every page class will store webelements and methods regarding the class

import com.vytrack.utilities.ConfigurationReader;
import com.vytrack.utilities.Driver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPage extends BasePage {
    @FindBy(id = "prependedInput") // this line will initialize web element
    public WebElement userNameInput;
    @FindBy(id = "prependedInput2")// without FindBy , web element will be null
    public WebElement passwordInput;
    @FindBy(id = "_submit")
    public WebElement loginButton;
    @FindBy(css = "[class='alert alert-error']")
    public WebElement warningMessage;

    // we create the constructor which is used to initialize instant variables
    public LoginPage() {

        // it is mandatory if you want to use @FindBy annotation
        // this means LoginPage class
        // Driver.get() returns webdriver object
        PageFactory.initElements(Driver.get(), this);
    }
    // reusable login method
    // just call this method and provide username and password as parameters
    // Keys.ENTER is to subtitute login click

    public void login(String userName, String password) {
        userNameInput.sendKeys(userName);
        passwordInput.sendKeys(password, Keys.ENTER);
    }

    public void login(String role) {
        String userName = "";
        String password = ConfigurationReader.getProperty("password");
        switch (role) {
            case "driver":
                userName = ConfigurationReader.getProperty("driver.username");
                break;
            case "store manager":
                userName = ConfigurationReader.getProperty("store.manager.username");
                break;
            case "sales manager":
                userName = ConfigurationReader.getProperty("sales.manager.username");
                break;
            default:
                throw new RuntimeException("Invalid role!");
        }
        login(userName, password);

    }

}

