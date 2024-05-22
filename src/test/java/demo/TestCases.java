package demo;

import java.text.DecimalFormat;
//import org.bouncycastle.oer.its.ieee1609dot2.basetypes.Duration;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestCases {
    WebDriver driver;

    @BeforeClass
    public void setup() {
        System.out.println("Constructor: Driver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        System.out.println("Successfully created Driver");
    }

    @Test
    public void testcase01() throws InterruptedException {
        System.out.println("Start Test case: Testcase01");
        driver.get("https://www.flipkart.com");

        // Find the search box and send text as washing machine
        Boolean testflow01 = SeleniumWrapper.sendKeys_FlipKart(
                By.xpath("//input[@title='Search for Products, Brands and More']"),"Washing Machine", driver);
        Assert.assertEquals(true, testflow01);

        // Click on popularity
        Boolean testflow2 = SeleniumWrapper.click_FlipKart(By.xpath("//div[text()='Popularity']"), driver);

        Assert.assertEquals(true, testflow2);

        // print the number of items
        List<WebElement> ratings = SeleniumWrapper.findElementsFlipKart(By.xpath("//span[@class='Y1HWO0']/div"), driver);
        int count = 0;
        for (WebElement rating : ratings) {
            String rate = rating.getText();
            Thread.sleep(100);
            double rat = Double.parseDouble(rate);
            if (rat <= 4 && rat > 0) {
                count++;
            }
        }
        System.out.println("Count of the products with rating less than or eqaul to 4 is " + count);
        System.out.println("End Test case: Testcase01");
    }

    @Test
    public void testcase02() throws InterruptedException {
        System.out.println("Start Test case: Testcase02");

        // Find the search box and send text as iphone
        Boolean testflow01 = SeleniumWrapper
                .sendKeys_FlipKart(By.xpath("//input[@title='Search for products, brands and more']"), "iphone", driver);
        Assert.assertEquals(true, testflow01);

        // get discounts , titles and store it in a list
        ////div[@class='UkUFwK']/span/ancestor::div[@class='yKfJKb row']/child::div/child::div[@class='KzDlHZ']
        List<WebElement> discounts = SeleniumWrapper
                .findElementsFlipKart(By.xpath("//div[@class='UkUFwK']/span"), driver);
        List<WebElement> titles = SeleniumWrapper.findElementsFlipKart(By.xpath(
                "//div[@class='UkUFwK']/span/ancestor::div[@class='yKfJKb row']/div/div[1][@class='KzDlHZ']"),
                driver);

       
        for (int i = 0; i < discounts.size(); i++) {
            Thread.sleep(100);
            String str = discounts.get(i).getText();
            String str1 = str.replaceAll("[^0-9]", "");
            int num = Integer.parseInt(str1);
            if (num > 17 && num < 50) {

                System.out.println(titles.get(i).getText() + " " + str);
               
                
            }
        }

        System.out.println("End Test case: Testcase02");
    }

    @Test
    public void testcase03() throws InterruptedException {
        System.out.println("Start Test case: Testcase03");
       
        Boolean testflow01 = SeleniumWrapper.sendKeys_FlipKart(
                By.xpath("//input[@title='Search for products, brands and more']"),"Coffee mug", driver);
        Assert.assertEquals(true, testflow01);
        Thread.sleep(2000);
        // scroll the page
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,300);");
        Thread.sleep(100);

        Boolean testflow02 = SeleniumWrapper
                .click_FlipKart(By.xpath("//div[contains(@title,'4')]//div[@class='XqNaEv']"), driver);
        Assert.assertEquals(true, testflow02);

        // get the image urls
        List<WebElement> imgURLs = SeleniumWrapper.findElementsFlipKart(
                By.xpath("//span[@class='Wphh3N']/ancestor::div[@class='slAVV4']//div[@class='_4WELSP']/img"), driver);

        // get the titles
        List<WebElement> titles = SeleniumWrapper.findElementsFlipKart(
                By.xpath("//span[@class='Wphh3N']/ancestor::div[@class='slAVV4']//a[@class='wjcEIp']"), driver);

        // get the review counts
        List<WebElement> reviews = SeleniumWrapper.findElementsFlipKart(By.xpath("//span[@class='Wphh3N']"), driver);
       
        // create an arraylist,get the text of reviews
        // take only integer value from thr text
        // convert string to int
        // add integer values to an array
        ArrayList<Integer> array = new ArrayList<>();
        for (WebElement review : reviews) {
            Thread.sleep(100);
            String text = review.getText();
            String str1 = text.replaceAll("[^0-9]", "");
            int num1 = Integer.parseInt(str1);
            array.add(num1);
        }

        // sort an array
        Collections.sort(array);

        // reverse an array
        Collections.reverse(array);

        // print the Title add image URL of the 5 items with highest number of reviews

        for (int i = 0; i < 5; i++) {
            String formattedNumber = " ";
            DecimalFormat formattter = new DecimalFormat("#,###");
            formattedNumber = formattter.format(array.get(i));
            WebElement element = driver
                    .findElement(By.xpath("//span[@class='Wphh3N' and contains(text(), '" + formattedNumber + "')]"));
            for (int j = 0; j < reviews.size(); j++) {
                Thread.sleep(100);
                if (element.getText().contains(reviews.get(j).getText())) {
                    System.out.println("review: " + element.getText());
                    System.out.println("title: " + titles.get(j).getText());
                    System.out.println("imageURL: " + imgURLs.get(j).getAttribute("src"));
                    System.out.println();
                    
                }
            }
        }
        System.out.println("End Test case: Testcase03");

    }

    @AfterClass
    public void endTest() {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();

    }

}
