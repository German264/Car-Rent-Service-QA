package carRentalServises.core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ApplicationManager {

    public WebDriver driver;
    public WebDriverWait wait;
    public BasePage basePage;

    public void init() {
        String browser = System.getProperty("browser", "chrome");

        // Выводим в консоль выбранный браузер
        System.out.println("Browser from System.getProperty('browser'): " + System.getProperty("browser"));


        // Проверяет значение переменной browser и в зависимости от результата инициализирует нужный драйвер
        switch (browser.toLowerCase()) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            default: // Это резервный сценарий на случай, если значение browser не совпадает ни с одним из указанных случаев
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
        }
        //driver = new ChromeDriver();
       // driver.manage().window().setPosition(new Point(2500, 0));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15)); // неявное
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(70)); // ожидание загрузки страницы
        wait = new WebDriverWait(driver, Duration.ofSeconds(7));
        driver.get("https://car-rental-cymg8.ondigitalocean.app/");
        basePage = new BasePage(driver,wait);
    }

    public BasePage getBasePage() {
        return basePage;
    }

    public void stop() {
        driver.quit();
    }
}
