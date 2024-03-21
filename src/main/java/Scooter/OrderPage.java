package Scooter;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderPage {
    protected WebDriver driver;
    //Поле ввода имени
    private final By nameInput = By.xpath(".//input[@placeholder = '* Имя']");
    //Поле ввода фамилии
    private final By lastNameInput = By.xpath(".//input[@placeholder = '* Фамилия']");
    //Поле ввода адреса
    private final By addressInput = By.xpath(".//input[@placeholder = '* Адрес: куда привезти заказ']");
    //Поле ввода номера телефона
    private final By phoneNumberInput = By.xpath(".//input[@placeholder = '* Телефон: на него позвонит курьер']");
    //Поле выбора станции метро
    private final By metroStationButton = By.xpath(".//input[@placeholder = '* Станция метро']");
    //Кнопка "Далее"
    private final By nextButton = By.xpath(".//button[text()='Далее']");
    //Поле Когда привезти самокат
    private final By whenInput = By.xpath(".//input[@placeholder = '* Когда привезти самокат']");
    //Поле Срок аренды
    private final By periodInput = By.xpath(".//*[@class = 'Dropdown-root']");
    //Поле Комментария для курьера
    private final By commentInput = By.xpath(".//input[@placeholder = 'Комментарий для курьера']");
    //Кнопка "Заказать"
    private final By orderButton = By.xpath(".//div[@class = 'Order_Buttons__1xGrp']/button[text() = 'Заказать']");
    //Кнопка да в окне подтверждения заказа
    private final By yesButton = By.xpath(".//button[text() = 'Да']");
    //Окно с результатом оформления заказа
    private final By madeOrder = By.xpath(".//div[text() = 'Заказ оформлен']");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    //Ввод текста в поле Имя
    public void inputName(String name) {
        driver.findElement(nameInput).clear();
        driver.findElement(nameInput).sendKeys(name);
    }
    //Ввод текста в поле Фамилия
    public void inputLastName(String lastName) {
        driver.findElement(lastNameInput).clear();
        driver.findElement(lastNameInput).sendKeys(lastName);
    }
    //Ввод текста в поле Адрес
    public void inputAddress(String address) {
        driver.findElement(addressInput).clear();
        driver.findElement(addressInput).sendKeys(address);
    }
    //Выбор станции метро
    public void selectMetroStation (String station){
        driver.findElement(metroStationButton).click();
        WebElement element = driver.findElement(By.xpath(".//div[text()='"+ station +"']/.."));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        element.click();
    }
    //Ввод номера телефона
    public void inputPhoneNumber(String phoneNumber) {
        driver.findElement(phoneNumberInput).clear();
        driver.findElement(phoneNumberInput).sendKeys(phoneNumber);
    }
    //Нажатие на кнопку Далее
    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }
    //Ввести дату в поле Когда привезти самокат
    public void inputWhen (String date) {
        driver.findElement(whenInput).clear();
        driver.findElement(whenInput).sendKeys(date);
        driver.findElement(whenInput).sendKeys(Keys.ENTER);
    }
    //Выбрать срок аренды
    public void selectRentPeriod (String period) {
        driver.findElement(periodInput).click();
        driver.findElement(By.xpath(".//div[text() = '"+ period +"']")).click();
    }
    //Выбрать цвет самоката, нужно указать либо black либо grey
    public void selectColor (String color) {
        driver.findElement(By.id(color)).click();
    }
    //Ввести комментарий для курьера
    public void inputComment (String comment) {
        driver.findElement(commentInput).sendKeys(comment);
    }
    //Нажать на кнопку "Заказать"
    public void clickOrderButton () {
        driver.findElement(orderButton).click();
    }
    //Нажать на кнопку "Да"
    public void clickYesButton () {
        driver.findElement(yesButton).click();
    }
    //Проверка успешности заказа
    public void checkOrder () {
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(madeOrder)).isDisplayed();
    }
    //Метод полного оформления заказа
    public void makeOrderForm(String name, String lastName, String address, String station, String phoneNumber, String date, String period, String color, String comment) {
        inputName(name);
        inputLastName(lastName);
        inputAddress(address);
        selectMetroStation(station);
        inputPhoneNumber(phoneNumber);
        clickNextButton();
        inputWhen(date);
        selectRentPeriod(period);
        selectColor(color);
        inputComment(comment);
        clickOrderButton();
        clickYesButton();
        checkOrder();
    }
}