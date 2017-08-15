package com.minxin.zonghe;


import com.minxin.util.DriverToBrowser;
import com.minxin.util.WebElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Created by Administrator on 2017/3/21.
 */
public class PageAction {

    private WebDriver driver;
    WebElementUtil we = new WebElementUtil();

    @Parameters({"url","zname"})
    @Test(description = "强制用户退出")
    public void quit(String url, String name) throws Exception{

        DriverToBrowser driverToBrowser = new DriverToBrowser();
        driver =  driverToBrowser.login(url,2);

        driver.findElement(By.xpath("//a[contains(text(),'用户被锁时请点击此处')]")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("longValue2")).sendKeys(name);


        we.highLightElement(driver, driver.findElement(By.xpath("//*[@class='btn' and @type='button' and @value='清除']")));
        driver.findElement(By.xpath("//*[@class='btn' and @type='button' and @value='清除' ]")).click();

        Thread.sleep(2000);
        driver.quit();


    }



    // 添加联系人
    public void addContacts(WebDriver driver,String name,String type) throws InterruptedException {

        WebElement contactName = driver.findElement(By.id("contactName"));
        contactName.sendKeys(name);

        WebElement mobilePhone = driver.findElement(By.id("mobilePhone"));
        mobilePhone.sendKeys("18311111111");

        WebElement relationCd = driver.findElement(By.id("relation"));
        we.setSelectContent(relationCd, "1");
        // 工作单位
        driver.findElement(By.id("companyName")).sendKeys("中融民信资本管理有限公司");
        // 职位
        driver.findElement(By.id("position")).sendKeys("职位");
        // 通讯地址
        driver.findElement(By.id("contactAddress")).sendKeys("北京市朝阳区光华路");

        // 类型
        WebElement contactTypeCd = driver.findElement(By.id("contactType"));
        we.setSelectContent(contactTypeCd, type); // 家庭联系人  （3 工作联系人 1 紧急联系人 2 家庭联系人）

        Thread.sleep(2000);
        WebElement savButton = driver.findElement(By.xpath("//div[@class ='list_cont']/dl/dd/input[@class='btn'][1]"));
        we.highLightElement(driver, savButton);
        savButton.click();
        we.waitImplicitly(driver, 3000);


    }

}
