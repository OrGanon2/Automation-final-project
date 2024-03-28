package test.sanityTesting;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BuyProductsScenario {
   @Test(testName = "standard User Login Secnario")
      public  void StandardUserTest() {
       WebDriverManager.chromedriver().setup(); // You don't need this line of code. this refers to selenium in version 3.
       WebDriver driver = new ChromeDriver();
       //logs in to the system
       driver.get("https://www.saucedemo.com");
       //finds the elements username by id and sends it the username "standard_user"
       driver.findElement(By.id("user-name")).sendKeys("standard_user");
       driver.findElement(By.id("password")).sendKeys("secret_sauce");
       driver.findElement(By.id("password")).submit();

       //checks if the secondary title is products
       assert driver.getCurrentUrl().equals("https://www.saucedemo.com/inventory.html") : "Something went wrong"; // Use the TestNG assertion instead of the assert keyword.
       WebElement titleElement = driver.findElement(By.cssSelector("#header_container > div.header_secondary_container > span")); // what this variable presents on the website? I want to know what the title is.
       String title = titleElement.getText();
       Assert.assertEquals(title, "Products", "Title validation failed.");
       System.out.println("Assertions passed."); // you don't need to print out a message. TestNG will do that for you with the assertion.

       //adds items to the cart.
       driver.findElement(By.cssSelector("#add-to-cart-sauce-labs-backpack")).click();
       driver.findElement(By.cssSelector("#add-to-cart-sauce-labs-bike-light")).click();
       driver.findElement(By.cssSelector("#shopping_cart_container > a")).click();

       //Validates that there are 2 products at the cart.
       String itemsInCart = driver.findElement(By.className("shopping_cart_badge")).getText();
       assert itemsInCart.equals("2") : "Wrong amount of items."; // Use the TestNG assertion instead of the assert keyword.
       driver.findElement(By.className("shopping_cart_link")).click();
       driver.findElement(By.cssSelector("#checkout")).click();


       //add user details and proceed to checkout.
       assert driver.getCurrentUrl().equals("https://www.saucedemo.com/checkout-step-one.html") : "Something went wrong"; // Use the TestNG assertion instead of the assert keyword.
       driver.findElement(By.id("first-name")).sendKeys("Bat");
       driver.findElement(By.id("last-name")).sendKeys("man");
       driver.findElement(By.id("postal-code")).sendKeys("7721315");
       driver.findElement(By.className("cart_button")).click();

       //validate the checkout overview  title and presses the finish button.
       assert driver.getCurrentUrl().equals("https://www.saucedemo.com/checkout-step-two.html") : "Something went wrong"; // Use the TestNG assertion instead of the assert keyword.
       WebElement titleElement2 = driver.findElement(By.cssSelector("#header_container > div.header_secondary_container > span"));
       String title2 = titleElement2.getText(); // what this variable presents on the website? I want to know what the title is.
       Assert.assertEquals(title2, "Checkout: Overview", "something went wrong.");
       driver.findElement(By.className("cart_button")).click();

       //validate the checkout complete title
       assert driver.getCurrentUrl().equals("https://www.saucedemo.com/checkout-complete.html") : "Something went wrong"; // Use the TestNG assertion instead of the assert keyword.
       WebElement titleElement3 = driver.findElement(By.cssSelector("#header_container > div.header_secondary_container > span")); // what this variable presents on the website? I want to know what the title is.
       String title3 = titleElement3.getText(); // what this variable presents on the website? I want to know what the title is.
       Assert.assertEquals(title3, "Checkout: Complete!", "something went wrong.");
       //checks  first text after checkout
       WebElement textValidate = driver.findElement(By.cssSelector("#checkout_complete_container > h2")); // what this variable presents on the website? I want to know what the title is.
       String text = textValidate.getText(); // what this variable presents on the website? I want to know what the title is.
       Assert.assertEquals(text, "Thank you for your order!", "something went wrong.");

       //checks second text after checkout
       WebElement textValidate2 = driver.findElement(By.cssSelector("#checkout_complete_container > div")); // what this variable presents on the website? I want to know what the title is.
       String text2 = textValidate2.getText(); // what this variable presents on the website? I want to know what the title is.
       Assert.assertEquals(text2, "Your order has been dispatched, and will arrive just as fast as the pony can get there!", "something went wrong.");

       driver.quit();
   }
}