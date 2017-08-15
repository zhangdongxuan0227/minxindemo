package com.minxin.util;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/7/19.
 */
public class WebElementUtil {

    LogUtil log = new LogUtil(WebElementUtil.class);


    /**
     * 根据索引值来对下拉框进行选择操作
     * @param element： 控件
     * @param index：索引值，从0开始计算
     */
    public void setSelectIndex(WebElement element, int index) {
        Select select = new Select(element);
        select.selectByIndex(index);
        log.info("下拉框所选值为"+ element.getText());

    }

    /**
     * 根据下拉列表中的选项value 属性来进行选择
     * @param element
     * @param value
     */
    public void setSelectContent(WebElement element, String value) {
        Select select = new Select(element);
        select.selectByValue(value);

        log.info("下拉框所选的值为：" + value);
    }

    /**
     * 根据下拉列表中选项的可见文本进行选择
     * @param element
     * @param value
     */
    public void setSelectByVisibleText(WebElement element, String value){
        Select select = new Select(element);
        select.selectByVisibleText(value);
    }

    public void setSelectAll(WebElement element){
        Select select = new Select(element);
        for(WebElement e : select.getOptions()){
            e.click();
        }
    }


    // 单选项（Radio Button）
    //判断是否被选择（可以判断单选和多选控件，如果已经选择返回false，否则返回true
    public Boolean isSelect(WebElement element){
        Boolean status = false;
        if(!element.isSelected())
            status = true;
        else{
            status = false;
            log.info("该单选按钮已经被选择");
        }
        return status;
    }

    //选择操作
    public void selectRadio(WebElement element){
        if(this.isSelect(element))
            element.click();

    }

