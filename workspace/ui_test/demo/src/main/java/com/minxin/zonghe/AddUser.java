package com.minxin.zonghe;

import com.minxin.Cache.Cache;
import com.minxin.util.ExcelDataUtil;
import com.minxin.util.GetData;
import com.minxin.util.LogUtil;
import com.minxin.util.WebElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.*;

/**
 * Created by Administrator on 2017/7/19.
 */
public class AddUser {

    Login login = new Login();
    private static WebDriver driver;
    WebElementUtil we = new WebElementUtil();
    PageAction pa = new PageAction();
    GetData getData = new GetData();
    LogUtil log = new LogUtil(AddUser.class);



    @Parameters({"url","zname", "zpassword"})
    @Test(description = "新增用户")
    public void addUser(String url,String zname, String zpassword) throws Exception{

        // 强退用户
        pa.quit(url,zname);
        login.loginToAdmin(url,zname, zpassword, 0);
        driver = Login.getDriver();

        //打开信贷客户管理
        if(we.waitToThread(driver, By.xpath("//a[contains(text(),'信贷客户管理')]"), 2000)){
            driver.findElement(By.xpath("//a[contains(text(),'信贷客户管理')]")).click();
        }


        // 点击新增
        driver.switchTo().frame("cont_right_show");
        driver.findElement(By.id("btn222")).click();

        driver.findElement(By.id("name")).sendKeys(Cache.name);
        driver.findElement(By.id("idNumber1")).sendKeys(Cache.shenfenzhen);

        // 证件有效期
        driver.findElement(By.id("checkboxided")).click();
        WebElement idIssuingDate = driver.findElement(By.id("idIssuingDate"));
        idIssuingDate.click();

        WebElement dateIframe = driver.findElement(By.xpath("//body/div[2]/iframe"));
        driver.switchTo().frame(dateIframe);
        WebElement today = driver.findElement(By.id("dpOkInput"));
        today.click();

        driver.switchTo().defaultContent();
        driver.switchTo().frame("cont_right_show");

        // 学历
        we.setSelectIndex(driver.findElement(By.id("eduaction")), 2);
        driver.findElement(By.id("mobilePhone")).sendKeys(Cache.phone);

        we.setSelectIndex(driver.findElement(By.id("maritalStatus")), 2);

        we.setSelectIndex(driver.findElement(By.id("children")), 2);
        // 房产情况
        we.setSelectIndex(driver.findElement(By.id("houseStatus")), 2);
        //同住者
        we.setSelectIndex(driver.findElement(By.id("livingStatus")), 2);
        //信用卡最高额度
        driver.findElement(By.id("cardMaxLimit")).sendKeys("30000");
        // 户籍地址
        driver.findElement(By.id("permanentAddress")).sendKeys("河南省郑州市七一路");
        // 通讯dizhi
        we.setSelectIndex(driver.findElement(By.id("cusCityCode")), 2);
        Thread.sleep(2000);
        we.setSelectIndex(driver.findElement(By.id("noticeAreaCode")), 1);
        driver.findElement(By.id("contactAddress")).sendKeys("通讯地址");

        // 单位名称
        driver.findElement(By.id("companyName")).sendKeys("中融民信资本管理有限公司");

        // 目标用户
        we.setSelectIndex(driver.findElement(By.id("targetCusCode")), 2);
        Thread.sleep(1000);
        // 客户职级
        we.setSelectIndex(driver.findElement(By.id("cusLevelCode")), 2);
        // 单位性质
        we.setSelectIndex(driver.findElement(By.id("companyType")), 2);

        // 工作时长
        driver.findElement(By.id("workAge")).sendKeys("3");
        driver.findElement(By.id("dept")).sendKeys("部门");
        // 担任职务
        driver.findElement(By.id("profession")).sendKeys("担任职务");
        // 年收入
        driver.findElement(By.id("annualSalary")).sendKeys("10");

        //单位电话
        we.setSelectContent(driver.findElement(By.id("OfficePhoneNumber")), "2");
        driver.findElement(By.id("companyTel4")).sendKeys(Cache.phone);

        // 单位地址
        we.setSelectIndex(driver.findElement(By.id("companyCusCityCode")), 2);
        Thread.sleep(1000);
        we.setSelectIndex(driver.findElement(By.id("companyNoticeAreaCode")), 2);
        driver.findElement(By.id("companyAddress")).sendKeys("单位地址");
        // 供链贷
        if(Cache.loanType.equals("60")  || Cache.loanType.equals("75") || Cache.loanType.equals("76")||Cache.loanType.equals("77")||Cache.loanType.equals("78") ){
            driver.findElement(By.id("manageCompanyName")).sendKeys("经营企业名称");
            we.setSelectContent(driver.findElement(By.id("companyNature")), "1");// 国有企业
            // 成立时间
            driver.findElement(By.id("companyStartDate")).click();
            WebElement dateframe = driver.findElement(By.xpath("//body/div[2]/iframe"));
            driver.switchTo().frame(dateframe);
            driver.findElement(By.id("dpOkInput")).click();
            driver.switchTo().defaultContent();
            driver.switchTo().frame("cont_right_show");

            driver.findElement(By.id("businessScope")).sendKeys("经营范围1经营范围2");
            driver.findElement(By.id("companyRegisterAdress")).sendKeys("注册地址");

        }

        // 客户经理
        we.setSelectIndex(driver.findElement(By.name("cusManager")), 1);
        driver.findElement(By.id("teamManagerName")).sendKeys("团队经理");
        // 职称
        driver.findElement(By.name("professionalTitle")).sendKeys("职称");
        // 农业种植类型
        we.setSelectIndex(driver.findElement(By.id("agricultureType")), 3);
        // 土地种植面积
        we.setSelectIndex(driver.findElement(By.id("agricultureArea")), 4);

        // 居住性质
        we.setSelectIndex(driver.findElement(By.id("housingInfoCd")), 3);
        // 当地居住年限
        driver.findElement(By.name("livingYears")).sendKeys("3");

        driver.findElement(By.id("saveButton1")).click();
        if(we.waitToThread(driver, By.xpath("//div[@class ='messageDiv']/img"), 2000)){
            System.out.printf("添加用户" + driver.findElement(By.xpath("//div[@class ='messageDiv']")).getText() +"\n");
        }
        driver.quit();
        log.info("添加用户成功");

    }


