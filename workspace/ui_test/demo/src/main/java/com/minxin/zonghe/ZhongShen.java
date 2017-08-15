package com.minxin.zonghe;


import com.minxin.Cache.Cache;
import com.minxin.util.WebElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Created by Administrator on 2017/3/22.
 */
public class ZhongShen {

    private WebDriver driver;
    WebElementUtil we = new WebElementUtil();
    Login login = new Login();
    PageAction pa = new PageAction();

    @Parameters({"url","zzhongshen", "zpassword"})
    @Test(description = "终审操作")
    public void zhongshen(String url, String zzhongshen, String zpassword) throws Exception{

        // 强退用户
        pa.quit(url,zzhongshen);
        login.loginToAdmin(url, zzhongshen, zpassword, 4);
        driver = Login.getDriver();
        Thread.sleep(2000);
        //打开审核管理
        driver.findElement(By.xpath("//a[contains(text(),'审核管理')]")).click();
        driver.switchTo().frame("cont_right_show");
        driver.switchTo().frame("searchAppIf1");

        if(we.waitToThread(driver, By.id("q_cusName1"), 10000)){
            driver.findElement(By.id("q_cusName1")).sendKeys(Cache.name);
        }


        driver.findElement(By.id("searchButton1")).click();

        Thread.sleep(3000);
        //点击办理
        driver.findElement(By.xpath("//a[contains(text(),'办理')][2]")).click();
        Thread.sleep(2000);

        // 切换窗口
        we.switchToWindow(driver);
        Thread.sleep(2000);
        // 点击提交
        driver.findElement(By.id("approveSubId2")).click();
        we.clickAlertSure1(driver, true);
        Thread.sleep(2000);
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.ENTER);
        Thread.sleep(2000);

        driver.quit();

    }
}
