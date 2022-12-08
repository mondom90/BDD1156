package Steps;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Logowanie {

    private static WebDriver driver;

    @BeforeAll
    public static void setDriver() {
        // otwarcie przegldarki
        driver = new ChromeDriver(); // konstruktor wywolujacy przegladarke;
        driver.manage().window().maximize();
    }

    @Given("Uzytkownik otwiera przegldarke")
    public void uzytkownik_otwiera_przegladarke() {
        System.out.println("Krok 1 - Uzytkownik otwiera przegldarke");
        //driver = new ChromeDriver(); // konstruktor wywolujacy przegladarke;
        //driver.manage().window().maximize();
    }

    @Given("Uzytkownik wpisuje adres strony internetowej")
    public void uzytkownik_wpisuje_adres_strony_internetowej() {
        System.out.println("Krok 2 - Uzytkownik wpisuje adres strony internetowej");
        driver.navigate().to("https://the-internet.herokuapp.com/login");
    }

    @When("Uzytkownik wpisuje poprawny login")
    public void uzytkownik_wpisuje_poprawny_login() {
        System.out.println("Krok 3 - Uzytkownik wpisuje poprawny login");
        WebElement usernameInput = driver.findElement(By.id("username"));
        usernameInput.clear();
        usernameInput.sendKeys("tomsmith");
        //driver.findElement(By.id("username")).sendKeys("tomsmith");
    }

    @When("Uzytkownik wpisuje poprawne haslo")
    public void uzytkownik_wpisuje_poprawne_haslo() {
        System.out.println("Krok 4 - Uzytkownik wpisuje poprawne haslo");
        driver.findElement(By.name("password")).sendKeys("SuperSecretPassword!");
    }

    @When("Uzytkownik klika przycisk Login")
    public void uzytkownik_klika_przycisk_login() {
        System.out.println("Krok 5 - Uzytkownik klika przycisk Login");

        WebElement loginButton = driver.findElement(By.tagName("button"));
        String textButton = loginButton.getText();
        System.out.println("PRZYCISK LOGIN ZAWIERA TEKST = " + textButton);
        loginButton.click();

        //driver.findElement(By.tagName("button")).click();
    }

    @Then("Uzytkownik zostaje poprawnie zalogowany do aplikacji")
    public void uzytkownik_zostaje_poprawnie_zalogowany_do_aplikacji() {
        System.out.println("Krok 6 - Uzytkownik zostaje poprawnie zalogowany do aplikacji");
        Assert.assertEquals("https://the-internet.herokuapp.com/secure", driver.getCurrentUrl());
        //driver.close();
    }

    @When("Uzytkownik wpisuje niepoprawne haslo")
    public void uzytkownik_wpisuje_niepoprawne_haslo() {
        driver.findElement(By.name("password")).sendKeys("niepoprawne haslo");
    }

    @Then("Uzytkownik nie zostaje poprawnie zalogowany do aplikacji")
    public void uzytkownik_nie_zostaje_poprawnie_zalogowany_do_aplikacji() {
        Assert.assertEquals("https://the-internet.herokuapp.com/login", driver.getCurrentUrl());
        //driver.close();
    }

    //And   Uzytkownik przechodzi na strone "https://the-internet.herokuapp.com/"
    @Given("Uzytkownik przechodzi na strone {string}")
    public void uzytkownik_przechodzi_na_strone(String adresStrony) {
        // adresStrony = "https://the-internet.herokuapp.com/"
        driver.navigate().to(adresStrony);
    }

    @When("Uzytkownik wprowadza login {string}")
    public void uzytkownik_wprowadza_login(String userName) {
        driver.findElement(By.id("username")).sendKeys(userName);
    }

    @When("Uzytkownik wprowadza haslo {string}")
    public void uzytkownik_wprowadza_haslo(String password) {
        driver.findElement(By.name("password")).sendKeys(password);
    }

    //When  Uzytkownik wprowadza login <loginek>
    @When("^Uzytkownik wprowadza loginek (.+)$")
    public void uzytkownik_wprowadza_login2(String loginek) {
        driver.findElement(By.id("username")).sendKeys(loginek);
    }


    @And("^Uzytkownik wprowadza haselko (.+)$")
    public void uzytkownik_wprowadza_haslo2(String haselko) {
        driver.findElement(By.name("password")).sendKeys(haselko);
    }

    @AfterAll
    public static void tearDriver() {
        driver.close();
    }

}