    @Parameters({"url","zname", "zpassword"})
    @Test(description = "添加联系人信息")
    public void addLianXiInfo(String url, String zname, String zpassword) throws Exception {

        // 强退用户
        pa.quit(url,zname);

        login.loginToAdmin(url, zname, zpassword, 0);
        driver = Login.getDriver();
        we.waitToThread(driver, By.xpath("//a[contains(text(),'信贷客户管理')]"), 2000);

        //打开信贷客户管理
        driver.findElement(By.xpath("//a[contains(text(),'信贷客户管理')]")).click();

        driver.switchTo().frame("cont_right_show");
        driver.findElement(By.id("q_name")).sendKeys(Cache.name);
        driver.findElement(By.id("search")).click();

        driver.switchTo().defaultContent();
        driver.switchTo().frame("cont_right_show");

        driver.findElement(By.xpath("//a[contains(text(),'联系人信息')]")).click();

        // 添加两个家庭联系人
        for (int i = 0; i < 5; i++) {
            String name1 = "test" + i;
            String type1 = null;
            if (i < 2) {
                type1 = "2";
                System.out.printf("type1 =" + type1 + "\n");

            } else if (i >= 2 && i < 4) {
                type1 = "3";
                System.out.printf("type1 =" + type1 + "\n");
            } else if (i >= 4 && i < 5) {
                type1 = "1";   // 紧急联系人
                System.out.printf("type1 =" + type1 + "\n");
            }

            Thread.sleep(2000);
            pa.addContacts(driver, name1, type1);

        }

        driver.switchTo().defaultContent();
        driver.findElement(By.xpath("//a[contains(text(),'退出登录')]")).click();
        driver.quit();
        log.info("添加联系人成功");

    }

