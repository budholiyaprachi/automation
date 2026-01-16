package com.redbus;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
        action.moveToElement(from).click().sendKeys("mumbai").perform();

       By searchcategorylocator =  By.xpath("//div[contains(@class,\"searchCategory\")]");
       List<WebElement> searchlist =  wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(searchcategorylocator, 2));

       System.out.println("total suggestion found :" + searchlist.size());
       WebElement locationsearchlist =   searchlist.get(0);

       //chaining of webelements
       By listitem =  By.xpath(".//div[contains(@class,\"listItem\")]");
       List<WebElement> locationlist =  locationsearchlist.findElements(listitem);
       System.out.println(locationlist.size());
       
       for(WebElement city : locationlist){
        if(city.getText().equalsIgnoreCase("mumbai"))
        {
            city.click();
            break;

        }}

        // Focus on TO Text Box 

        WebElement to = wd.switchTo().activeElement();
        to.sendKeys("Pune");

       By toSearchcategorylocator =  By.xpath("//div[contains(@class,\"searchCategory\")]");
       List<WebElement> toSearchlist =  wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(toSearchcategorylocator, 2));
       WebElement toLocationsearchlist =   toSearchlist.get(0);

       By toListitem =  By.xpath(".//div[contains(@class,\"listItem\")]");
       List<WebElement> toLocationlist =  toLocationsearchlist.findElements(toListitem);
       System.out.println(toLocationlist.size());
       
       for(WebElement city : toLocationlist){
        if(city.getText().equalsIgnoreCase("Pune"))
        {
            city.click();
            break;

        }}
      WebElement srchbtn = wd.findElement(By.xpath("//button[@aria-label='Search buses']"));
      srchbtn.click();

     
       WebElement filterAC= wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@role='checkbox' and starts-with(@aria-label,'AC')]")));
       filterAC.click();

       WebElement filterSeats = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@aria-label,'Single Seats')]")));
       filterSeats.click();

       WebElement status = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(@class,\"subtitle\")]")));
       wait.until(ExpectedConditions.textToBePresentInElement(status,"buses"));
       System.out.println(status.getText());

       
       By tupleWrapper = By.xpath("//li[contains(@class,\"tupleWrapper\")]");
       By busNameLocator = By.xpath("//div[contains(@class,\"busTitleWrapper\")]");


      JavascriptExecutor js = (JavascriptExecutor) wd;
      //js.executeScript( "arguments[0].scrollIntoView({behavior:'smooth'});",busList.get(busList.size() - 3));

      while(true) // thiss entine code will perform lazy loading , means scrolling down slowly till all the buses get loaded.
      {
        List<WebElement> busList= wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(tupleWrapper));
        List<WebElement> endoflist = wd.findElements(By.xpath("//span[contains(@text(),\'End of List\')]"));

        if(!endoflist.isEmpty())
        {
            break;

        }
        js.executeScript( "arguments[0].scrollIntoView({behavior:'smooth'});",busList.get(busList.size() - 3));

      }
      List<WebElement> busList= wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(tupleWrapper));
      for(WebElement bus : busList)
      {
        String busName = bus.findElement(busNameLocator).getText();
        System.out.println(busName);
      }
      System.out.println("Total number of buses located with AC and Single seats Filter" + busList.size());






    
        

       

        



        
    }
}
