package com.minxin.fangdai;


import com.minxin.Cache.Cache;
import com.minxin.util.WebElementUtil;
import com.minxin.zonghe.Login;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

/**
 * Created by Administrator on 2017/3/3.
 */
public class SureShen {

    private static WebDriver driver;
    WebElementUtil we = new WebElementUtil();
    Login login = new Login();

    @Test
    public void sureShen(String url, String qjingli01name, String diyapassword) throws Exception{

        login.login(url, qjingli01name,diyapassword, 0);
        driver = Login.getDriver();

        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[contains(text(),'房贷业务管理')]")).click();

        // 查询出对应的修改记录
        driver.switchTo().frame("cont_right_show");
        driver.findElement(By.id("q_realName")).sendKeys(Cache.name);

        driver.findElement(By.id("searchButton")).click();

        Thread.sleep(3000);
        // 点击确认按钮
        driver.findElement(By.id("link_21278_1")).click();
        driver.switchTo().defaultContent();

        driver.switchTo().frame("cont_right_show");
        //同意按钮
        Thread.sleep(2000);
        driver.findElement(By.id("saveButton")).click();

        we.clickAlertSure1(driver, true);

        if(we.waitToThread(driver, By.xpath("//div[@class='messageDiv']/img"), 3000)){

            String message = driver.findElement(By.xpath("//div[@class='messageDiv']")).getText();

            System.out.printf("房贷确认结果为：" + message);
        }

        driver.quit();

    }



}
