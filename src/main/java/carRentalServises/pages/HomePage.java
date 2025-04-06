package carRentalServises.pages;

import carRentalServises.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {

    @FindBy(xpath = "//a[text()='Log in']")
    private WebElement loginLink;

    public HomePage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void clickLogin() {
        loginLink.click();
    }

    public boolean isLoginButtonDisplayed() {
        return loginLink.isDisplayed();
    }

    public boolean isLoginLinkEnabled() {
        return loginLink.isEnabled();
    }
}