    public void clickAlertSure1(WebDriver driver, Boolean status) throws Exception{
        Alert alert = null;
        int count = 0;
        while(alert == null && count < 10){
            try {
                Thread.sleep(1000);
                System.out.println("wait once.");
                alert = driver.switchTo().alert();
                count ++;
                log.info("count 值为:" + count);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(status){
            log.info("点击确认按钮" + "弹框内容为：" +alert.getText());
            Thread.sleep(200);
            alert.accept();  //点击确认按钮

        }else {
            alert.dismiss();  //点击取消按钮
            log.info("点击取消按钮");
        }

    }


    /**
     * 获取弹窗的内容
     * @param driver
     * @return
     */

    public String getPromptMessage(WebDriver driver) {
        Alert alert = null;
        int count = 0;
        while(alert == null && count < 10){
            try {
                Thread.sleep(2000);
                System.out.println("wait once.");
                alert = driver.switchTo().alert();
                count ++;
                log.info("count 值为:" + count);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        log.info("获取弹框内容为：" + alert.getText());
        return alert.getText();
    }




    // 修改属性
    public void moidfyJs(WebDriver driver){

        ((JavascriptExecutor)driver).executeScript("document.getElementById(\"SWFUpload_0\").type ='file';");
    }


    /**
     * 通过Thread 线程等待的方式来判断元素是否存在
     * @param driver
     * @param by
     * @param time： 等待时间
     * @return
     * @throws Exception
     */
    public boolean waitToThread(WebDriver driver, By by, int time) throws Exception{
        boolean staus = false;
        int i=0;
        while(i<20){
            try{
                Thread.sleep(time);
                driver.findElement(by);
                System.out.printf("11111" + i +"\n");
                staus = true;
                i++;
                break;
            } catch (java.util.NoSuchElementException e){
                System.out.printf("元素在页面不存在");

                staus = false;
            }
        }

        return staus;
    }

    public boolean waitToElement(WebDriver driver, By by, int time) throws Exception{
        boolean staus = false;
        int i=0;
        while(i < 9){
            try{
                System.out.printf("元素在页面存在" + i);
                WebElement element = driver.findElement( By.id("q_cusName1"));
                System.out.printf("元素在页面存在" + element);
                if(element.isDisplayed()){
                    System.out.printf("元素在页面存在");
                    staus = true;
                    break;
                }else{
                    Thread.sleep(time);
                    System.out.printf("等待");
                    i++;
                }
            }catch (Exception e){
                e.printStackTrace();
            }



        }
        System.out.printf("等待1");
        return staus;
    }



    public void waitForElement(WebDriver driver, By selector) throws Exception{
        for (int i = 0; i < 10; i++) {
            try {
                System.out.println("Trying to locate element and initialize the page ......");
                WebElement element = driver.findElement(selector);
                if (element.isDisplayed()) {
                    System.out.printf("元素" + element.isDisplayed());
                    break;
                } else {
                    System.out.println("Waiting for elements to initialize the page ......");
                    try {
                        Thread.sleep(2000);
                        System.out.printf("wait one");
                    } catch ( TimeoutException e1) {
                        e1.printStackTrace();
                        System.out.println("Fail! Element find by " + selector.toString() + " didn't display!");
//                        fail("Fail! Element find by " + selector.toString() + " didn't display!");
                    }
                    continue;
                }
            } catch (NoSuchElementException e) {
                if (i == 9) {
                    e.printStackTrace();
                    System.out.println("Waiting for elements to initialize the page ......");
                }
                try {
                    Thread.sleep(1000);
                } catch ( TimeoutException e1) {
                    e1.printStackTrace();
                    System.out.println("Fail! Element find by " + selector.toString() + " didn't display!");
//                    fail("Fail! Element find by " + selector.toString() + " didn't display!");
                }
            }
        }
    }










    public void fujian(WebDriver driver) throws Exception{
        // 根据图片点击上传附件按钮

        Screen s = new Screen();
        Pattern up = new Pattern("E:\\jenkins\\workspace\\ui_test\\demo\\data\\1.png");
        if(s.find(up) != null){
            System.out.printf("附件" + s.find(up));
            s.click();
        }
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        String path = "E:\\jenkins\\workspace\\ui_test\\demo\\data\\Test.rar";
        Pattern content = new Pattern("E:\\jenkins\\workspace\\ui_test\\demo\\data\\2.png");
        s.type(content, path);
        Pattern open = new Pattern("E:\\jenkins\\workspace\\ui_test\\demo\\data\\3.png");
        if(s.find(open) != null){
            s.click();
        }
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        Thread.sleep(5000);
    }


    public void fujian(WebDriver driver, int num, int num2, int num3) throws Exception{
        Screen s = new Screen();
        Pattern up = new Pattern("E:\\jenkins\\workspace\\ui_test\\demo\\data\\"+ num +".png");

        if(s.find(up) != null){
            System.out.printf("附件" + s.find(up));
            s.click();
        }
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        String path = "E:\\jenkins\\workspace\\ui_test\\demo\\data\\Test.rar";
        Pattern content = new Pattern("E:\\jenkins\\workspace\\ui_test\\demo\\data\\"+ num2 +".png");
        s.type(content, path);
        Pattern open = new Pattern("E:\\jenkins\\workspace\\ui_test\\demo\\data\\"+ num3 +".png");
        if(s.find(open) != null){
            s.click();
        }
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        Thread.sleep(5000);

    }


    /**
     * 查找上传附件左边的控件，通过右偏移来实现上传附件
     * @param driver
     * @param type 上传个数
     * @param num 图片名 数字
     * @throws Exception
     */

    public void fujian(WebDriver driver, int type, int num) throws Exception {

        Screen s = new Screen();

        for(int i=0; i< type; i++){

            Pattern up = new Pattern("E:\\jenkins\\workspace\\ui_test\\demo\\data\\"+ num +".png");
            System.out.printf("num 为" + num);
            num++;
            s.find(up).right(70).click();

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




    // 获取当前控件所在位置
    public int[] position(WebElement element){

        int[] arr = new int[2];

        System.out.println("X坐标：" + element.getLocation().getX());
        arr[0] = element.getLocation().getX();
        System.out.println("Y坐标：" + element.getLocation().getY());
        arr[1] = element.getLocation().getY();
        log.info("当前控件所在坐标为："+ "x="+arr[0]+"Y=" + arr[1]);

        return arr;

    }



    //  切换窗口
    public  WebDriver switchToWindow(WebDriver driver) {
        WebDriver driverWindow = null;
        System.out.printf("窗口的标题为： " + driver.getTitle());
        String handle = driver.getWindowHandle();
        for(String e : driver.getWindowHandles()) {
            if(e.equals(handle))
                continue;
            driverWindow = driver.switchTo().window(e);

        }
        System.out.printf("窗口的标题为： " + driverWindow.getTitle());

        return driverWindow;

    }



    //滑到页面底部
    public void scroolRoot(WebDriver driver){

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
        log.info("滚动条已经滑动到底部");

    }


    public void clickElementAction(WebDriver driver ) {
        Actions acitons = new Actions(driver);
        acitons.sendKeys(Keys.ENTER).perform();
    }

    // 页面控件高亮显示
    public  void highLightElement(WebDriver driver, WebElement element){

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("element = arguments[0];" + "original_style = element.getAttribute('style');" + "element.setAttribute('style', original_style + \";" + "background: yellow; border: 2px solid red;\");" + "setTimeout(function(){element.setAttribute('style', original_style);}, 1000);", element);

    }

    //隐士等待
    public  void waitImplicitly(WebDriver driver, int time){

        driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
    }

























}
