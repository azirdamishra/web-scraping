package com.example.webscraper.configuration;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class SeleniumConfiguration {

    @PostConstruct
    void postConstruct(){
        System.setProperty("webdriver.chrome.driver", "/Users/adrizamishra/Downloads/chromedriver");
    }

    @Bean
    public ChromeDriver driver(){
        final ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        //chromeOptions.addArguments("--window-size=1400,600");
        chromeOptions.addArguments("start-maximized");
        return new ChromeDriver(chromeOptions);
    }
    //will be handled for us by spring

}
