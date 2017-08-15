package com.minxin.fangdai;

import com.minxin.util.WebElementUtil;
import com.minxin.zonghe.Login;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by Administrator on 2017/7/26.
 */
public class PageAction {

    static WebDriver driver;
    Login login = new Login();
    WebElementUtil we = new WebElementUtil();

    public void addContacts(WebDriver driver,String name,String type) throws InterruptedException {

        WebElement contactName = driver.findElement(By.id("contactName"));
        contactName.sendKeys(name);

        WebElement mobilePhone = driver.findElement(By.id("mobilePhone"));
        mobilePhone.sendKeys("18311111111");

        WebElement relationCd = driver.findElement(By.id("relationCd"));
        we.setSelectContent(relationCd, "1");

        // 类型
        WebElement contactTypeCd = driver.findElement(By.id("contactTypeCd"));
        we.setSelectContent(contactTypeCd, type); // 家庭联系人  （3 工作联系人 1 紧急联系人 2 家庭联系人）

        Thread.sleep(2000);
        WebElement savButton = driver.findElement(By.xpath("//div[@class ='list_cont']/dl/dd/input[@class='btn'][1]"));
        we.highLightElement(driver, savButton);
        savButton.click();
        we.waitImplicitly(driver, 3000);
        Thread.sleep(2000);
        WebElement message = driver.findElement(By.xpath("//div[@class='messageDiv']/img"));
        System.out.printf("联系人提示信息" + message.getText());


    }
}
