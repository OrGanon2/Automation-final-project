package test.loginTesting.positiveTesting;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
public class ValidateStandardUserLogin {
    public static void main(String[] args) {
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
        driver.findElement(By.cssSelector("#login-button")).click();


        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, "https://www.saucedemo.com/inventory.html", "wrong URL.");
        WebElement products = driver.findElement(By.cssSelector("#header_container > div.header_secondary_container > span"));
        String titleProductCheck = products.getText();
        Assert.assertEquals(titleProductCheck, "Products", "Title validation failed.");

        driver.quit();

    }
}