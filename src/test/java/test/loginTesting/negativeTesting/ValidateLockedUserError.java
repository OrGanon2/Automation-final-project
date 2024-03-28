package test.loginTesting.negativeTesting;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class ValidateLockedUserError {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup(); // You don't need this line of code. this refers to selenium in version 3.
        WebDriver driver = new ChromeDriver();

        //logs in to the system
        validateLockedUsers(driver, "standard_user", "invalid_password", "Epic sadface: Username and password do not match any user in this service");
        validateLockedUsers(driver, "invalid_user", "secret_sauce", "Epic sadface: Username and password do not match any user in this service");
        validateLockedUsers(driver, "invalid_user", "invalid_password", "Epic sadface: Username and password do not match any user in this service");
        validateLockedUsers(driver, "", "secret_sauce", "Epic sadface: Username is required");
        validateLockedUsers(driver, "standard_user", "", "Epic sadface: Password is required");
        validateLockedUsers(driver, "", "", "Epic sadface: Username is required");

            driver.quit();
        }
        public static void validateLockedUsers(WebDriver driver,String username ,String password, String expectedErrorMessage){
            driver.get("https://www.saucedemo.com");
            assert driver.getCurrentUrl().equals("https://www.saucedemo.com/inventory.html") : "Something went wrong"; // Use the TestNG assertion instead of the assert keyword.

            //finds the elements username by id and sends it the username "standard_user"
            WebElement userName = driver.findElement(By.id("user-name"));
            WebElement Password = driver.findElement(By.id("password"));

            userName.sendKeys(username);
            Password.sendKeys(password);
            Password.submit();

            WebElement errorMessage = driver.findElement(By.cssSelector("[data-test='error']"));
            Assert.assertEquals(errorMessage.getText(), expectedErrorMessage, "Login scenario failed for username: " + username + ", password: " + password);
        }
}