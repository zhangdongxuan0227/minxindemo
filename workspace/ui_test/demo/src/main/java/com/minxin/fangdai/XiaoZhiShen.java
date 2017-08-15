package com.minxin.fangdai;


import com.minxin.Cache.Cache;
import com.minxin.util.WebElementUtil;
import com.minxin.zonghe.Login;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

/**
 * Created by Administrator on 2017/7/20.
 */
public class XiaoZhiShen {

    Login login = new Login();
    private static WebDriver driver;
    WebElementUtil we = new WebElementUtil();

    @Test(description = "销支审核")
    public void xiaoZhiShen(String url, String xiaozhi, String diyapassword) throws Exception{
        login.login(url, xiaozhi, diyapassword, 0);
        driver = Login.getDriver();

        if(we.waitToThread(driver, By.xpath("//a[contains(text(),'销支待审核')]"), 3000 )){
            driver.findElement(By.xpath("//a[contains(text(),'销支待审核')]")).click();
        }


        driver.switchTo().frame("cont_right_show");

        driver.findElement(By.id("q_realName")).sendKeys(Cache.name);
        driver.findElement(By.id("searchButton")).click();
        if(we.waitToThread(driver, By.xpath("//a[contains(text(), '审核')]"), 2000)){
            driver.findElement(By.xpath("//a[contains(text(), '审核')]")).click();
        }
        driver.findElement(By.xpath(".//*[@id='btnBackDiv']/dl/dd/input[1]")).click();
//        driver.findElement(By.xpath("//input[@type='button' and @value='审核通过']")).click();
        Thread.sleep(3000);
        driver.switchTo().defaultContent();
        driver.switchTo().frame("cont_right_show");

        driver.findElement(By.xpath("//button[contains(text(), '确定')]")).click();
        driver.findElement(By.xpath("//button[contains(text(), '确定')]")).click();

        Thread.sleep(3000);

        driver.quit();

    }
}
