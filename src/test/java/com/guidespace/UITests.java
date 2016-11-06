package com.guidespace;

import com.guidespace.domain.Person;
import com.guidespace.repository.UserRepository;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Console;


/**
 * Created by Vitali on 11/2/2016.
 */
@Transactional
public class UITests {

    @Autowired
    private static UserRepository personRepository;
    private static WebDriver webDriver;
    private static long randNum;

//    private static Person person;
//    private static final String username = "VasjaPupkin";
//    private static final String email = "vasjapupkin@gmail.com";
//    private static final String hash = "NKUxcRLwTiUlxfR4Z4EXrWGXVkU/o9bJMT6sma2J6SmoOjV/QhjXn3PP/jqE4XV1qvqqifN3lbqVHEL9wjo5ZA==";
//    private static final String salt = "+U43D57Z4gPq4rcHduvhWGrwzR4UngHQpQJcCs02XZo=";

    @BeforeClass
    public static void setUp(){
        System.setProperty("webdriver.chrome.driver", ".\\src\\test\\java\\com\\guidespace\\chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.get("http://localhost:8080/");
        randNum = Math.round(Math.random()*10000);
//        person = new Person(username, hash, salt, email);
//        person.setUser_role_id(2);
//        personRepository.save(person);
    }

    @Test
    public void aregistrationTest() throws InterruptedException {
        webDriver.findElement(By.id("loginNavbar")).click();
        synchronized (webDriver) {
            webDriver.wait(1600);
            webDriver.findElement(By.id("signuptab")).click();
            webDriver.wait(1600);
            webDriver.findElement(By.id("emailRegister")).sendKeys("test"+randNum+"@gmail.com");
            webDriver.findElement(By.id("usernameRegister")).sendKeys("test"+randNum);
            webDriver.findElement(By.id("passwordRegister")).sendKeys("Test"+randNum+"123");
            webDriver.findElement(By.id("passwordRegister2")).sendKeys("Test"+randNum+"123");
            webDriver.findElement(By.id("confirmsignup")).click();
            webDriver.wait(1800);
            webDriver.get("http://localhost:8080/giveAdmin");
            webDriver.wait(2000);
            webDriver.get("http://localhost:8080");
            Assert.assertTrue(webDriver.findElement(By.id("logoutNavbar")).getText() ,webDriver.findElement(By.id("logoutNavbar")).getText().equalsIgnoreCase("log out"));
            webDriver.findElement(By.id("logoutNavbar")).click();
        }
    }

    @Test
    public void bloginTest() throws InterruptedException {
        synchronized (webDriver) {
            if(webDriver.findElement(By.id("logoutNavbar")).getText().equalsIgnoreCase("log out")) {
                webDriver.findElement(By.id("logoutNavbar")).click();
                webDriver.wait(1600);
            }
            webDriver.findElement(By.id("loginNavbar")).click();
            webDriver.wait(1600);
            webDriver.findElement(By.id("username")).sendKeys("test"+randNum);
            webDriver.findElement(By.id("password")).sendKeys("Test"+randNum+"123");
            webDriver.findElement(By.id("signinButton")).click();
            webDriver.wait(1500);
        }
        Assert.assertTrue(webDriver.findElement(By.id("logoutNavbar")).getText() ,webDriver.findElement(By.id("logoutNavbar")).getText().equalsIgnoreCase("log out"));
    }

    @Test
    public void сQuestionAddingTest() throws InterruptedException {
        synchronized (webDriver) {
            if(!webDriver.findElement(By.id("logoutNavbar")).getText().equalsIgnoreCase("log out")) {
                bloginTest();
            }
            webDriver.wait(1500);
            if("Add Question".toLowerCase().equalsIgnoreCase(webDriver.findElement(By.id("questionNavbar")).getText())) {
                webDriver.findElement(By.id("questionNavbar")).click();
                webDriver.wait(2000);
            }
            webDriver.findElement(By.id("question")).sendKeys("Mis on Eesti pindala?");
            webDriver.findElement(By.id("answer1")).sendKeys("45339km2");
            webDriver.findElement(By.id("atf1")).click();
            webDriver.findElement(By.id("answer2")).sendKeys("45532km2");
            webDriver.findElement(By.id("answer3")).sendKeys("45533km2");
            webDriver.findElement(By.id("answer4")).sendKeys("45534km2");
            webDriver.findElement(By.id("addQuestion")).click();
            webDriver.wait(1500);
        }
        Assert.assertTrue(webDriver.findElement(By.id("outputDiv")).getText() ,webDriver.findElement(By.id("outputDiv")).getText().toLowerCase().contains("New question was added".toLowerCase()));
    }


    @AfterClass
    public static void cleanUp(){
        if (webDriver != null) {
            webDriver.close();
            webDriver.quit();
        }
    }
}
