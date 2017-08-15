package com.minxin.zonghe;


import com.minxin.Cache.Cache;
import com.minxin.util.WebElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Created by Administrator on 2017/3/22.
 */
public class ChuShen {


    private WebDriver driver;
    WebElementUtil we = new WebElementUtil();
    Login login = new Login();
    PageAction pa = new PageAction();

    @Parameters({"url","zchushen", "zpassword"})
    @Test(description = "初审")
    public void chushen(String url, String zchushen, String zpassword) throws Exception{

        // 强退用户
        pa.quit(url, zchushen);
        login.loginToAdmin(url, zchushen, zpassword, 4);
        driver = Login.getDriver();
        Thread.sleep(2000);
        //打开审核管理
        driver.findElement(By.xpath("//a[contains(text(),'审核管理')]")).click();

        driver.switchTo().frame("cont_right_show");
        driver.switchTo().frame("searchAppIf1");

//        WebDriverWait wait = new WebDriverWait(driver, 20);
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("q_cusName1")));

        if( we.waitToThread(driver, By.id("q_cusName1"), 10000)){
            driver.findElement(By.id("q_cusName1")).sendKeys(Cache.name);
        }

        driver.findElement(By.id("searchButton1")).click();

        if(we.waitToThread(driver, By.xpath("//a[contains(text(),'办理')][2]"), 3000)){
            //点击办理
            driver.findElement(By.xpath("//a[contains(text(),'办理')][2]")).click();
        }

        Thread.sleep(2000);
        // 切换窗口
        we.switchToWindow(driver);
        // 审批结论
        we.setSelectIndex(driver.findElement(By.id("approveResult")), 1);
        driver.findElement(By.id("approveAmount")).sendKeys(Cache.amount);
        driver.findElement(By.id("verifyIncome")).sendKeys("600000");
        // 是否自动提交
        driver.findElement(By.xpath(".//*[@id='autoSubmitTr']/td[2]/input[2]")).click();
        System.out.printf("loanType" + Cache.loanType);

        //授信金额
        if(Cache.loanType.equals("60") || Cache.loanType.equals("75") || Cache.loanType.equals("76")||Cache.loanType.equals("77")||Cache.loanType.equals("78")){
            // 授信额度
            //driver.findElement(By.id("creditAmount")).sendKeys("10000");
            driver.findElement(By.xpath(".//*[@id='autoSubmitTr']/td[6]/input")).sendKeys(Cache.amount);
        }
        // 选择信审主管
        we.setSelectIndex(driver.findElement(By.id("approveRoleType")), 1);
        driver.findElement(By.xpath(".//*[@id='approveRoleTypeDiv']/dl/dd/span/span/span")).click();
        driver.findElement(By.xpath("//div[contains(text(),'马美静')]")).click();

        driver.findElement(By.id("approveSubId2")).click();

        we.clickAlertSure1(driver, true);
        Thread.sleep(3000);

        driver.quit();


    }
}
