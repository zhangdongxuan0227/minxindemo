package com.minxin.zonghe;


import com.minxin.Cache.Cache;
import com.minxin.util.WebElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Created by Administrator on 2017/3/21.
 */
public class XSFenDan {

    Login login = new Login();
    private static WebDriver driver;
    WebElementUtil we = new WebElementUtil();
    PageAction pa = new PageAction();

    @Parameters({"url","zxinshen", "zRobin"})
    @Test(description = "信审分单")
    public void xinshenfendan(String url, String zxinshen, String zRobin) throws Exception{

        // 强退用户
        pa.quit(url, zxinshen);
        login.loginToAdmin(url, zxinshen, zRobin, 4);
        driver = Login.getDriver();

        Thread.sleep(2000);
        //打开信贷客户管理
        driver.findElement(By.xpath("//a[contains(text(),'分件管理')]")).click();

        driver.switchTo().frame("cont_right_show");
        driver.switchTo().frame("assignNo");

        if(we.waitToThread(driver, By.id("q_cusName1"), 10000)) {
            WebElement nameElement = driver.findElement(By.id("q_cusName1"));
            nameElement.sendKeys(Cache.name);
        }

        driver.findElement(By.id("searchButton1")).click();
        // 点击分配按钮
        Thread.sleep(2000);
        driver.findElement(By.id("link_217_")).click();
        driver.switchTo().defaultContent();
        driver.switchTo().frame("cont_right_show");
        Thread.sleep(2000);

        driver.findElement(By.xpath("//div[@class='list_cont']/dl/dd/span/span/span")).click();
        if(url.contains("10.10.23.54")){   // 54环境
            driver.findElement(By.xpath("//div[contains(text(),'罗婷婷')]")).click();
        }else{  // 122 环境
            driver.findElement(By.xpath("//div[contains(text(),'测试初审')]")).click();
        }

        Thread.sleep(1000);

        driver.findElement(By.xpath("//*[@class='btn' and @type='button' ][1]")).click();

        if(we.waitToThread(driver, By.xpath("//div[@class ='messageDiv']"), 2000)){
            System.out.printf("信审分单结果为：" + driver.findElement(By.xpath("//div[@class ='messageDiv']")).getText() + "\n");
            String message =  driver.findElement(By.xpath("//div[@class ='messageDiv']")).getText();
            Assert.assertEquals("分配成功", message.trim());
        }

        driver.quit();


    }

}
