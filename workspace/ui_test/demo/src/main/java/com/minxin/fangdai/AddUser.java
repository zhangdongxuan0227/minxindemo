package com.minxin.fangdai;

import com.minxin.Cache.Cache;
import com.minxin.util.WebElementUtil;
import com.minxin.zonghe.Login;
import com.minxin.fangdai.PageAction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

/**
 * Created by Administrator on 2017/7/21.
 */
public class AddUser {

    // 借款标题
    private String loanTitle = Cache.name +"抵押贷";

    Login login = new Login();
    private static WebDriver driver;
    WebElementUtil we = new WebElementUtil();
    PageAction pa = new PageAction();

    int num=5;

    @Test
    public void addNewUser(String url, String kefuname, String diyapassword) throws Exception{

        login.login(url, kefuname, diyapassword, 0);
        driver = Login.getDriver();
        driver.findElement(By.xpath("//a[contains(text(),'房贷业务管理')]")).click();

        driver.switchTo().frame("cont_right_show");
        driver.findElement(By.xpath("//input[@id='btn21363' and @value='新增']")).click();

        driver.switchTo().frame("xubox_iframe1");

//        driver.findElement(By.xpath(".//*[@id='queryMainForm']/dl[2]/dd/input[3]")).click();
        if(we.waitToThread(driver, By.xpath("//input[@type='button' and @value='新增']"),2000)){
            driver.findElement(By.xpath("//input[@type='button' and @value='新增']")).click();
        }

        driver.switchTo().defaultContent();

        driver.switchTo().frame("cont_right_show");
        driver.switchTo().frame("addLoanApp1");
        Thread.sleep(3000);
        driver.findElement(By.id("realName")).sendKeys(Cache.name);
        we.setSelectContent(driver.findElement(By.id("sexuality")), "1");

        // 性别
        WebElement sexuality = driver.findElement(By.id("sexuality"));
        we.setSelectContent(sexuality, "0");

        // 证件类型
        WebElement idType = driver.findElement(By.id("idType"));
        we.setSelectContent(idType, "0");

        //证件号码
        WebElement id_number = driver.findElement(By.id("id_number"));
        id_number.sendKeys(Cache.shenfenzhen);

        // 发证机关
        WebElement fazhengjiguan = driver.findElement(By.id("issuingAuthority"));
        fazhengjiguan.sendKeys("北京市朝阳区公安局");

        WebElement changqiyouxiao = driver.findElement(By.id("checkboxided"));
        we.selectRadio(changqiyouxiao);

        // 学历
        WebElement eduactionCd = driver.findElement(By.id("eduactionCd"));
        we.setSelectContent(eduactionCd, "2");
        WebElement maritalStatusCd = driver.findElement(By.id("maritalStatusCd"));
        we.setSelectContent(maritalStatusCd, "0");
        // 证件有效期
        WebElement idIssuingDate = driver.findElement(By.id("idIssuingDate"));
        idIssuingDate.click();
        WebElement dateIframe = driver.findElement(By.xpath("//body/div[2]/iframe"));
        driver.switchTo().frame(dateIframe);
        WebElement today = driver.findElement(By.id("dpTodayInput"));
        today.click();
        System.out.printf("日期标题为" + today.getText());
        driver.switchTo().defaultContent();
        driver.switchTo().frame("cont_right_show");
        driver.switchTo().frame("addLoanApp1");
        // 居住情况
        WebElement livingStatusCd = driver.findElement(By.id("livingStatusCd"));
        we.setSelectContent(livingStatusCd, "1");
        WebElement province = driver.findElement(By.xpath("//div[@id ='familyAddress']/dl/dd/select[@id='province']"));
        we.setSelectContent(province, "110000");
        WebElement city = driver.findElement(By.xpath("//div[@id ='familyAddress']/dl/dd/select[@id='city']"));
        we.setSelectContent(city, "110100");
        WebElement county = driver.findElement(By.xpath("//div[@id ='familyAddress']/dl/dd/select[@id='county']"));
        we.setSelectContent(county, "110101");
        WebElement detailAddress = driver.findElement(By.xpath("//div[@id ='familyAddress']/dl/dd/input"));
        detailAddress.sendKeys("凯富大厦");
        // 现住地址
        we.setSelectContent(driver.findElement(By.id("contactprovince")), "110000");
        we.setSelectContent(driver.findElement(By.id("contactcity")), "110100");
        we.setSelectContent(driver.findElement(By.id("contactcounty")), "110101");
        we.waitImplicitly(driver, 1000);
        driver.findElement(By.name("contactAddress")).sendKeys("居住地址");

        WebElement tel = driver.findElement(By.id("tel"));
        tel.sendKeys(Cache.phone);
        driver.findElement(By.id("EMail")).sendKeys(Cache.phone + "@qq.com");

        WebElement saveButton = driver.findElement(By.id("saveBtn"));
        saveButton.click();


        if(we.waitToThread(driver, By.xpath("//div[@class ='messageDiv']"), 2000)){
            System.out.printf("添加新用户" + driver.findElement(By.xpath("//div[@class ='messageDiv']")).getText() + "\n");
        }
        driver.switchTo().defaultContent();


//        driver.quit();

    }


