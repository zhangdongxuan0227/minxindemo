package com.minxin.fangdai;


import com.minxin.Cache.Cache;
import com.minxin.util.WebElementUtil;
import com.minxin.zonghe.Login;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/3/3.
 */
public class UploadInfo {

    private static WebDriver driver;
    WebElementUtil we = new WebElementUtil();
    Login login = new Login();
    int num = 7;

    //上传受理单
    @Test
    public void uploadDanJu(String url, String quanzhengren01, String diyapassword)throws Exception{

        login.login(url, quanzhengren01, diyapassword, 0);
        driver = Login.getDriver();

        Thread.sleep(3000);
        driver.findElement(By.xpath("//a[contains(text(),'权证人资料上传')]")).click();

        driver.switchTo().frame("cont_right_show");
        driver.findElement(By.id("q_realName")).sendKeys(Cache.name);

        driver.findElement(By.id("searchButton")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//a[contains(text(),'上传受理单')]")).click();
        for(int i=0; i < 3; i++){
            if(i < 1){
                this.fujian(driver, 7);
            }else if(i==1){
                this.fujian(driver, 8);
            }else{
                this.fujian(driver, 9);
            }

        }
        driver.findElement(By.xpath("//input[@type='button' and @value='提交审核']")).click();
        driver.findElement(By.xpath("//button[contains(text(), '确定')]")).click();
        driver.quit();
        System.out.printf("上传受理单完成！");
    }

    /**
     * 权证人上传合同
     * quanzhengren01  用户上传影像资料
     */

    @Test
    public void uploadInfo(String url, String quanzhengren01, String diyapassword) throws Exception {

        login.login(url, quanzhengren01,diyapassword, 0);
        driver = Login.getDriver();

        Thread.sleep(3000);
        driver.findElement(By.xpath("//a[contains(text(),'权证人资料上传')]")).click();

        driver.switchTo().frame("cont_right_show");
        driver.findElement(By.id("q_realName")).sendKeys(Cache.name);

        driver.findElement(By.id("searchButton")).click();
        Thread.sleep(3000);

        driver.findElement(By.xpath("//a[contains(text(),'上传合同')]")).click();


        Thread.sleep(3000);

        driver.switchTo().frame("fileIframe0");
        we.fujian(driver, 4, 2, 3);

        driver.switchTo().defaultContent();
        driver.switchTo().defaultContent();

        driver.switchTo().frame("cont_right_show");
        // 权证人
        we.setSelectIndex(driver.findElement(By.id("empId")), 1);

        // 点击提交审核
        driver.findElement(By.xpath("//div[@class='list_cont']/dl/dd/input")).click();

        Thread.sleep(300);
        driver.findElement(By.xpath("//button[contains(text(),'确定')]")).click();
        driver.switchTo().defaultContent();

        driver.quit();
        System.out.printf("权证人上传合同完成！");

    }


    public void fujian(WebDriver driver,int num) throws Exception {

        Screen s = new Screen();
        Pattern up = new Pattern("E:\\jenkins\\workspace\\ui_test\\demo\\data\\"+ num +".png");
        s.find(up).right(35).click();

        // 上传附件操作
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        String path = "E:\\jenkins\\workspace\\ui_test\\demo\\data\\Test.rar";
        Pattern content = new Pattern("E:\\jenkins\\workspace\\ui_test\\demo\\data\\2.png");
        s.type(content, path);
        Pattern open = new Pattern("E:\\jenkins\\workspace\\ui_test\\demo\\data\\3.png");
        if(s.find(open) != null){
            s.click();
        }
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        Thread.sleep(6000);

    }

}
