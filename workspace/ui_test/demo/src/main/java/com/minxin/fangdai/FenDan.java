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
public class FenDan {

    private WebDriver driver;
     WebElementUtil we = new WebElementUtil();
    Login login = new Login();

    // 房贷分控分单
    @Test
    public void fenDan(String url, String fendanname, String diyapassword) throws Exception{

        login.login(url, fendanname, diyapassword, 1);
        driver = Login.getDriver();


        WebElement waitPart = driver.findElement(By.xpath("//div[@class='index_cont_left']/div[1]/ul/li/a"));
        waitPart.click();

        driver.switchTo().frame("cont_right_show");


        WebElement fangdai = driver.findElement(By.id("li3"));
        fangdai.click();

        driver.switchTo().frame("assignFrame");

        WebElement q_cusName1 = driver.findElement(By.id("q_cusName1"));
        q_cusName1.sendKeys(Cache.name);


        WebElement searchButton1 = driver.findElement(By.id("searchButton1"));
        we.highLightElement(driver, searchButton1);
        searchButton1.click();
        Thread.sleep(2000);

        // 点击分配
        WebElement fenpeiButton = driver.findElement(By.id("link_21336_"));
        fenpeiButton.click();

        WebElement combo = driver.findElement(By.xpath(".//*[@id='updForm']/div/dl/dd/span/span/span"));
        combo.click();

        // WebElement content = driver.findElement(By.xpath("//div[@class ='panel']/div/div[2]"));
        WebElement content = driver.findElement(By.xpath("//div[@class ='panel']/div/div"));
        content.click();
        Thread.sleep(1000);

        WebElement okButton = driver.findElement(By.xpath(".//*[@id='updForm']/div/div/dl/dd/input[1]"));
        okButton.click();
        Thread.sleep(2000);

        driver.switchTo().defaultContent();
        driver.switchTo().defaultContent();
        driver.quit();
    }
}
