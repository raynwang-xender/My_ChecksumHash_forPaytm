package com.example.demo;

import com.paytm.pg.merchant.CheckSumServiceHelper;

import java.util.TreeMap;

public class CheckSumGeneration {

    private static boolean isProduct = true;

    //Below parameters provided by Paytm for test
    public static String MID = "ONIONM51894990378872";
    public static String MercahntKey = "UAYaaBVkWm17jl4N";//UAYaaBVkWm17jl4N
    public static String INDUSTRY_TYPE_ID = "Retail";
    public static String CHANNLE_ID = "WAP";
    public static String WEBSITE = "APP_STAGING";
//    public static String REVERIFY_URL = "https://pguat.paytm.com/oltp/HANDLER_INTERNAL/getTxnStatus?";
    public static String CALLBACK_URL = "http://192.168.10.91:9999/callback";
//    public static String PAYMENT_MODE_ONLY = "YES";
//    public static String PAYMENT_TYPE_ID = "DC";
//    public static String AUTH_MODE = "3D";

    //Below parameters provide by Paytm for Product
    static {
        if (isProduct) {
            MID = "xender87809192798508";  //ONIONm00999354646325 xender87809192798508
            MercahntKey = "A3VG@Ao7CeW0l01t";//67g_Dq9bfRyTt4i A3VG@Ao7CeW0l01t
            INDUSTRY_TYPE_ID = "Retail92";
            CHANNLE_ID = "WAP";//WAP WEB
            WEBSITE = "APPPROD";  //ONIONmWAP ONIONmWEB backup:APPPROD WEBPROD xender paytm retail
//            REVERIFY_URL = "https://secure.paytm.in/oltp/HANDLER_INTERNAL/getTxnStatus?";
//            PAYMENT_MODE_ONLY = "YES";
//            PAYMENT_TYPE_ID = "DC";

        }
    }


    public static TreeMap<String, String> generate(String orderid, String custid, String txnamount, String email, String mobileno){

        TreeMap<String,String> paramMap = new TreeMap<>();
        paramMap.put("MID" , MID);
        paramMap.put("ORDER_ID" , orderid);
        paramMap.put("CUST_ID" , custid);
        paramMap.put("INDUSTRY_TYPE_ID" , INDUSTRY_TYPE_ID);
        paramMap.put("CHANNEL_ID" , CHANNLE_ID);//WAP WEB
        paramMap.put("TXN_AMOUNT" , txnamount);
        paramMap.put("WEBSITE" , WEBSITE);
        paramMap.put("MOBILE_NO" , mobileno);
        paramMap.put("CALLBACK_URL" , CALLBACK_URL);
        paramMap.put( "EMAIL" , email);


//        paramMap.put("THEME","MERCHANT4");
//        paramMap.put("PAYMENT_MODE_ONLY",PAYMENT_MODE_ONLY);
//        paramMap.put("PAYMENT_TYPE_ID",PAYMENT_TYPE_ID);
//        paramMap.put("AUTH_MODE",AUTH_MODE);
        try{
            String checkSum =  CheckSumServiceHelper.getCheckSumServiceHelper().genrateCheckSum(MercahntKey, paramMap);
            paramMap.put("CHECKSUMHASH" , checkSum);
            return paramMap;

        }catch(Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}