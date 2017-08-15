package com.minxin.util;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import org.testng.Assert;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Administrator on 2017/4/26.
 */
public class ExcelDataUtil implements Iterator<Object[]> {

    private Workbook book = null;
    private Sheet sheet = null;
    private int rowNum = 0; // 行数
    private int curRowNo = 0; // 当前行数
    private int columnNum = 0;//列数
    private String[] columnnName ; // 列名

    public ExcelDataUtil(String filepath, String casename){
        try{

            String path = "E:\\jenkins\\workspace\\ui_test\\demo\\data\\" + filepath + ".xls";
            InputStream inputStream = new FileInputStream(path);

            book = Workbook.getWorkbook(inputStream);
            sheet = book.getSheet(casename);
            this.rowNum = sheet.getRows();

            Cell[] c = sheet.getRow(0);
            this.columnNum = c.length;
            columnnName = new String[c.length];
            for(int i =0; i<c.length; i++){
                columnnName[i] = c[i].getContents().toString();
            }
            this.curRowNo++;

        }catch (Exception e){
            e.printStackTrace();
            Assert.fail("unable to read Excel data");
        }
    }


    /**
     * *方法功能：是否有下一条数据
     *如果行数为0即空sheet或者 当前行数大于总行数
     *就关闭对excel的操作返回false，否则返回true
     */


    public boolean hasNext(){
        if(rowNum == 0 || curRowNo >= rowNum){
            try{
                book.close();
            }catch (Exception e){
                e.printStackTrace();
            }
            return false;
        }else{
            return true;
        }
    }

    @Override
    public Object[] next(){
        Cell[] c = sheet.getRow(this.curRowNo);
        Map<String, String > s = new TreeMap<String, String>();
        for(int i = 0; i < columnNum; i++){
            String temp = "";
            try{
                temp = c[i].getContents().toString();
            }catch(ArrayIndexOutOfBoundsException ex){
                temp = "";
            }
            s.put(columnnName[i], temp);
        }

        Object r[] = new Object[1];
        r[0] = s;
        this.curRowNo++;
        return r;
    }

    @Override
    public void remove(){
        throw new UnsupportedOperationException("remove unsupported.");
    }
}
