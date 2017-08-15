package com.minxin.util;

import com.minxin.Cache.Cache;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;

/**
 * Created by Administrator on 2017/7/20.
 */
public class GetData {

    LogUtil log = new LogUtil(GetData.class);


    @Test(dataProvider = "db1")
    public void get(Map<String, String> data)throws Exception{
        this.getData(data);
        System.out.printf("******************************");
    }
    public void getData(Map<String, String> data) throws Exception{
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

        //log.info("name = " + Cache.name +"\n"+ "phone =" + Cache.phone +"\n"+ "shenfenzhen = " + Cache.shenfenzhen +"\n"+ "amount=" + Cache.amount +"\n"+ "loanType=" + Cache.loanType +"\n"+ "period =" + Cache.period+"\n"+ "repayMentWay =" + Cache.repayMentWay+"\n"+ "serviceChargeWay =" + Cache.serviceChargeWay+"\n");
    }

    @DataProvider(name = "db1")
    public Iterator<Object[]> data() throws Exception{
        return (Iterator<Object[]>)new ExcelDataUtil("data", "Sheet1");
    }

}
