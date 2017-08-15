package com.minxin.fangdai;


import com.minxin.Cache.Cache;
import com.minxin.util.WebElementUtil;
import com.minxin.zonghe.Login;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

/**
 * Created by Administrator on 2017/3/2.
 */
public class DiaoCha {

    private WebDriver driver;
    WebElementUtil we = new WebElementUtil();
    Login login = new Login();


    @Test
    public void  diaocha(String url, String kefuname, String diyapassword) throws Exception {
        login.login(url,kefuname,diyapassword, 0);
        driver = Login.getDriver();

        // 点击调查
        driver.findElement(By.xpath("//a[contains(text(),'房贷调查管理')]")).click();

        Thread.sleep(1000);
        driver.switchTo().frame("cont_right_show");
        // 查询出修改结果
        WebElement q_realName = driver.findElement(By.id("q_realName"));
        q_realName.sendKeys(Cache.name);
        WebElement searchButton = driver.findElement(By.id("searchButton"));
        searchButton.click();

        Thread.sleep(1000);

        driver.findElement(By.id("link_21547_1")).click();

        Thread.sleep(1000);
        driver.switchTo().frame("addLoanApp1");

        //抵押金额
        WebElement mortgageMoney = driver.findElement(By.id("mortgageMoney"));
        mortgageMoney.sendKeys("600000");

        //是否抵押
        WebElement isMortgage = driver.findElement(By.xpath("//input[@name='isMortgage' and @value='0']"));
        we.selectRadio(isMortgage);

        //有无电梯
        WebElement ishaveLift = driver.findElement(By.xpath("//input[@name='ishaveLift' and @value='1']"));
        we.selectRadio(ishaveLift);

        //有无落户
        WebElement ishaveOld = driver.findElement(By.xpath("//input[@name='ishaveOld' and @value='1']"));
        we.selectRadio(ishaveOld);

        //户型
//        WebElement houseType = driver.findElement(By.id("houseType"));
//        we.setSelectContent(houseType, "2");

        //装修
//        WebElement renovationType = driver.findElement(By.id("renovationType"));
//        we.setSelectContent(renovationType, "2");

//        //小区名称
//        WebElement housingEstate = driver.findElement(By.id("housingEstate"));
//        housingEstate.sendKeys("测试小区");
//
//        //房产现状
//        WebElement housingSituation = driver.findElement(By.name("housingSituation"));
//        we.setSelectContent(housingSituation, "1");

        //地铁路线
        WebElement ishaveSubway = driver.findElement(By.xpath("//input[@name='ishaveSubway' and @value='1']"));
        we.selectRadio(ishaveSubway);

//        //房屋楼层
//        WebElement floors = driver.findElement(By.name("floors"));
//        floors.sendKeys("9");
//
//        //总层数
//        WebElement totalFloors = driver.findElement(By.name("totalFloors"));
//        totalFloors.sendKeys("22");
//
//        //建筑面积
//        WebElement constructionArea = driver.findElement(By.name("constructionArea"));
//        constructionArea.sendKeys("222.22");
//
//        //朝向
//        WebElement orientation = driver.findElement(By.name("orientation"));
//        we.setSelectContent(orientation, "2");

        //采光情况
        WebElement lightWindCondition = driver.findElement(By.name("lightWindCondition"));
        we.setSelectContent(lightWindCondition, "2");

        //噪音影响
        WebElement noise = driver.findElement(By.name("noise"));
        we.setSelectContent(noise, "2");

        //厌恶设施
        WebElement hateFacilities = driver.findElement(By.xpath("//input[@name='hateFacilities' and @value='1']"));
        we.selectRadio(hateFacilities);

        //车位情况
        WebElement stall = driver.findElement(By.name("stall"));
        we.setSelectContent(stall, "1");

        //小区周边
        WebElement cellAround = driver.findElement(By.name("cellAround"));
        we.selectRadio(cellAround);

        //暂存
        WebElement zancun = driver.findElement(By.id("zancun"));
        zancun.click();

        Thread.sleep(2000);
        WebElement message = driver.findElement(By.xpath("//div[@class='messageDiv']"));
        System.out.printf("房贷调查:" + message.getText());

        Thread.sleep(1000);
        driver.switchTo().defaultContent();
        driver.switchTo().frame("cont_right_show");

        //提交按钮
        WebElement btn = driver.findElement(By.xpath(".//div[@class='list_cont']/dl/dd/input[2]"));
        btn.click();

        Thread.sleep(1000);
        we.clickAlertSure1(driver, true);
        driver.quit();


    }
}
