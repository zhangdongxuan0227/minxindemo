package com.minxin.util;

import org.openqa.selenium.WebDriver;

//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.ie.InternetExplorerDriver;
//import org.openqa.selenium.phantomjs.PhantomJSDriver;

/**
 * Created by Administrator on 2017/7/19.
 */
public class DriverToBrowser {

    private static WebDriver driver;


    /**
     * 进入后台界面
     * @param url  所访问网址
     * @param key   通过其来判断使用何种浏览器
     * @return
     */
    public WebDriver login(String url, int key){
        startDriver(key);
        driver.get(url);
        driver.manage().window().maximize();
        return driver;
    }

    private void startDriver(int key) {
    }


    /**
     * 打开IE 、 Firefox、Chrome 浏览器
     * @param key
     * @return
     */
    /*public static WebDriver startDriver(int key) {
        switch(key) {
            case 0:
                //ͨ打开IE浏览器
                File file =new File("C:/Program Files/Internet Explorer/IEDriverServer.exe");
                System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
                DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
                ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
                driver = new InternetExplorerDriver(ieCapabilities);

                break;
            case 1:
                //打开火狐浏览器
                driver = new FirefoxDriver();
                break;
            case 2:
                // 打开chrome浏览器
                File file1 = new File("E:/webdriver/chromedriver.exe");
                System.setProperty("webdriver.chrome.driver", file1.getAbsolutePath());
                driver = new ChromeDriver();
                break;
            case 3:
                System.setProperty("phantomjs.binary.path", "D:\\Tool\\phantomjs-1.9.7-windows\\phantomjs.exe");
                driver = new PhantomJSDriver();
                driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);


        }
        return driver;

    }*/

}

