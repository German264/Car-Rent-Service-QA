package CarRentalServises.core;

import carRentalServises.core.ApplicationManager;
import carRentalServises.pages.HomePage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestBase {

    protected ApplicationManager app;
    protected HomePage homePage;

    @BeforeMethod
    public void setUp() {
        app = new ApplicationManager();      // Создаём менеджер приложения
        app.init();                          // Инициализируем WebDriver и WebDriverWait
        homePage = new HomePage(app.driver, app.wait);  // Передаём зависимости на главную страницу
    }

    @AfterMethod
    public void tearDown() {
        if (app != null) {
            app.stop();                      // Закрываем браузер
        }
    }
}

