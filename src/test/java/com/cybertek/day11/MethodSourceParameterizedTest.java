package com.cybertek.day11;

import com.cybertek.utilities.ExcelUtil;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class MethodSourceParameterizedTest {

//    public static List<String> getNames() {
//        List<String> nameList = Arrays.asList("Igor", "Maria", "Parvin", "Dan", "Murodil", "Alex", "John");
//        return nameList;
//
//    }

    @ParameterizedTest
    @MethodSource("getNames")
    public void testPrintNames(String name) {
        System.out.println("name = " + name);
    }


    public static List<String> getNames(){
        //you can get value from anywhere almost anytype and return to your test
        //DB
        //Excel
        //other APIs

        List<String> nameList = Arrays.asList("Parvin","Nasim","mohamad","Nadir","Saim","Gurhan","Murodil");
        return  nameList;
    }

    public static List<Map<String,String>> getExcelData(){
        //get your file object
        ExcelUtil vytrackFile = new ExcelUtil("Vytracktestdata.xlsx","QA3-all");
        //return sheet as a alist of map
        return vytrackFile.getDataList();

    }

    @ParameterizedTest
    @MethodSource("getExcelData")
    public void excelParamTest(Map<String,String> userInfo){

        System.out.print("FirstName: "+userInfo.get("firstname") + ",\t\t\t");
        System.out.println("LastName: "+userInfo.get("lastname"));

    }

}
