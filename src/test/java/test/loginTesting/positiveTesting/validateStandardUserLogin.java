package test.loginTesting.positiveTesting;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
public class validateStandardUserLogin { // Class name always starts with a capital letter.
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup(); // You don't need this line of code. this refers to selenium in version 3.
        WebDriver driver = new ChromeDriver();

        //logs in to the system
        validateUsers(driver,"standard_user");
        validateUsers(driver,"problem_user");
        validateUsers(driver,"performance_glitch_user");
        validateUsers(driver,"error_user");
        validateUsers(driver,"visual_user");

    driver.quit();
    }
    public static void validateUsers(WebDriver driver,String username){
        driver.get("https://www.saucedemo.com");

        //finds the elements username by id and sends it the username "standard_user"
        WebElement userName = driver.findElement(By.id("user-name"));
        userName.sendKeys(username);
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("secret_sauce");
        password.submit(); // where is the submit button element? please don't use things you don't familiar with.

        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, "https://www.saucedemo.com/inventory.html", "wrong URL.");

        WebElement titleElement = driver.findElement(By.cssSelector("#header_container > div.header_secondary_container > span"));
        String title = titleElement.getText(); // what this variable presents on the website? I want to know what the title is.
        Assert.assertEquals(title, "Products", "Title validation failed.");
        System.out.println("Assertions passed."); // you don't need to print out a message. TestNG will do that for you with the assertion.
    }
}