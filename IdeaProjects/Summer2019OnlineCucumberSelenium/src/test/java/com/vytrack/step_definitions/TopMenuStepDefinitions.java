package com.vytrack.step_definitions;

import com.vytrack.pages.LoginPage;
import io.cucumber.java.en.Then;

public class TopMenuStepDefinitions {
    LoginPage loginPage = new LoginPage();

    @Then("user navigates to {string} then to {string}")
    public void user_navigates_to_then_to(String module, String sunmodule) {
        loginPage.navigateTo(module, sunmodule);
    }


}
