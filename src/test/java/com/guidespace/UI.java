package com.guidespace;

import com.guidespace.service.UserService;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by Vitali on 11/2/2016.
 */
@Transactional
public class UI {

    private static WebDriver webDriver;
    private static long randNum;

    @BeforeClass
    public static void setUp(){
//        System.setProperty("webdriver.chrome.driver", "/src/test/java/com/guidespace/chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", "./src/test/java/com/guidespace/chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.get("http://localhost:8080/");
        randNum = Math.round(Math.random()*10000);
    }

    @Test
    public void aregistrationTest() throws InterruptedException {
        webDriver.findElement(By.id("loginNavbar")).click();
        synchronized (webDriver) {
            webDriver.wait(800);
            webDriver.findElement(By.id("signuptab")).click();
            webDriver.wait(800);
            webDriver.findElement(By.id("emailRegister")).sendKeys("test"+randNum+"@gmail.com");
            webDriver.findElement(By.id("usernameRegister")).sendKeys("test"+randNum);
            webDriver.findElement(By.id("nameRegister")).sendKeys("Test");
            webDriver.findElement(By.id("surnameRegister")).sendKeys("Test");
            webDriver.findElement(By.id("dateOfBirthRegister")).sendKeys(""+12122015);
            webDriver.findElement(By.id("certMarkLoc")).sendKeys("Test");
            webDriver.findElement(By.id("certExpirationDateRegister")).sendKeys(""+12122014);
            webDriver.findElement(By.id("certWorkLangRegister")).sendKeys("estonian");
            webDriver.findElement(By.id("passwordRegister")).sendKeys("Test"+randNum+"123");
            webDriver.findElement(By.id("passwordRegister2")).sendKeys("Test"+randNum+"123");
            webDriver.findElement(By.id("confirmsignup")).click();
            webDriver.wait(600);
            if (!webDriver.findElement(By.id("logoutNavbar")).getText().equalsIgnoreCase("log out")) { //ei olnud redirecti
                webDriver.findElement(By.id("signintab")).click();
                webDriver.wait(200);
                webDriver.findElement(By.id("username")).sendKeys("test" + randNum);
                webDriver.findElement(By.id("password")).sendKeys("Test" + randNum + "123");
                webDriver.findElement(By.id("signinButton")).click();
            }
            webDriver.wait(1800);
            webDriver.get("http://localhost:8080/giveAdmin");
            webDriver.wait(800);
            webDriver.get("http://localhost:8080");
            webDriver.wait(800);
            System.out.println(randNum);
            Assert.assertTrue(webDriver.findElement(By.id("logoutNavbar")).getText() ,webDriver.findElement(By.id("logoutNavbar")).getText().equalsIgnoreCase("log out"));
            webDriver.findElement(By.id("logoutNavbar")).click();
        }
    }

    @Test
    public void bloginTest() throws InterruptedException {
        synchronized (webDriver) {
            webDriver.wait(800);
            if(webDriver.findElement(By.id("logoutNavbar")).getText().equalsIgnoreCase("log out")) {
                webDriver.findElement(By.id("logoutNavbar")).click();
                webDriver.wait(800);
            }
            webDriver.findElement(By.id("loginNavbar")).click();
            webDriver.wait(800);
            webDriver.findElement(By.id("username")).sendKeys("test"+randNum);
            webDriver.findElement(By.id("password")).sendKeys("Test"+randNum+"123");
            webDriver.findElement(By.id("signinButton")).click();
            webDriver.wait(800);
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
            webDriver.wait(2500);
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
