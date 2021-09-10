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
    private static final String URL = "https://accounts.google.com";

    private final ChromeDriver driver;

    @PostConstruct
    void postConstruct() throws InterruptedException {
        scrape("/signin");
    }

    public void scrape(final String value) throws InterruptedException {
        driver.get(URL + value);

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        System.out.println(" Page Title: " + driver.getTitle());

//        List<WebElement> anotherAcc = driver.findElements(By.cssSelector("form[method='post'] > span > section > div > div > div > div > ul:nth-child(3) > div[role='link']"));
//        anotherAcc.forEach(sub -> System.out.println(sub.getText() + " o"));
//        WebElement goToProfile = driver.findElementByCssSelector("div[data-pagelet='LeftRail'] > div > div > ul > li > div > a[role='link']");
        driver.findElementById("identifierId").sendKeys("admizbuzztara@gmail.com");
        Thread.sleep(1500);
        driver.findElementById("identifierNext").click();
        Thread.sleep(1500);
        driver.findElementByName("password").sendKeys("HELLOworld@123");
        Thread.sleep(1500);
        driver.findElementById("passwordNext").click();
        Thread.sleep(10000);
        System.out.println(driver.getTitle());

        //        List<WebElement> anotherAcc = driver.findElements(By.cssSelector("form[method='post'] > span > section > div > div > div > div > ul:nth-child(3) > div[role='link']"));
        driver.findElement(By.cssSelector("a[aria-label='Google apps']")).click();
        Thread.sleep(1500);
        driver.get("https://mail.google.com/mail/u/0/#inbox");
        Thread.sleep(3000);
        System.out.println(driver.getTitle());

        driver.close();
    }

}
