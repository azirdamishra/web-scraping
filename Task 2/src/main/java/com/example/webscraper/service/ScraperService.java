package com.example.webscraper.service;

import lombok.AllArgsConstructor;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@AllArgsConstructor
public class ScraperService {
    //private static final String URL = "https://relatedwords.org/relatedto/";
    //private static final String URL = "https://blog.hubspot.com/";
    private static final String URL = "https://www.influencer.in/";

    private final ChromeDriver driver;

    @PostConstruct
    void postConstruct() throws InterruptedException {
        scrape("influencer-profile/");
    }

    public void scrape(final String value) throws InterruptedException {
        driver.get(URL + value);
        final WebElement words = driver.findElementByClassName("container"); //div of "words" elements
        final List<WebElement> wordList = words.findElements(By.className("inner_content"));
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        System.out.println(" Page Title: " + driver.getTitle());

        List<WebElement> box = driver.findElements(By.className("camp_box"));
        //WebElement box = driver.findElements(By.className("camp_box")); //does not work because the method only returns a list
        List<WebElement> influencers = box.get(0).findElements(By.className("col-md-4"));
        //System.out.println(influencers.size());
        System.out.println(influencers.get(2).getText()); //3rd influencer in the list and we get all elements
        WebElement influ = influencers.get(2);
        List<WebElement> texts = influ.findElements(By.className("list2"));
        //System.out.println(texts.get(0).getText()); //this has content
        //System.out.println(texts.get(1).getText()); //this is empty
        System.out.println("----------------x----------------");
        String text = texts.get(0).getText(); //this is empty
        System.out.println("Number of Instagram followers: " + text.split("\n")[1]);


       WebDriverWait wait = new WebDriverWait(driver, 10);
       wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("meet_influ")));
       WebElement parent = driver.findElementByXPath("//*[@id=\"horizontalTab3\"]/ul/li[2]");
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", parent);
        Dimension liSize = driver.findElementByXPath("//*[@id=\"horizontalTab3\"]/ul/li[2]/a").getSize();
        driver.findElementByXPath("//*[@id=\"horizontalTab3\"]/ul/li[2]/a").click();

        WebElement tabs = driver.findElement(By.id("horizontalTab3"));
        List<WebElement> subList = tabs.findElements(By.tagName("li"));

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //subList.forEach(sub -> System.out.println(sub.getText() + " o")); //contains the followers number !! but not the changed one
        String[] s = subList.get(3).getText().split("\n");
        System.out.println("Number of Youtube followers: " + s[1]);
        driver.close();
    }

}
