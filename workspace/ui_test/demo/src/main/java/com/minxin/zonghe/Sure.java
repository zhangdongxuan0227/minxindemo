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
public class Sure {

    private WebDriver driver;
    WebElementUtil we = new WebElementUtil();
    Login login = new Login();
    PageAction pa = new PageAction();

    @Parameters({"url","zxinshen", "zRobin"})
    @Test(description = "确认借款")
    public void sure(String url, String zxinshen, String zRobin) throws Exception{

        // 强退用户dkjf
        pa.quit(url, zxinshen);
        login.loginToAdmin(url, zxinshen, zRobin, 0);
        driver = Login.getDriver();
        Thread.sleep(2000);
        //打开审核管理
        driver.findElement(By.xpath("//a[contains(text(),'信贷业务管理')]")).click();
        Thread.sleep(3000);
        driver.switchTo().frame("cont_right_show");
        driver.findElement(By.id("q_name")).sendKeys(Cache.name);

        driver.findElement(By.xpath("//*[@class='btn'  and @type='button' and @value='查询']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[contains(text(),'确认')]")).click();

        driver.switchTo().defaultContent();
        driver.switchTo().frame("cont_right_show");
        driver.findElement(By.id("saveButton")).click();
        we.clickAlertSure1(driver, true);
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.ENTER);

        if(we.waitToThread(driver, By.xpath("//div[@class ='messageDiv']"), 2000)){
            System.out.printf("确认借款结果为：：" + driver.findElement(By.xpath("//div[@class ='messageDiv']")).getText() + "\n");
            String message =  driver.findElement(By.xpath("//div[@class ='messageDiv']")).getText();
            Assert.assertEquals("审核信息确认成功", message.trim());
        }
        driver.quit();

    }


}
