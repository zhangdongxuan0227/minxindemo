package com.minxin.zonghe;


import com.minxin.Cache.Cache;
import com.minxin.util.LogUtil;
import com.minxin.util.WebElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Created by Administrator on 2017/3/21.
 */
public class FenDan {

    Login login = new Login();
    private static WebDriver driver;
    WebElementUtil we = new WebElementUtil();
    PageAction pa = new PageAction();
    LogUtil log = new LogUtil(FenDan.class);

    @Parameters({"url","zfendan", "zpassword"})
    @Test
    public void fendan(String url,String zfendan, String zpassword)throws Exception{

        // 强退用户
        pa.quit(url, zfendan);
        login.loginToAdmin(url, zfendan, zpassword, 0);
        driver = Login.getDriver();

        Thread.sleep(2000);
        //打开信贷客户管理
        driver.findElement(By.xpath("//a[contains(text(),'信贷业务分单')]")).click();

        driver.switchTo().frame("cont_right_show");

        driver.findElement(By.id("q_name")).sendKeys(Cache.name);

        Actions action = new Actions(driver);
        action.sendKeys(Keys.ENTER);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[contains(text(),'分单')]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath(".//*[@id='updForm']/div/dl[3]/dd/span/span/span")).click();

        if(url.contains("10.10.23.122")){
            // 122 环境分配给赵丽娟
            driver.findElement(By.xpath("//*[@class='combobox-item' and contains(text(),'张丽娟0464')]")).click();
        }else{
            // 54 环境分配给赵龙
            driver.findElement(By.xpath("//*[@class='combobox-item' and contains(text(),'赵龙1718')]")).click();
        }

        driver.findElement(By.id("allot")).click();
        if(we.waitToThread(driver, By.xpath("//div[@class ='messageDiv']"), 2000)){
            System.out.printf("分单" + driver.findElement(By.xpath("//div[@class ='messageDiv']")).getText() + "\n");
            String message = driver.findElement(By.xpath("//div[@class ='messageDiv']")).getText();
            Assert.assertEquals("分配成功！", message.trim());
        }

        driver.quit();
        log.info("分单完成");
    }
}
