package com.guidespace;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


/**
 * Created by Vitali on 11/2/2016.
 */

public class UITests {

    @Test
    public void login(){
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("http://localhost:8080/");
    }

}
