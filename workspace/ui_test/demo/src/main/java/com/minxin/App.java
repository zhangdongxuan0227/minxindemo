package com.minxin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.File;

//import org.openqa.selenium.phantomjs.PhantomJSDriver;

public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }

    public WebDriver driver;

    @Test
    public void openBrowe(){

        File file1 = new File("D:/webdriver/chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", file1.getAbsolutePath());
        driver = new ChromeDriver();
        driver.get("https://www.baidu.com/");
        driver.manage().window().maximize();
    }

    public void mulUser(){}

//    @Test
/*    public void phantomjsLogin(){
        System.setProperty("phantomjs.binary.path", "D:\\Tool\\phantomjs-1.9.7-windows\\phantomjs.exe");
        driver = new PhantomJSDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.baidu.com/");
        System.out.println(driver.getCurrentUrl());
        driver.findElement(By.id("kw")).sendKeys("测试");
        driver.findElement(By.id("su")).click();
        System.out.printf("cjkj");
    }*/

    @AfterMethod
    public void closeBrowser(){
        driver.quit();
    }




}
