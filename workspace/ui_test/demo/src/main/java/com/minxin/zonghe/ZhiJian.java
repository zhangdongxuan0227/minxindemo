package com.minxin.zonghe;


import com.minxin.Cache.Cache;
import com.minxin.util.WebElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Created by Administrator on 2017/3/21.
 */
public class ZhiJian {

    private WebDriver driver;
    WebElementUtil we = new WebElementUtil();
    Login login = new Login();
    PageAction pa = new PageAction();

    @Parameters({"url","zname", "zpassword"})
    @Test
    public void zhijian(String url, String zname, String zpassword) throws Exception{

        // 强退用户
        pa.quit(url, zname);
        login.loginToAdmin(url, zname, zpassword, 0);
        driver = Login.getDriver();

        if(we.waitToThread(driver, By.xpath("//a[contains(text(),'信贷质检管理')]"), 2000)){
            //打开信贷客户管理
            driver.findElement(By.xpath("//a[contains(text(),'信贷质检管理')]")).click();
        }

        driver.switchTo().frame("cont_right_show");


        driver.findElement(By.id("q_name")).sendKeys(Cache.name);

        Thread.sleep(2000);
        WebElement e = driver.findElement(By.xpath("//*[@class='btn'  and @type='submit'] "));

        we.highLightElement(driver, e);
        e.click();
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ENTER);

        if(we.waitToThread(driver, By.xpath("//*[@class='tdbg tdbg_odd']/td[11]/a[1]"), 1000)){
            driver.findElement(By.xpath("//*[@class='tdbg tdbg_odd']/td[11]/a[1]")).click();

        }

        Thread.sleep(2000);

        // 审核结果
        we.setSelectIndex(driver.findElement(By.id("applicationStatus")), 1);
        driver.findElement(By.id("qualityResult")).sendKeys("备注");

        driver.findElement(By.xpath("//input[@class='btn' and @value='提交']")).click();
//
//        if(we.waitToThread(driver, By.xpath("//div[@class ='messageDiv']"), 2000)){
//            System.out.printf("质检结果为：" + driver.findElement(By.xpath("//div[@class ='messageDiv']")).getText() + "\n");
//            String message =  driver.findElement(By.xpath("//div[@class ='messageDiv']")).getText();
//            Assert.assertEquals("质检完成，请到业务管理--->信贷业务管理查看(同盾和算话接口异常)", message.trim());
//        }
        Thread.sleep(3000);

        driver.quit();

    }
}
