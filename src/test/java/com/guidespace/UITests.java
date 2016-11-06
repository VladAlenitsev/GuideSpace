package com.guidespace;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.transaction.annotation.Transactional;

import java.io.Console;


/**
 * Created by Vitali on 11/2/2016.
 */
@Transactional
public class UITests {

    private static WebDriver webDriver;
    private static long randNum;

    @BeforeClass
    public static void setUp(){
        System.setProperty("webdriver.chrome.driver", ".\\src\\test\\java\\com\\guidespace\\chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.get("http://localhost:8080/");
        randNum = Math.round(Math.random()*10000);
    }

    @Test
    public void aregistrationTest() throws InterruptedException {
        webDriver.findElement(By.id("loginNavbar")).click();
        synchronized (webDriver) {
            webDriver.wait(500);
            webDriver.findElement(By.id("signuptab")).click();
            webDriver.wait(500);
            webDriver.findElement(By.id("emailRegister")).sendKeys("test"+randNum+"@gmail.com");
            webDriver.findElement(By.id("usernameRegister")).sendKeys("test"+randNum);
            webDriver.findElement(By.id("passwordRegister")).sendKeys("Test"+randNum+"123");
            webDriver.findElement(By.id("passwordRegister2")).sendKeys("Test"+randNum+"123");
            webDriver.findElement(By.id("confirmsignup")).click();
            webDriver.wait(500);
        }
        Assert.assertTrue(webDriver.findElement(By.id("logoutNavbar")).getText() ,webDriver.findElement(By.id("logoutNavbar")).getText().equalsIgnoreCase("log out"));
    }

    @Test
    public void bloginTest() throws InterruptedException {
        synchronized (webDriver) {
            if(webDriver.findElement(By.id("logoutNavbar")).getText().equalsIgnoreCase("log out")) {
                webDriver.findElement(By.id("logoutNavbar")).click();
                webDriver.wait(500);
            }
            webDriver.findElement(By.id("loginNavbar")).click();
            webDriver.wait(500);
            webDriver.findElement(By.id("username")).sendKeys("test"+randNum);
            webDriver.findElement(By.id("password")).sendKeys("Test"+randNum+"123");
            webDriver.findElement(By.id("signinButton")).click();
            webDriver.wait(500);
        }
        Assert.assertTrue(webDriver.findElement(By.id("logoutNavbar")).getText() ,webDriver.findElement(By.id("logoutNavbar")).getText().equalsIgnoreCase("log out"));
    }

    @AfterClass
    public static void cleanUp(){
        if (webDriver != null) {
            webDriver.close();
            webDriver.quit();
        }
    }
}