    @Test
    public void addOldUser(String url, String kefuname, String diyapassword) throws Exception{

        login.login(url,kefuname,diyapassword, 0);
        driver = Login.getDriver();
        Thread.sleep(3000);

        driver.findElement(By.xpath("//a[contains(text(),'房贷业务管理')]")).click();

        driver.switchTo().frame("cont_right_show");
        driver.findElement(By.xpath("//input[@id='btn21363' and @value='新增']")).click();

        driver.switchTo().frame("xubox_iframe1");


        Thread.sleep(3000);
        driver.findElement(By.xpath(".//*[@id='queryMainForm']/dl[2]/dd/input[3]")).click();
        driver.switchTo().defaultContent();

        driver.switchTo().frame("cont_right_show");
        driver.switchTo().frame("addLoanApp1");
        Thread.sleep(3000);

        driver.findElement(By.id("id_number")).sendKeys(Cache.shenfenzhen);
        driver.findElement(By.id("realName")).click();
        driver.switchTo().defaultContent();


    }

    @Test(description = "申请信息")
    public void applicationInformation() throws Exception{

        driver.switchTo().frame("cont_right_show");

        driver.findElement(By.xpath("//a[contains(text(),'申请信息')]")).click();
        driver.switchTo().frame("addLoanApp2");
        //申请额度
        driver.findElement(By.id("amount")).sendKeys(Cache.amount);

        WebElement loanTypeElement = driver.findElement(By.id("loanType"));
        we.setSelectContent(loanTypeElement, "26");

        // 借款期限
        WebElement loanTimes = driver.findElement(By.id("loanTimes"));
        we.setSelectContent(loanTimes, Cache.period);

        WebElement payType = driver.findElement(By.id("payType"));   // 还款方式
        we.setSelectContent(payType, Cache.repayMentWay);
        // 服务费收取方式
        we.setSelectContent(driver.findElement(By.id("applyFeeChargeWayCD")), Cache.serviceChargeWay);

        // 借款用途
        WebElement loanPurposeCd = driver.findElement(By.id("loanPurposeCd"));
        we.setSelectContent(loanPurposeCd, "10");

        we.waitImplicitly(driver, 2000);
        WebElement otherStatements = driver.findElement(By.id("otherStatements"));
        otherStatements.sendKeys(loanTitle);

        // 抵押率
        driver.findElement(By.id("mortgageRate")).sendKeys("3");
//        driver.findElement(By.id("serviceRate")).sendKeys("6");
        //还款类型
        driver.findElement(By.xpath("//input[@name='paymentMan' and @value='1']")).click();

        driver.findElement(By.id("mortgageMoney")).sendKeys("30000");
        driver.findElement(By.id("secondMortgageMoney")).sendKeys("500000");

        WebElement cusChannelManagerId = driver.findElement(By.id("cusChannelManagerId"));
//        we.setSelectContent(cusChannelManagerId, "33637");
        we.setSelectIndex(cusChannelManagerId, 1);
        //32462  测试环境

        driver.findElement(By.id("btnTemporaryId")).click();
        driver.switchTo().defaultContent();
        driver.switchTo().frame("cont_right_show");
        driver.switchTo().frame("addLoanApp2");

        if(we.waitToThread(driver, By.xpath("//div[@class ='messageDiv']"), 2000)){
            System.out.printf("填写申请信息" + driver.findElement(By.xpath("//div[@class ='messageDiv']")).getText() + "\n");
            //保存成功！
        }
        driver.switchTo().defaultContent();

        driver.quit();

    }

