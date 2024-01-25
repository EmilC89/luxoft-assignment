package org.assignment;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class AmazonPriceFinder {
    public static void main(String[] args) {
        // Setup WebDriver using WebDriverManager
        // Edge Browser
        WebDriverManager.edgedriver().clearDriverCache().setup();
        WebDriver driver = new EdgeDriver();

        // Chrome Browser
//        WebDriverManager.chromedriver().clearDriverCache().setup();
//        WebDriver driver = new ChromeDriver();



        try {
            driver.get("https://www.amazon.com");

            // Locate and fill the search box
            WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
            searchBox.sendKeys("smartwatch");
            searchBox.submit();

            // Create an instance of WebDriverWait with the specified timeout using Duration
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

            // Wait for the search results to load
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".s-result-list")));

            // Find all price elements
            List<WebElement> priceElements = driver.findElements(By.cssSelector(".a-price-whole"));

            int minPrice = Integer.MAX_VALUE;

            // Loop through all the price elements and find the lowest price
            for (WebElement priceElement : priceElements) {
                String priceText = priceElement.getText().replaceAll("[^0-9]", "");
                if (!priceText.isEmpty()) {
                    int price = Integer.parseInt(priceText);
                    if (price < minPrice) {
                        minPrice = price;
                    }
                }
            }

            System.out.println("Lowest Price Found: $" + minPrice);
        } finally {
            driver.quit();
        }
    }
}