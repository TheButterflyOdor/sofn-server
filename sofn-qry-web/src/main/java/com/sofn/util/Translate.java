package com.sofn.util;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sofn.core.util.StringUtils;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.io.*;



/**
 * @author sofn
 * @version 2016年10月18日 下午 5:31
 */

public class  Translate {
    public static org.slf4j.Logger logger = LoggerFactory.getLogger("Translate");
    static Map<String,String>  districtMap = null;
    static Map<String,String>  Probicemap = null;
    static Map<String,String>  cityMap = null;
    private final static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    /**
     * 区域名称翻译
     * @param arae
     * @return
     */
    public static String getArae(String arae)  {
        //判断
        if(StringUtils.isBlank(arae)){
            return "";
        }
        //防止只存省或市id导致报错
        if(arae.length()==2){
            arae = arae+"0000";
        }else if (arae.length()==4){
            arae = arae+"00";
        }
        String probinceID = arae.substring(0,2);
        String cityID = arae.substring(0,4);
        String districtID = arae.substring(0,6);
        String prName = getProbice(probinceID);
        String ciName = getCity(cityID);
        String diName = getDistrict(districtID);
        ciName = ciName==null?"":ciName;
        diName = diName==null?"":diName;
        return  prName+ciName+diName ;
    }
       protected static String getProbice(String probiceID) {

            //Map<String,String>  map = null ;
            String probice;
            String file = "ProJson";
                if(Probicemap != null){
                     probice = Probicemap.get(probiceID);
                }else {
                    File fileName = getFilePath(file);
                    Probicemap = Translate.readFile(fileName, "province");
                     probice = Probicemap.get(probiceID);
                }
                return probice;
        }
        protected static  String getCity(String cityID){

            String city;
            String file ="CityJson";
                 if (cityMap != null){
                     city = cityMap.get(cityID);
                }else {
                    File fileName = getFilePath(file);
                     cityMap = Translate.readFile(fileName, "city");
                    city = cityMap.get(cityID);
                }
            return city;
        }
        protected static String getDistrict(String districtID)  {

             String district;
             String file ="DistrictJson";
                if (districtMap != null){
                    district = districtMap.get(districtID);
                }else {
                    File fileName = getFilePath(file);
                    districtMap = Translate.readFile(fileName, "District");
                    district = districtMap.get(districtID);
                }
                return district;
        }

    /**
     * 读取文件内容
     * @param fileName
     * @param name
     * @return
     */
    protected static Map readFile(File fileName,String name){
        Map<String, String> map = new HashMap<String, String>();
        BufferedReader br;
        InputStreamReader inputStreamReader;
        try {
            try {
             inputStreamReader = new InputStreamReader( new FileInputStream(fileName),"UTF-8");//指定文件读取格式
            br = new BufferedReader(inputStreamReader);
        String line = null, ws = null;
        StringBuffer jsonBuffer = new StringBuffer();
        JSONObject jsonObject;
        JSONArray jsonArray;
        while ((line = br.readLine()) != null) {//读取文件内容
            jsonBuffer.append(line);
        }
        br.close();
                //将文件内容转换成json数据
        if(name.equals("province")){
            jsonArray = (JSONArray) JSONArray.parse(jsonBuffer.toString().substring(11, jsonBuffer.length()));
            for (int i = 0; i < jsonArray.size(); i++) {
                jsonObject = (JSONObject) jsonArray.get(i);
                jsonObject.getString("province");
                jsonObject.getString("name");
                jsonObject.getString("type");
                map.put(jsonObject.getString("province"), jsonObject.getString("name"));
            }
        }else if (name.equals("city")){
            jsonArray = (JSONArray) JSONArray.parse(jsonBuffer.toString().substring(8, jsonBuffer.length()));
            for (int i = 0; i < jsonArray.size(); i++) {
                jsonObject = (JSONObject) jsonArray.get(i);
                jsonObject.getString("province");
                jsonObject.getString("city");
                jsonObject.getString("name");
                jsonObject.getString("type");
                map.put(jsonObject.getString("province")+jsonObject.getString("city"), jsonObject.getString("name"));
            }
        }else {
            jsonArray = (JSONArray) JSONArray.parse(jsonBuffer.toString().substring(10, jsonBuffer.length()));
            for (int i = 0; i < jsonArray.size(); i++) {
                jsonObject = (JSONObject) jsonArray.get(i);
                jsonObject.getString("province");
                jsonObject.getString("city");
                jsonObject.getString("country");
                jsonObject.getString("name");
                jsonObject.getString("type");
                map.put(jsonObject.getString("province")+jsonObject.getString("city")+jsonObject.getString("country"),jsonObject.getString("name"));
            }

        }
            }catch (UncheckedIOException e){
            logger.info("=========文件IOIOException异常请检查",e);
            }
        }catch (IOException e){
            logger.info("=========IOException",e);
        }

        return map;
    }

    /**
     * 巡查类型字典翻译
     * @param inspectionType
     * @return
     */
    public static String getInspectionType(String inspectionType){
        String inspectionTypeName;
        if (inspectionType .equals("1")) {
            inspectionTypeName = "基地";
        } else if (inspectionType .equals("2")) {
            inspectionTypeName = "主体";
        } else if (inspectionType.equals("3")) {
            inspectionTypeName = "农资";
        } else if (inspectionType.equals("0")) {
            inspectionTypeName = "其他";
        }else{
            inspectionTypeName = "";
        }
        return inspectionTypeName;
    }
    public static String getInspectionResult(String inspectionResult){
        String inspectionResultName;
        if (inspectionResult.equals("1")) {
            inspectionResultName = "合格";
        } else if (inspectionResult.equals("2")) {
            inspectionResultName = "不合格";
        } else if (inspectionResult.equals("3")) {
            inspectionResultName = "整改";
        }else {
            inspectionResultName = "";
        }
            return inspectionResultName;


    }

    /**
     * 取得文件路径
     * @param file
     * @return
     */
    protected static  File getFilePath(String file){
        String filePath = Translate.class.getClassLoader().getResource(file+".js").getPath();
        File fileName = new File(filePath);
        return fileName;
    }

    /**
     * 时间格式
     * @param date
     * @return
     */
    public static String getFormat(Date date){
      String dateFormat =  simpleDateFormat.format(date).toString();
        return dateFormat;
    }
}