    @Test(description = "职业信息")
    public void zhiyeInfor(String url, String kefuname, String diyapassword) throws Exception{

        login.login(url, kefuname, diyapassword, 0);
        driver = Login.getDriver();

        if(we.waitToThread(driver,By.xpath("//a[contains(text(),'房贷业务管理')]"), 3000 )){
            driver.findElement(By.xpath("//a[contains(text(),'房贷业务管理')]")).click();
        }


        driver.switchTo().frame("cont_right_show");

        driver.findElement(By.id("q_realName")).sendKeys(Cache.name);
        driver.findElement(By.id("searchButton")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//a[contains(text(),'修改') and @id ='link_21157_1']")).click();
        driver.switchTo().defaultContent();

        driver.switchTo().frame("cont_right_show");
        // 切换到职业信息
        WebElement zhiyeInfo = driver.findElement(By.id("ui-id-4"));
        zhiyeInfo.click();

        driver.switchTo().frame("addLoanApp3");


        // 职业类型
        WebElement professionType = driver.findElement(By.id("professionType"));
        we.setSelectContent(professionType, "1");

        // 职业信息
        WebElement company = driver.findElement(By.id("company"));
        company.sendKeys("民信贷");

        WebElement companyType = driver.findElement(By.id("companyType"));
        we.setSelectContent(companyType, "1");

        WebElement makeProfession = driver.findElement(By.id("makeProfession"));
        we.setSelectContent(makeProfession, "1");

        WebElement profession = driver.findElement(By.id("profession"));
        we.setSelectContent(profession, "131");

        WebElement professionDetailCode = driver.findElement(By.id("professionDetailCode"));
        we.setSelectContent(professionDetailCode, "145");

        WebElement workDept = driver.findElement(By.id("workDept"));
        workDept.sendKeys("销售部门");
        // 职级
        WebElement rank = driver.findElement(By.id("rank"));
        we.setSelectContent(rank, "2");

        WebElement workingYear = driver.findElement(By.id("workingYear"));
        workingYear.sendKeys("2");
        // 单位地址
        WebElement companyProvince = driver.findElement(By.id("companyProvince"));
        we.setSelectContent(companyProvince, "110000");
        WebElement companyCity = driver.findElement(By.id("companyCity"));
        we.setSelectContent(companyCity, "110100");
        WebElement companyCounty = driver.findElement(By.id("companyCounty"));
        we.setSelectContent(companyCounty, "110101");
        WebElement companyAddress = driver.findElement(By.id("companyAddress"));
        companyAddress.sendKeys("凯富大厦");

        // 收入方式
        WebElement incomeType = driver.findElement(By.id("incomeType"));
        we.setSelectContent(incomeType, "1");

        WebElement salaryDate = driver.findElement(By.id("salaryDate"));
        salaryDate.sendKeys("5");

        WebElement monthIncome = driver.findElement(By.id("monthIncome"));
        monthIncome.sendKeys("3000");

        // 单位电话
        driver.findElement(By.id("telephone")).sendKeys("010-6789456");
        // 职业类别
        we.setSelectIndex(driver.findElement(By.name("occupationType")), 2);
        //起始服务开始年

        driver.findElement(By.name("initiationserviceYear")).click();
        WebElement dateIframe = driver.findElement(By.xpath("//body/div[2]/iframe"));
        driver.switchTo().frame(dateIframe);
        driver.findElement(By.id("dpOkInput")).click();
        driver.switchTo().defaultContent();
        //起始服务开始月
        driver.switchTo().frame("cont_right_show");
        driver.switchTo().frame("addLoanApp3");
        driver.findElement(By.name("initiationserviceMonth")).click();
        WebElement dateIframe1 = driver.findElement(By.xpath("//body/div[2]/iframe"));
        driver.switchTo().frame(dateIframe1);
        driver.findElement(By.id("dpOkInput")).click();
        driver.switchTo().defaultContent();

        driver.switchTo().frame("cont_right_show");
        driver.switchTo().frame("addLoanApp3");
        WebElement saveButton = driver.findElement(By.xpath(".//*[@id='addForm']/div/div[4]/dl/dd/input"));
        saveButton.click();
        Thread.sleep(2000);

//        WebElement message = driver.findElement(By.xpath("//div[@class='messageDiv']"));
//        System.out.printf("职业信息提示信息" + message.getText());
        driver.switchTo().defaultContent();
        driver.switchTo().frame("cont_right_show");
        driver.switchTo().frame("addLoanApp3");
        if(we.waitToThread(driver, By.xpath("//div[@class ='messageDiv']"), 2000)){
            System.out.printf("填写职业信息" + driver.findElement(By.xpath("//div[@class ='messageDiv']")).getText() + "\n");
            //保存成功！
        }
        driver.switchTo().defaultContent();
        driver.switchTo().defaultContent();

        driver.quit();



    }

    @Test(description = "联系人")
    public void exchangeLianXiInfo(String url, String kefuname, String diyapassword) throws Exception {


        login.login(url, kefuname, diyapassword, 0);
        driver = Login.getDriver();

        Thread.sleep(3000);
        driver.findElement(By.xpath("//a[contains(text(),'房贷业务管理')]")).click();

        driver.switchTo().frame("cont_right_show");

        driver.findElement(By.id("q_realName")).sendKeys(Cache.name);
        driver.findElement(By.id("searchButton")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//a[contains(text(),'修改') and @id ='link_21157_1']")).click();
        driver.switchTo().defaultContent();

        driver.switchTo().frame("cont_right_show");

        // 切换到联系人信息

        WebElement lianxiren = driver.findElement(By.id("ui-id-5"));
        lianxiren.click();

        driver.switchTo().frame("addLoanApp4");

        for(int i=0; i<2; i++){
            pa.addContacts(driver, Cache.name, "1"); // 添加紧急联系人
        }

        driver.quit();
    }

    @Test(description = "抵押信息")
    public void exchangeDiYaInfo(String url, String kefuname, String diyapassword) throws Exception{

        login.login(url, kefuname, diyapassword, 0);
        driver = Login.getDriver();

        if(we.waitToThread(driver, By.xpath("//a[contains(text(),'房贷业务管理')]"), 2000)){
            driver.findElement(By.xpath("//a[contains(text(),'房贷业务管理')]")).click();
        }


        driver.switchTo().frame("cont_right_show");

        driver.findElement(By.id("q_realName")).sendKeys(Cache.name);
        driver.findElement(By.id("searchButton")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//a[contains(text(),'修改') and @id ='link_21157_1']")).click();
        driver.switchTo().defaultContent();

        driver.switchTo().frame("cont_right_show");

        //切换到抵押信息
        driver.findElement(By.id("ui-id-9")).click();

        driver.switchTo().frame("addLoanApp8");

        driver.findElement(By.id("propertyName")).sendKeys(Cache.name);
        driver.findElement(By.id("price")).sendKeys("60000");
        // 小区名称
        driver.findElement(By.name("compoundName")).sendKeys("小区名称");
        // 户型
        driver.findElement(By.name("houseTypeRoom")).sendKeys("2");
        driver.findElement(By.name("houseTypeHall")).sendKeys("2");
        driver.findElement(By.name("houseTypeToilet")).sendKeys("1");
        driver.findElement(By.name("houseTypeOther")).sendKeys("2");
        // 朝向
        driver.findElement(By.name("houseOrientation")).sendKeys("南北");
        // 总楼层
        driver.findElement(By.name("floorNumber")).sendKeys("10");
        // 所在楼层
        driver.findElement(By.name("currentFloor")).sendKeys("3");
        // 使用状况
        we.setSelectContent(driver.findElement(By.name("houseUsage")),"2");

        driver.findElement(By.id("motageTimes")).sendKeys("1");
        driver.findElement(By.id("houseArea")).sendKeys("100");
        // 建成年月
        driver.findElement(By.name("completedYear")).click();
        WebElement dateIframe = driver.findElement(By.xpath("//body/div[2]/iframe"));
        driver.switchTo().frame(dateIframe);
        WebElement today = driver.findElement(By.id("dpOkInput"));
        today.click();

        driver.switchTo().defaultContent();
        driver.switchTo().defaultContent();
        driver.switchTo().frame("cont_right_show");
        driver.switchTo().frame("addLoanApp8");
        // 建成月
        driver.findElement(By.name("completedMonth")).click();
        WebElement monthIframe = driver.findElement(By.xpath("//body/div[2]/iframe"));
        driver.switchTo().frame(monthIframe);
        driver.findElement(By.id("dpOkInput")).click();

        driver.switchTo().defaultContent();
        driver.switchTo().defaultContent();
        driver.switchTo().frame("cont_right_show");
        driver.switchTo().frame("addLoanApp8");

        driver.findElement(By.id("mortgageAmount")).sendKeys("70000");

        // 房产地址
        WebElement province = driver.findElement(By.id("province"));
        we.setSelectIndex(province, 1);

        WebElement city = driver.findElement(By.id("city"));
        we.setSelectIndex(city, 1);
        WebElement county = driver.findElement(By.id("county"));
        we.setSelectIndex(county, 1);
        driver.findElement(By.id("houseAddress")).sendKeys("北京凯富大厦");

        driver.findElement(By.id("initialAssetValue")).sendKeys("78000");

        WebElement propertyUse = driver.findElement(By.id("propertyUse"));
        we.setSelectIndex(propertyUse, 2);

        // 房屋类型
        we.setSelectIndex(driver.findElement(By.id("buildingType")),2);
        we.setSelectIndex(driver.findElement(By.id("propertyNature")), 2);
        we.setSelectIndex(driver.findElement(By.id("houseOwnerType")), 2);

        we.setSelectIndex(driver.findElement(By.id("houseClaimType")), 1);
        we.setSelectIndex(driver.findElement(By.id("getTheWay")), 1);

        // 财产类型
        we.setSelectIndex(driver.findElement(By.id("propertyType")), 1);
        // 是否夫妻共有
        we.setSelectIndex(driver.findElement(By.id("isCoupleCommon")), 1);

        //购置时间
        WebElement purchaseDate = driver.findElement(By.id("purchaseDate"));
        purchaseDate.click();
        WebElement dateIframe1 = driver.findElement(By.xpath("//body/div[2]/iframe"));
        driver.switchTo().frame(dateIframe1);
        driver.findElement(By.id("dpOkInput")).click();

        System.out.printf("日期标题为" + today.getText());

        driver.switchTo().defaultContent();
        driver.switchTo().frame("cont_right_show");
        driver.switchTo().frame("addLoanApp8");

        //房产证号
        WebElement housepropetyNumber = driver.findElement(By.id("housepropetyNumber"));
        housepropetyNumber.clear();
        housepropetyNumber.sendKeys("京房权证西字第12345号");
        //装修
        we.setSelectContent(driver.findElement(By.id("renovationType")), "1");

        // 评估价值
        driver.findElement(By.id("appraisalValue1")).sendKeys("60000");
        driver.findElement(By.name("appraisalSource1")).sendKeys("评估来源");
        driver.findElement(By.id("appraisalValue2")).sendKeys("60000");
        driver.findElement(By.name("appraisalSource2")).sendKeys("评估来源");
        driver.findElement(By.name("appraisalValue3")).sendKeys("60000");
        driver.findElement(By.name("appraisalSource3")).sendKeys("评估来源");

        WebElement saveButton = driver.findElement(By.xpath(".//*[@id='addForm']/div[2]/dl/dd/input"));
        saveButton.click();

        if(we.waitToThread(driver, By.xpath("//div[@class ='messageDiv']"), 2000)){
            System.out.printf("填写抵押信息：" + driver.findElement(By.xpath("//div[@class ='messageDiv']")).getText() + "\n");
            //保存成功！
        }

        driver.switchTo().defaultContent();
        driver.switchTo().defaultContent();
        Thread.sleep(2000);
        driver.quit();


    }

    @Test(description = "上传附件")
    public void exchangeFuJianInfo(String url, String kefuname, String diyapassword) throws Exception{

        login.login(url, kefuname,diyapassword, 0);
        driver = Login.getDriver();

        Thread.sleep(3000);
        driver.findElement(By.xpath("//a[contains(text(),'房贷业务管理')]")).click();

        driver.switchTo().frame("cont_right_show");

        driver.findElement(By.id("q_realName")).sendKeys(Cache.name);
        driver.findElement(By.id("searchButton")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//a[contains(text(),'修改') and @id ='link_21157_1']")).click();
        driver.switchTo().defaultContent();

        driver.switchTo().frame("cont_right_show");

        // 切换到附件

        driver.findElement(By.id("ui-id-7")).click();

        driver.switchTo().frame("addLoanApp6");
        driver.switchTo().frame("fileIframe");

        Thread.sleep(3000);
        we.fujian(driver, 2, 5);

        driver.switchTo().defaultContent();
        driver.switchTo().frame("cont_right_show");
        we.highLightElement(driver, driver.findElement(By.xpath("//input[@class='btn' and @value='提交']")));
        Thread.sleep(3000);
        driver.findElement(By.xpath("//input[@class='btn' and @value='提交']")).click();
        Thread.sleep(3000);
        if(we.waitToThread(driver, By.xpath("//div[@class='messageDiv']/img"), 3000)){

            String message = driver.findElement(By.xpath("//div[@class='messageDiv']")).getText();

            System.out.printf("进件信息" + message);
        }

        driver.quit();


    }


}
