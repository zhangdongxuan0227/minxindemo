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
public class LoanQualityChecking {

    private WebDriver driver;
    WebElementUtil we = new WebElementUtil();
    Login login = new Login();

    @Test
    public void loanQualityChecking(String url, String kefuname, String diyapassword) throws Exception{

        login.login(url, kefuname, diyapassword, 0);
        driver = Login.getDriver();

        // 点击质检
        if(we.waitToThread(driver, By.xpath("//a[contains(text(),'质检')]"),4000)){
            WebElement loanCheck = driver.findElement(By.xpath("//a[contains(text(),'质检')]"));
            System.out.printf("标题为：" + loanCheck.getText());
            loanCheck.click();
        }



        driver.switchTo().frame("cont_right_show");
        WebElement q_name = driver.findElement(By.id("q_real_name"));
        q_name.sendKeys(Cache.name);

        // WebElement searchButton = driver.findElement(By.xpath(".//*[@id='queryMainForm']/dl/dd/input[1]"));
        WebElement searchButton = driver.findElement(By.id("searchButton"));
        searchButton.click();
        Thread.sleep(1000);

        WebElement checkButton = driver.findElement(By.id("link_22052_1"));
        checkButton.click();

        driver.switchTo().defaultContent();
        driver.switchTo().frame("cont_right_show");
        driver.switchTo().frame("addLoanApp1");

        Thread.sleep(1000);
        WebElement qualityResult = driver.findElement(By.id("examine_remark"));
        qualityResult.sendKeys("质检通过");

        WebElement tButton = driver.findElement(By.id("btnSubmitId"));
        Thread.sleep(2000);
        tButton.click();
        Thread.sleep(1000);
        driver.quit();

    }
}
