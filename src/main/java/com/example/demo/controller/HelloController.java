package com.example.demo.controller;


import com.example.demo.CheckSumGeneration;
import com.oracle.javafx.jmx.json.JSONException;
import com.paytm.pg.merchant.CheckSumServiceHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@RestController
public class HelloController {

    @RequestMapping(value = "/api")
//    public HashMap<String,String> hello(final HttpServletRequest request){
    public TreeMap<String,String> hello(
            @RequestParam(value = "cid") String cid,
            @RequestParam(value = "amount") String amount,
            @RequestParam(value = "orderid") String orderid
    ){
        String custid = cid;
        String txnamount = amount;
        String email = "gaurav.rawat@onionmobi.com";
        String mobileno = "8929254381";

        TreeMap<String,String> treeMap = CheckSumGeneration.generate(orderid, custid, txnamount, email, mobileno);

        System.out.println("---rayn treemap"+treeMap);
        return treeMap;
    }

    @RequestMapping(value = "/callback")
    public String hello(
            HttpServletRequest request
    ){
        
        Map<String, String[]> parameterMap = request.getParameterMap();

        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            String key = entry.getKey();
            String[] value = entry.getValue();
            for (int i = 0; i < value.length; i++) {
                System.out.println("Callback param--- "+key + " = " + value[i]);
                if ("CHECKSUMHASH".equals(key)){

                }
            }

        }

        return "callback page";
    }


    @RequestMapping(value = "/native")
    public String hello(
            @RequestParam(value = "body") String body
    ){

        System.out.println("---Rayn body:"+body);

        try {
            String checksum =CheckSumServiceHelper.getCheckSumServiceHelper().genrateCheckSum("6IN4N_7TbCjZawj1",body);
            return checksum;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @RequestMapping(value = "/transactionStatus")
    public String hello(
            @RequestParam(value = "mid") String mid,
            @RequestParam(value = "orderId") String orderId
    ){

        System.out.println("---Rayn transactionStatus:"+mid+"---"+orderId);

        TreeMap treeMap = new TreeMap();
        treeMap.put("MID",mid);
        treeMap.put("ORDERID",orderId);

        try {
            String checksum =CheckSumServiceHelper.getCheckSumServiceHelper().genrateCheckSum("6IN4N_7TbCjZawj1",treeMap);
            return checksum;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
