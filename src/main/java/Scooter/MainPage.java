package Scooter;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;


public class MainPage {

    protected WebDriver driver;
    // локатор кнопки согласия на куки
    private final By cookieAgreeButton = By.className("App_CookieButton__3cvqF");
    // локатор кнопки "Заказать" в хедере
    private final By headerOrderButton = By.className("Button_Button__ra12g");
    // локатор кнопки "Заказать" внизу страницы
    private final By finishOrderButton = By.className("Home_FinishButton__1_cWm");
    //Локатор выпадающего списка в разделе «Вопросы о важном»
    private final By mainPageFaqSection = By.className("Home_FAQ__3uVm4");

    //конструктор класса
    public MainPage(WebDriver driver) {
        this.driver = driver;
    }
    //ожидание загрузки страницы
    public void waitForLoadProfileData() {
        new WebDriverWait(driver, 3000).until(driver ->
                driver.findElement(finishOrderButton).getText() != null);
    }
    //клик по кнопке согласия с куками
    public void clickCookieAgreeButton() {
        driver.findElement(cookieAgreeButton).click();
    }
    //клик на верхнюю или нижнюю кнопку "Заказать"
    public void clickOrderButton (int orderButton) {
        if (orderButton == 0) {
            driver.findElement(headerOrderButton).click();
        } else {
            WebElement element = driver.findElement(finishOrderButton);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
            driver.findElement(finishOrderButton).click();
        }
    }
    //прокрутить страницу до выпадающего списка "Вопросы о важном"
    public void scrollPageTillSectionFaq() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",
                driver.findElement(mainPageFaqSection));
    }
    // Получение текста выпадающего списка по номеру в списке
    public String sectionFaqGetText(int listItemNumber){
        driver.findElement(By.id("accordion__heading-"+listItemNumber)).click();
        return driver.findElement(By.xpath(".//*[@id='accordion__panel-"+listItemNumber+"']/p")).getText();
    }

}