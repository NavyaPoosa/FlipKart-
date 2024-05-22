package demo;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class SeleniumWrapper{
   
    public static Boolean click_FlipKart(By locator, WebDriver driver){
        Boolean success;
        try {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
                wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
                WebElement clickButton = driver.findElement(locator);
                clickButton.click();
                Thread.sleep(2000);
                success = true;
        }catch(Exception e){
            System.out.println("Exception occured while clicking:");
            e.printStackTrace();
            success = false;
        }
        return success;
    }

    public static Boolean sendKeys_FlipKart(By locator,String textToSend, WebDriver driver){
        Boolean success;
        try{
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
                wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
                WebElement textInputBox = driver.findElement(locator);
                textInputBox.click();
                Thread.sleep(1000);
                textInputBox.clear();
                textInputBox.sendKeys(textToSend);
                Thread.sleep(1000);
                textInputBox.sendKeys(Keys.ENTER);
                Thread.sleep(1000);
                success = true;
            }catch (Exception e){
                System.out.println("Exception Occured!" + e. getMessage());
                success = false;
            }
            return success;
    }

    public static List<WebElement> findElementsFlipKart(By locator, WebDriver driver){
        try{
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
            List<WebElement> elements = driver.findElements(locator);
            return elements;
            
        }
        catch(Exception e){
                System.out.println("Exception Occured!" + e. getMessage());
                return null;
                    }
        
    } 



}