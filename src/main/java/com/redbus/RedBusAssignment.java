package com.redbus;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Hello world!
 */
public class RedBusAssignment {
    public static void main(String[] args) {
        //launch browser
        ChromeOptions chromeoptions = new ChromeOptions();
        chromeoptions.addArguments("--start-maximized"); // another way for maximize window
        WebDriver wd = new ChromeDriver(chromeoptions);

        // visit red bus website 
        wd.get("https://www.redbus.in/");
        WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(10));
        WebElement from = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[normalize-space()='From']")));
        Actions action = new Actions(wd);
        action.moveToElement(from).click().sendKeys("Bangalore").perform();
        

       

        



        
    }
}
