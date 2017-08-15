package com.minxin.zonghe;

import com.minxin.Cache.Cache;
import com.minxin.util.ExcelDataUtil;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.*;

/**
 * Created by Administrator on 2017/7/20.
 */
public class ZongHeFlow {

    AddUser au = new AddUser();
    FenDan fd = new FenDan();
    ZhiJian zj = new ZhiJian();
    XSFenDan xfd = new XSFenDan();
    ChuShen cs = new ChuShen();
    ZhongShen zs = new ZhongShen();
    Sure sure = new Sure();


//    @Parameters({ "url","zname", "zpassword", "zfendan" ,"zxinshen" ,"zchushen" ,"zzhongshen" ,"zRobin" })
//    @Test(description = "综合业务进件流程")
    public void zongHeFlow(String url, String zname,String zpassword, String zfendan, String zxinshen, String zchushen, String zzhongshen, String zRobin)throws Exception{
        au.addUser(url, zname, zpassword);
        Reporter.log("*******************添加用户成功****************");
        System.out.printf("添加用户完成" + "\n");
        au.addLianXiInfo(url, zname, zpassword);
        au.addBorrow(url, zname, zpassword);

        // 分单操作
        fd.fendan(url, zfendan, zpassword);
        Reporter.log("*******************分单成功****************");
        System.out.printf("分单完成" + "\n");
        // 质检操作
        zj.zhijian(url, zname, zpassword);
        Reporter.log("*******************质检成功****************");
        System.out.printf("质检完成" + "\n");
         //信审分单
        xfd.xinshenfendan(url, zxinshen, zRobin);
        Reporter.log("*******************信审分单成功****************");
        System.out.printf("信审分单完成" + "\n");
         //初审
        cs.chushen(url, zchushen, zpassword);
        Reporter.log("*******************初审成功****************");
        System.out.printf("初审完成" + "\n");
        // 终审
        zs.zhongshen(url, zzhongshen, zpassword);
        Reporter.log("*******************终审成功****************");
        System.out.printf("终审完成" + "\n");
        sure.sure(url, zxinshen, zRobin);

        Reporter.log("*******************确认借款****************");
        System.out.printf("确认借款完成" + "\n");
    }



    @Parameters({ "url","zname", "zpassword", "zfendan" ,"zxinshen" ,"zchushen" ,"zzhongshen" ,"zRobin" })
    @Test(dataProvider = "db1")
    public void get(Map<String, String> data, ITestContext context)throws Exception{
        String url = context.getCurrentXmlTest().getParameter("url");
        String zname = context.getCurrentXmlTest().getParameter("zname");
        String zpassword = context.getCurrentXmlTest().getParameter("zpassword");
        String zfendan = context.getCurrentXmlTest().getParameter("zfendan");
        String zxinshen = context.getCurrentXmlTest().getParameter("zxinshen");
        String zchushen = context.getCurrentXmlTest().getParameter("zchushen");
        String zzhongshen = context.getCurrentXmlTest().getParameter("zzhongshen");
        String zRobin = context.getCurrentXmlTest().getParameter("zRobin");

        this.getData(data,url, zname, zpassword, zfendan, zxinshen, zchushen, zzhongshen, zRobin  );
        System.out.printf("*******************  此次脚本执行结束  **************************");
    }
    public void getData(Map<String, String> data, String url, String zname, String zpassword,String zfendan,String zxinshen, String zchushen, String zzhongshen, String zRobin) throws Exception{
        Set<String> set = data.keySet();
        Iterator<String> it = set.iterator();
        List list = new ArrayList<String>();

        for(; it.hasNext() ;){
            String s = it.next();
            list.add(s);
            if(s.equals("name")){
                System.out.printf("name = "+ data.get(s));
                Cache.name = data.get(s);
            }else if(s.equals("phone")){
                System.out.printf("phone = " + data.get(s));
                Cache.phone = data.get(s);
            }else if(s.equals("shenfenzhen")){
                System.out.printf("shenfenzhen = " + data.get(s));
                Cache.shenfenzhen = data.get(s);
            }else if(s.equals("amount")){
                System.out.printf("amount = " + data.get(s));
                Cache.amount = data.get(s);
            }else if(s.equals("loanType")){
                System.out.printf("loanType = " + data.get(s));
                Cache.loanType = data.get(s);
            }else if(s.equals("period")){
                System.out.printf("period = " + data.get(s));
                Cache.period = data.get(s);
            }else if(s.equals("repayMentWay")){
                System.out.printf("repayMentWay = " + data.get(s));
                Cache.repayMentWay = data.get(s);
            }else if(s.equals("serviceChargeWay")){
                System.out.printf("serviceChargeWay = " + data.get(s));
                Cache.serviceChargeWay = data.get(s);
            }
        }
        System.out.printf("name = " + Cache.name + "\n" + "phone =" + Cache.phone + "\n" + "shenfenzhen = " + Cache.shenfenzhen + "\n" + "amount=" + Cache.amount + "\n" + "loanType=" + Cache.loanType + "\n" + "period =" + Cache.period + "\n" + "repayMentWay =" + Cache.repayMentWay + "\n" + "serviceChargeWay =" + Cache.serviceChargeWay + "\n");
        this.zongHeFlow(url,zname,zpassword,zfendan,zxinshen,zchushen,zzhongshen,zRobin);

    }

    @DataProvider(name = "db1")
    public Iterator<Object[]> data() throws Exception{
        return (Iterator<Object[]>)new ExcelDataUtil("data", "Sheet1");
    }
}
