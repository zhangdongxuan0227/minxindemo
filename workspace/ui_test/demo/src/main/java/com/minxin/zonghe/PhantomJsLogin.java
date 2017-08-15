package com.minxin.zonghe;

import com.minxin.util.DriverToBrowser;
import com.minxin.util.LogUtil;
import com.minxin.util.WebElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by Administrator on 2017/7/26.
 */
public class PhantomJsLogin {

    private static WebDriver driver;
    WebElementUtil we = new WebElementUtil();
    LogUtil log = new LogUtil(Login.class);
    Login login = new Login();

    // 金盾系统登录
    public void login(String url, String name, String password, int key){


        DriverToBrowser driverToBrowser = new DriverToBrowser();
        driver = driverToBrowser.login(url, 2);
        driver.findElement(By.id("userName")).sendKeys(name);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.name("login")).click();
        we.waitImplicitly(driver, 4000);
    }

    // 综合业务系统登录
    public void loginToAdmin(String url, String name, String password, int key) throws Exception{

        DriverToBrowser driverToBrowser = new DriverToBrowser();
        driver =  driverToBrowser.login(url, 2);
        driver.findElement(By.id("userName")).sendKeys(name);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("code")).sendKeys("X8S9");
        WebElement loginButton = driver.findElement(By.name("login"));
        loginButton.click();
        log.info("登陆成功");
        we.waitImplicitly(driver, 4000);
        // 54环境 呼叫坐席
        if(url.contains("http://10.10.23.54") && name.contains("luotingting")){
            we.clickAlertSure1(driver, true);
            driver.findElement(By.id("hidden")).click();
            driver.switchTo().frame("content");

        }

        login.toSubsystem(key);
    }
}
