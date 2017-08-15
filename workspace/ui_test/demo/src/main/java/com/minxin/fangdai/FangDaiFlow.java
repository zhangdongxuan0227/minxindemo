package com.minxin.fangdai;

import com.minxin.Cache.Cache;
import com.minxin.util.ExcelDataUtil;
import com.minxin.fangdai.*;

import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.*;

/**
 * Created by Administrator on 2017/7/21.
 */
public class FangDaiFlow {

    AddUser au = new AddUser();
    ChuShen cs = new ChuShen();
    DiaoCha dc = new DiaoCha();
    FenDan fd = new FenDan();
    LoanQualityChecking lqc = new LoanQualityChecking();
    SureShen ss = new SureShen();
    UploadInfo up = new UploadInfo();
    XiaoZhiShen xzs = new XiaoZhiShen();
    XinShenZhongShen xszs = new XinShenZhongShen();
    ZhongShen zs = new ZhongShen();

    public void fangdaiflow(String url, String kefuname, String fendanname, String fchushenname, String fzhongshenname, String qjingli01name,String quanzhengren01, String diyapassword, String xiaozhi) throws Exception{
        au.addNewUser(url, kefuname, diyapassword);
        au.addOldUser(url, kefuname, diyapassword);
        au.applicationInformation();
        au.zhiyeInfor(url, kefuname, diyapassword);
        au.exchangeLianXiInfo(url, kefuname, diyapassword);
        au.exchangeDiYaInfo(url, kefuname, diyapassword);
        au.exchangeFuJianInfo(url, kefuname, diyapassword);

        // 房贷调查
        dc.diaocha(url,kefuname,diyapassword);
        // 房贷质检
        lqc.loanQualityChecking(url,kefuname,diyapassword);
        // 房贷分单
        fd.fenDan(url, fendanname,diyapassword);
        // 房贷初审
        cs.chuShen(url,fchushenname,diyapassword);
         //房贷终审
        zs.zhongshen(url, fzhongshenname, diyapassword);
        // 房贷确认
        ss.sureShen(url,qjingli01name,diyapassword);
        // 权证人上传合同
        up.uploadInfo(url, quanzhengren01, diyapassword);
        //销支审核
        xzs.xiaoZhiShen(url, xiaozhi, diyapassword);
        //权证人上传受理单
        up.uploadDanJu(url, quanzhengren01, diyapassword);
        //信审终审
        xszs.xinShenZhongShen(url,fzhongshenname,diyapassword);
        // 销支审核
        xzs.xiaoZhiShen(url, xiaozhi,diyapassword);


    }


    @Parameters({ "url","kefuname", "fendanname", "fchushenname" ,"fzhongshenname" ,"qjingli01name" ,"quanzhengren01" ,"diyapassword", "xiaozhi"})
    @Test(dataProvider = "db1")
    public void get(Map<String, String> data, ITestContext context)throws Exception{
        String url = context.getCurrentXmlTest().getParameter("url");
        String kefuname = context.getCurrentXmlTest().getParameter("kefuname");
        String fendanname = context.getCurrentXmlTest().getParameter("fendanname");
        String fchushenname = context.getCurrentXmlTest().getParameter("fchushenname");
        String fzhongshenname = context.getCurrentXmlTest().getParameter("fzhongshenname");
        String qjingli01name = context.getCurrentXmlTest().getParameter("qjingli01name");
        String quanzhengren01 = context.getCurrentXmlTest().getParameter("quanzhengren01");
        String diyapassword = context.getCurrentXmlTest().getParameter("diyapassword");
        String xiaozhi = context.getCurrentXmlTest().getParameter("xiaozhi");

        this.getData(data,url, kefuname, fendanname, fchushenname, fzhongshenname, qjingli01name, quanzhengren01, diyapassword, xiaozhi  );
        System.out.printf("*******************  此次脚本执行结束  **************************");
    }


    public void getData(Map<String, String> data, String url, String kefuname, String fendanname, String fchushenname, String fzhongshenname, String qjingli01name,String quanzhengren01, String diyapassword, String xiaozhi) throws Exception{
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
        this.fangdaiflow(url, kefuname, fendanname, fchushenname, fzhongshenname, qjingli01name,quanzhengren01,diyapassword,xiaozhi);

    }

    @DataProvider(name = "db1")
    public Iterator<Object[]> data() throws Exception{
        return (Iterator<Object[]>)new ExcelDataUtil("data", "Sheet2");
    }

}
