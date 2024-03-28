package test.sanityTesting;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BuyProductsScenario {
   @Test(testName = "standard User Login Secnario")
      public  void StandardUserTest() {
       WebDriver driver = new ChromeDriver();
       //logs in to the system
       driver.get("https://www.saucedemo.com");
       //finds the elements username by id and sends it the username "standard_user"
       driver.findElement(By.id("user-name")).sendKeys("standard_user");
       driver.findElement(By.id("password")).sendKeys("secret_sauce");
       driver.findElement(By.id("password")).submit();

       //checks if the secondary title is products
       assert driver.getCurrentUrl().equals("https://www.saucedemo.com/inventory.html") : "Something went wrong";
       WebElement products = driver.findElement(By.cssSelector("#header_container > div.header_secondary_container > span"));
       String titleProductCheck = products.getText();
       Assert.assertEquals(titleProductCheck, "Products", "Title validation failed.");

       //adds items to the cart.
       driver.findElement(By.cssSelector("#add-to-cart-sauce-labs-backpack")).click();
       driver.findElement(By.cssSelector("#add-to-cart-sauce-labs-bike-light")).click();
       driver.findElement(By.cssSelector("#shopping_cart_container > a")).click();

       //Validates that there are 2 products at the cart.
       String itemsInCart = driver.findElement(By.className("shopping_cart_badge")).getText();
       Assert.assertEquals(itemsInCart, "2", "Wrong amount of items in the cart.");
       driver.findElement(By.className("shopping_cart_link")).click();
       driver.findElement(By.cssSelector("#checkout")).click();


       //add user details and proceed to checkout.
       assert driver.getCurrentUrl().equals("https://www.saucedemo.com/checkout-step-one.html") : "Something went wrong";
       driver.findElement(By.id("first-name")).sendKeys("Bat");
       driver.findElement(By.id("last-name")).sendKeys("man");
       driver.findElement(By.id("postal-code")).sendKeys("7721315");
       driver.findElement(By.className("cart_button")).click();

       //validate the checkout overview  title and presses the finish button.
       assert driver.getCurrentUrl().equals("https://www.saucedemo.com/checkout-step-two.html") : "Something went wrong";
       WebElement Checkout = driver.findElement(By.cssSelector("#header_container > div.header_secondary_container > span"));
       String titleCheckoutCheck = Checkout.getText();
       Assert.assertEquals(titleCheckoutCheck, "Checkout: Overview", "something went wrong.");
       driver.findElement(By.className("cart_button")).click();

       //validate the checkout complete title
       assert driver.getCurrentUrl().equals("https://www.saucedemo.com/checkout-complete.html") : "Something went wrong";
       WebElement checkoutComplete = driver.findElement(By.cssSelector("#header_container > div.header_secondary_container > span"));
       String checkoutCompleteCheck = checkoutComplete.getText();
       Assert.assertEquals(checkoutCompleteCheck, "Checkout: Complete!", "something went wrong.");
       //checks  first text after checkout
       WebElement orderComplete = driver.findElement(By.cssSelector("#checkout_complete_container > h2"));
       String text = orderComplete.getText();
       Assert.assertEquals(text, "Thank you for your order!", "something went wrong.");

       //checks second text after checkout
       WebElement orderDispached = driver.findElement(By.cssSelector("#checkout_complete_container > div"));
       String orderDispachedCheck = orderDispached.getText();
       Assert.assertEquals(orderDispachedCheck, "Your order has been dispatched, and will arrive just as fast as the pony can get there!", "something went wrong.");

       driver.quit();

   }

}
