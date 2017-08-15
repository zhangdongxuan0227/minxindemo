package com.minxin.fangdai;


import com.minxin.Cache.Cache;
import com.minxin.util.WebElementUtil;
import com.minxin.zonghe.Login;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

/**
 * Created by Administrator on 2017/1/17.
 */
public class ChuShen {


    // 客户名称
    public static String name = Cache.name;

    static WebDriver driver;
    Login login = new Login();
    WebElementUtil we = new WebElementUtil();

    /**
     * 房贷初审
     * @throws InterruptedException
     */
    @Test
    public void chuShen(String url, String fchushenname, String diyapassword) throws Exception {

        login.login(url, fchushenname, diyapassword, 1);
        driver = Login.getDriver();

        // 点击待审核任务
        WebElement waitCheck = driver.findElement(By.xpath(".//*[@id='wrap']/div[2]/div/div[1]/div[1]/ul/li[1]/a"));
        waitCheck.click();

        driver.switchTo().frame("cont_right_show");
        WebElement q_cusName1 = driver.findElement(By.id("q_cusName1"));
        q_cusName1.sendKeys(Cache.name);

        WebElement searchButton1 = driver.findElement(By.id("searchButton1"));
        searchButton1.click();

        Thread.sleep(2000);
        WebElement checkButton = driver.findElement(By.id("link_21221_1"));
        checkButton.click();
        driver.switchTo().defaultContent();

        driver.switchTo().frame("cont_right_show");
        WebElement commitForm = driver.findElement(By.id("commitForm"));
        we.highLightElement(driver, commitForm);
        Thread.sleep(2000);
        commitForm.click();
        driver.switchTo().defaultContent();

        Thread.sleep(2000);
        driver.switchTo().frame("cont_right_show");
        driver.switchTo().frame("xubox_iframe1");

        if(we.waitToThread(driver,By.id("approveResult"),3000)){
            //决策
            WebElement approveResult = driver.findElement(By.id("approveResult"));
            we.setSelectContent(approveResult, "1");
        }



        // 抵押次数
        we.setSelectContent(driver.findElement(By.id("mortgage")), "50");


        // 评估金额
        Thread.sleep(2000);
        WebElement assessAmount = driver.findElement(By.id("assessAmount"));
        assessAmount.sendKeys("500000");

        // 房产评估描述
        WebElement houseDetails = driver.findElement(By.id("houseDetails"));
        houseDetails.sendKeys("房产评估描述");

        // 决策描述
        WebElement replenishExplain = driver.findElement(By.id("replenishExplain"));
        replenishExplain.sendKeys("决策描述");

        Thread.sleep(1000);
        WebElement btn = driver.findElement(By.className("btn"));
        btn.click();

        driver.switchTo().defaultContent();
        driver.switchTo().defaultContent();
        Thread.sleep(1000);
        driver.quit();



    }

}
