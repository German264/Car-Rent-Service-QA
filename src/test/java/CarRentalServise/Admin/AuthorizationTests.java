package CarRentalServise.Admin;


import CarRentalServise.core.TestBase;
import carRentalServises.pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class AuthorizationTests extends TestBase {

    @Test
    public void LoginButtonIsDisplayedPositiveTest() {
        // Ожидаем, что кнопка "Log in" отображается на главной странице
        Assert.assertTrue(homePage.isLoginButtonDisplayed(), "Кнопка 'Log in' не отображается на главной странице");
    }

    @Test
    public void NavigationToLoginFormPositiveTest() {
        // Переход на страницу авторизации
        homePage.clickLogin();

        // Ожидаем, пока заголовок "Login" станет видимым
        WebElement loginHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='Login']")));

        // Проверка, что заголовок "Login" отображается
        Assert.assertTrue(loginHeader.isDisplayed(), "Заголовок 'Login' отсутствует");
    }

    @Test
    public void LoginFormElementsPositiveTest() {
        // Переход на страницу авторизации
        homePage.clickLogin();

        // Создание экземпляра страницы
        LoginPage loginPage = new LoginPage(driver, wait);

        // Ожидание, пока форма логина загрузится
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email")));

        // Ожидание, пока поле email будет отображаться
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email")));
        Assert.assertTrue(emailField.isDisplayed(), "Поле для ввода email не отображается");

        // Ожидание, пока поле пароля будет отображаться
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
        Assert.assertTrue(passwordField.isDisplayed(), "Поле для ввода пароля не отображается");

        // Проверка, что кнопка логина неактивна при пустых полях
        Assert.assertFalse(loginPage.isLoginButtonEnabled(), "Кнопка 'Login' должна быть неактивной при пустом email и пароле");

        // Проверка, что кнопка логина отображается
        WebElement loginButton = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//button[@type='submit' and text()='Login']")
        ));
        Assert.assertTrue(loginButton.isDisplayed(), "Кнопка 'Login' должна быть видна");

    }

    @Test
    public void LoginButtonActivationPositiveTest() {
        // Переход на страницу авторизации
        homePage.clickLogin();

        // Создание экземпляра страницы
        LoginPage loginPage = new LoginPage(driver, wait);

        // Ожидание появления поля для ввода email
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("email")));

        // Ввод данных в поля email и пароль
        loginPage.enterEmail("admin@gmail.com");
        loginPage.enterPassword("Yyyyyyy12345!");

        // Проверка, что кнопка 'Login' активна
        Assert.assertTrue(loginPage.isLoginButtonEnabled(), "Кнопка 'Login' не активна после ввода данных");
    }

    @Test
    public void AdminButtonAppearsAfterSuccessfulLoginPositiveTest() {
        // Переход на страницу авторизации
        homePage.clickLogin();

        // Создание экземпляра страницы
        LoginPage loginPage = new LoginPage(driver, wait);

        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email")));

        // Ввод корректных данных администратора
        loginPage.enterEmail("admin@gmail.com");
        loginPage.enterPassword("Yyyyyyy12345!");

        // Клик по кнопке входа
        loginPage.clickLoginButton();

        // Ожидание, пока появится кнопка Admin
        WebElement adminButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Admin']")));

        // Проверка, что кнопка Admin отображается и кликабельна
        Assert.assertTrue(adminButton.isDisplayed(), "Кнопка 'Admin' не отображается после входа");
        Assert.assertTrue(adminButton.isEnabled(), "Кнопка 'Admin' неактивна");
    }

    @Test
    public void LoginButtonDisabledWithEmptyEmailNegativeTest() {
        // Переход на страницу авторизации
        homePage.clickLogin();

        // Создание экземпляра страницы
        LoginPage loginPage = new LoginPage(driver, wait);

        // Ожидаем, что поле пароля станет доступным
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));

        // Вводим пароль
        passwordField.sendKeys("Yyyyyyy12345!");

        // Проверка, что кнопка 'Login' не активна
        Assert.assertFalse(loginPage.isLoginButtonEnabled(), "Кнопка 'Login' должна быть неактивной при пустом email");
    }

    @Test
    public void LoginButtonDisabledWithEmptyPasswordNegativeTest() {
        // Переход на страницу авторизации
        homePage.clickLogin();

        // Создание экземпляра страницы
        LoginPage loginPage = new LoginPage(driver, wait);

        // Ожидаем, что поле email станет доступным
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email")));

        // Вводим email
        loginPage.enterEmail("admin@gmail.com");

        // Проверка, что кнопка 'Login' не активна
        Assert.assertFalse(loginPage.isLoginButtonEnabled(), "Кнопка 'Login' должна быть неактивной при пустом пароле");
    }

    @Test
    public void InvalidPasswordErrorMessageNegativeTest() {
        // Переход на страницу авторизации
        homePage.clickLogin();

        // Создание экземпляра страницы
        LoginPage loginPage = new LoginPage(driver, wait);

        // Ожидаем, что поле email станет доступным
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email")));

        // Ввод корректного email и некорректного пароля
        loginPage.enterEmail("admin@gmail.com");
        loginPage.enterPassword("invalidpass");

        // Клик по кнопке входа
        loginPage.clickLoginButton();

        // Проверка появления сообщения об ошибке
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Сообщение об ошибке не появилось");
        Assert.assertEquals(loginPage.getErrorMessageText(),
                "Password must include an uppercase letter, a number, and a special character (# ? ! @ $ % ^ & * -)",
                "Текст сообщения об ошибке не соответствует ожидаемому");
    }
}

