package carRentalServises.pages;

import carRentalServises.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {

    @FindBy(xpath = "//h2[text()='Login']")
    private WebElement loginHeader;

    @FindBy(name = "email")
    private WebElement emailField;

    @FindBy(name = "password")
    private WebElement passwordField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement loginButton;

    @FindBy(css = "div.text-red-500.text-xs.mt-1")
    private WebElement errorMessage;

    @FindBy(xpath = "//a[text()='Admin']")
    private WebElement adminButton;

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void enterEmail(String email) {
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void enterPassword(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public boolean isLoginButtonEnabled() {
        return loginButton.isEnabled();
    }

    public boolean isErrorMessageDisplayed() {
        return errorMessage.isDisplayed();
    }

    public String getErrorMessageText() {
        return errorMessage.getText();
    }

    public boolean isAdminButtonVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOf(adminButton));
            return adminButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isAdminButtonClickable() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(adminButton));
            return adminButton.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

}
