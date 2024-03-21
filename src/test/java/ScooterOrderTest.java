import Scooter.MainPage;
import Scooter.OrderPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@RunWith(Parameterized.class)
public class ScooterOrderTest {
    public static final String URL = "https://qa-scooter.praktikum-services.ru/";
    private WebDriver driver;
    private final int orderButton;
    private final String name;
    private final String lastname;
    private final String address;
    private final String station;
    private final String phoneNumber;
    private final String date;
    private final String period;
    private final String color;
    private final String comment;

    public ScooterOrderTest(int orderButton, String name, String lastName, String address, String station, String phoneNumber, String date, String period, String color, String comment) {
        this.orderButton = orderButton;
        this.name = name;
        this.lastname = lastName;
        this.address = address;
        this.station = station;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.period = period;
        this.color = color;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] ScooterOrderTestData() {
        return new Object[][]{
                {1, "Мария", "Иванова", "ул. Пушкина, 36", "Лубянка", "+79279272727", "01.03.2024", "трое суток", "black", "не опаздывайте"},
                {2, "Ольга", "Петрова", "ул. Победы, 36", "Черкизовская", "+79179171717", "03.03.2024", "пятеро суток", "grey", "жду не жождусь"}
        };
    }

    @Before
    public void startUp() {
        driver = new ChromeDriver();
        driver.get(URL);
    }

    @Test
    public void makeOrder() {
        MainPage mainPage = new MainPage(driver);
        OrderPage orderPage = new OrderPage(driver);
        mainPage.waitForLoadProfileData();
        mainPage.clickCookieAgreeButton();
        mainPage.clickOrderButton(orderButton);
        orderPage.makeOrderForm(name, lastname, address, station, phoneNumber, date, period, color, comment);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}