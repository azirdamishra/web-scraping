package com.example.webscraper.service;

import lombok.AllArgsConstructor;
import org.openqa.selenium.By;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

import java.util.concurrent.TimeUnit;

@Service
@AllArgsConstructor
public class ScraperService {
    private static final String URL = "https://relatedwords.org/relatedto/";

    private final ChromeDriver driver;

    @PostConstruct
    void postConstruct() {
        scrape("fortress");
    }

    public void scrape(final String value) {
        driver.get(URL + value);
        final WebElement words = driver.findElementByClassName("words"); //div of "words" elements
        final List<WebElement> wordList = words.findElements(By.tagName("a"));
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        wordList.forEach(word -> System.out.println(word.getText()));
        driver.quit();
    }

}
