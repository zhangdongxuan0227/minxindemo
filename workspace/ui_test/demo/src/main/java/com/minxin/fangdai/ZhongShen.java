package com.minxin.fangdai;


import com.minxin.Cache.Cache;
import com.minxin.util.WebElementUtil;
import com.minxin.zonghe.Login;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

/**
 * Created by Administrator on 2017/3/3.
 */
public class ZhongShen {

    private static WebDriver driver;
    WebElementUtil we = new WebElementUtil();
    Login login = new Login();

    @Test
    public void zhongshen(String url, String fzhongshenname, String diyapassword) throws Exception{

        login.login(url, fzhongshenname, diyapassword, 1);
        driver = Login.getDriver();

        Thread.sleep(1000);
        driver.findElement(By.xpath(".//*[@id='wrap']/div[2]/div/div[1]/div[1]/ul/li[1]/a")).click();

        driver.switchTo().frame("cont_right_show");
        driver.findElement(By.id("q_cusName1")).sendKeys(Cache.name);
        driver.findElement(By.id("searchButton1")).click();

        Thread.sleep(2000);
        driver.findElement(By.id("link_21221_1")).click();
        driver.switchTo().defaultContent();

        driver.switchTo().frame("cont_right_show");
        WebElement commitForm = driver.findElement(By.id("commitForm"));
        commitForm.click();
        driver.switchTo().defaultContent();
        Thread.sleep(2000);
        driver.switchTo().frame("cont_right_show");
        driver.switchTo().frame("xubox_iframe1");

        WebElement approveResult = driver.findElement(By.id("approveResult"));
        we.setSelectContent(approveResult, "1");

        driver.findElement(By.id("memo")).sendKeys("决策描述");
        driver.findElement(By.id("outOptionMemo")).sendKeys("外部意见描述");
        driver.findElement(By.id("baoCun")).click();

        driver.switchTo().defaultContent();
        driver.switchTo().defaultContent();
        driver.switchTo().frame("cont_right_show");
        if(we.waitToThread(driver, By.xpath("//div[@class='messageDiv']/img"), 3000)){

            String message = driver.findElement(By.xpath("//div[@class='messageDiv']")).getText();

            System.out.printf("房贷终审结果为：" + message);
        }
        driver.quit();
    }

}