    @Parameters({"url","zname", "zpassword"})
    @Test(description = "借款申请")
    public void addBorrow(String url, String zname, String zpassword) throws Exception{
        System.out.printf("借款申请开始");

        // 强退用户
        pa.quit(url, zname);

        login.loginToAdmin(url,zname, zpassword, 0, 2);
        driver = Login.getDriver();

        Thread.sleep(2000);
        //打开信贷客户管理
        driver.findElement(By.xpath("//a[contains(text(),'信贷客户管理')]")).click();

        driver.switchTo().frame("cont_right_show");
        driver.findElement(By.id("q_name")).sendKeys(Cache.name);
        driver.findElement(By.id("search")).click();

        driver.switchTo().defaultContent();
        driver.switchTo().frame("cont_right_show");

        // 借款申请
        driver.findElement(By.xpath("//a[contains(text(),'业务申请')]")).click();
        driver.switchTo().defaultContent();

        Thread.sleep(2000);
        driver.switchTo().frame("cont_right_show");

        //借款用途
        we.setSelectIndex(driver.findElement(By.id("loanPurpose")), 2);
        // 借款类型
        we.setSelectContent(driver.findElement(By.id("loanType")), Cache.loanType);
        if(Cache.loanType.equals("60") || Cache.loanType.equals("75") || Cache.loanType.equals("76")||Cache.loanType.equals("77")||Cache.loanType.equals("78")){
            // 供链贷 借款主体
            we.setSelectContent(driver.findElement(By.id("repay_subject")), "2");
            driver.findElement(By.id("core_enterprise")).sendKeys("核心企业");
            driver.findElement(By.xpath("//input[@class='assure_measure' and @value='2']")).click();
            driver.findElement(By.id("specificMeasures")).sendKeys("具体措施为");

        }else if(Cache.loanType.equals("67")){
            //客户类型
            we.setSelectContent(driver.findElement(By.id("busCusType")), "2");
        }
        // 申请金额
        driver.findElement(By.id("applicationsAmount1")).sendKeys(Cache.amount);
        // 月还款能力
        driver.findElement(By.id("applicationsAmount2")).sendKeys("30000");
        // 申请期限
        we.setSelectContent(driver.findElement(By.id("period")), Cache.period);
        String loanType = Cache.loanType;
        Thread.sleep(1000);
        // 还款方式
        we.setSelectContent(driver.findElement(By.id("repayMentWay")), Cache.repayMentWay);
        Thread.sleep(3000);

        // 服务费收取方式
        we.setSelectContent(driver.findElement(By.id("serviceChargeWay")), Cache.serviceChargeWay);
       // 月综合费率
//        we.setSelectIndex(driver.findElement(By.id("inputMouthRate")), 1);   // 1.8 自动带出

        // 还款来源
        driver.findElement(By.name("repayFrom")).sendKeys("还款来源");
        // 用款计划
        driver.findElement(By.name("useMoneyPlan")).sendKeys("用款计划");
        // 还款计划
        driver.findElement(By.name("repayPlan")).sendKeys("还款计划");

        // 客户经理 郭小霞
        we.setSelectIndex(driver.findElement(By.id("cusManagerId")), 2);

        we.scroolRoot(driver);
        Thread.sleep(2000);
        // 添加附件
        we.fujian(driver);


        // 判断附件是否添加完成
//        WebElement e = driver.findElement(By.xpath("//span[contains(text(),'上传成功！')]"));
//        driver.switchTo().frame("file");
//        By by = By.xpath("//a[contains(text(),'删除')]");
//        we.waitToThread(driver, by, 1000);
//        driver.switchTo().defaultContent();
//        driver.switchTo().frame("cont_right_show");

        //

        we.highLightElement(driver, driver.findElement(By.id("saveButton")));
        driver.findElement(By.id("saveButton")).click();

        if(we.waitToThread(driver, By.xpath("//div[@class ='messageDiv']/img"), 2000)){
            System.out.printf("申请借款" + driver.findElement(By.xpath("//div[@class ='messageDiv']")).getText() + "\n");
        }
        driver.switchTo().defaultContent();

        driver.findElement(By.xpath("//a[contains(text(),'退出登录')]")).click();
        driver.quit();
        System.out.printf("借款申请结束");

    }




}
