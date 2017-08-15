package com.minxin.fangdai;


import com.minxin.Cache.Cache;
import com.minxin.util.WebElementUtil;
import com.minxin.zonghe.Login;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

/**
 * Created by Administrator on 2017/7/20.
 * 信审终审
 */
public class XinShenZhongShen {

    private WebDriver driver;
    WebElementUtil we = new WebElementUtil();
    Login login = new Login();

    @Test
    public void xinShenZhongShen(String url, String fzhongshenname, String diyapassword) throws Exception{
        login.login(url, fzhongshenname, diyapassword, 0);
        driver = Login.getDriver();
        if(we.waitToThread(driver, By.xpath("//a[contains(text(),'权证人资料审核')]"),1000 )){
            driver.findElement( By.xpath("//a[contains(text(),'权证人资料审核')]")).click();
        }

        driver.switchTo().frame("cont_right_show");
        driver.findElement(By.id("q_realName")).sendKeys(Cache.name);
        driver.findElement(By.id("searchButton")).click();
        // 点击审核按钮
        driver.findElement(By.xpath("//a[contains(text(),'审核')]")).click();

        driver.findElement(By.xpath("//input[@type='button' and @value='提交']")).click();
        driver.findElement(By.xpath("//button[contains(text(), '确定')]")).click();
        driver.findElement(By.xpath("//button[contains(text(), '确定')]")).click();
        Thread.sleep(3000);
        driver.quit();
        System.out.printf("信审终审完成！");



    }
}
