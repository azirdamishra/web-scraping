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
    private static final String URL = "https://www.facebook.com";

    private final ChromeDriver driver;

    @PostConstruct
    void postConstruct() throws InterruptedException {
        scrape("/");
    }

    public void scrape(final String value) throws InterruptedException {
        driver.get(URL + value);
//        final WebElement words = driver.findElementByClassName("container"); //div of "words" elements
//        final List<WebElement> wordList = words.findElements(By.className("inner_content"));
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        System.out.println(" Page Title: " + driver.getTitle());
        WebElement email = driver.findElementById("email");
        email.sendKeys("mishradriza@gmail.com");

        WebElement pwd = driver.findElementById("pass");
        pwd.sendKeys("HELLOworld");

        //WebElement button = driver.findElementByXPath("//*input[@id=\"u_0_j_12\"]");
        //WebElement button = driver.findElementByXPath("//input[contains(@id, 'u_0_j']]");
        WebElement button = driver.findElement(By.name("login"));
        button.click();

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        System.out.println(" Logged in");

        Thread.sleep(3000);
        //System.out.println(driver.getTitle());
        //driver.get("https://www.facebook.com/?sk=welcome");

        WebElement goToProfile = driver.findElementByCssSelector("div[data-pagelet='LeftRail'] > div > div > ul > li > div > a[role='link']");
        goToProfile.click();


        WebElement textArea = driver.findElementByCssSelector("div[role='button'] > div > span[style*='-webkit-box-orient: ']");
        //WebElement textArea = driver.findElementByCssSelector("span[style*='webkit-box-orient']");
        textArea.click();
        Thread.sleep(3000); //wait 30 secs

        WebElement textExpanded = driver.findElementByCssSelector("div[role='presentation'] > div > div > div > div > div > div:nth-child(2) >  div[aria-describedby*='placeholder-']");
        textExpanded.click();
        textExpanded.sendKeys("Something here");

        Thread.sleep(3000);
        WebElement postButton = driver.findElement(By.cssSelector("div[aria-label='Post']"));
        postButton.click();
        Thread.sleep(3000);
        driver.close();
    }

}
