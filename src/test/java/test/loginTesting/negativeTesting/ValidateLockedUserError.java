package test.loginTesting.negativeTesting;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
public class ValidateLockedUserError {
    public static void main(String[] args) {
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
            Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/", "Something went wrong.");

            //finds the elements username by id and sends it the username "standard_user"
            WebElement userName = driver.findElement(By.id("user-name"));
            WebElement Password = driver.findElement(By.id("password"));

            userName.sendKeys(username);
            Password.sendKeys(password);
            driver.findElement(By.cssSelector("#login-button")).click();

            WebElement errorMessage = driver.findElement(By.cssSelector("[data-test='error']"));
            Assert.assertEquals(errorMessage.getText(), expectedErrorMessage, "Login scenario failed for username: " + username + ", password: " + password);
        }
}