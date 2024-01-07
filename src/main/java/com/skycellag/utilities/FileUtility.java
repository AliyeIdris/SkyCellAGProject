package com.skycellag.utilities;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.skycellag.payloads.LoggerPojo;
import com.skycellag.payloads.Loggers;
import com.skycellag.payloads.sensorpayload.EndDeviceIds;
import com.skycellag.payloads.sensorpayload.MainPayload;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.*;

/**
 * @author : user
 * @created : 2.01.2024,16:25
 * @Email :aliyeidiris@gmail.com
 **/
public class FileUtility {
    public static String readConfig(String key){
        final String configPath="config.properties";
        Properties properties =new Properties();
        try {
            properties.load(new FileInputStream(configPath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties.getProperty(key);
    }
    public static MainPayload readSensorPayload(){
        String s=File.separator;
        String filePath=System.getProperty("user.dir")+s+"src"+s+"test"+s+"resources"+s+"TestData"+s+"sensorData.json";
        ObjectMapper mapper=new ObjectMapper();
        MainPayload mainPayload=null;


        try {
            EndDeviceIds endDeviceIds=mapper.readValue(filePath, EndDeviceIds.class);
            mainPayload=mapper.readValue(filePath, MainPayload.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return mainPayload;
    }

    public static void main(String[] args) {
        //System.out.println(readSensorPayload().getReceivedAt());
       /* String s=File.separator;
        String filePath=System.getProperty("user.dir")+s+"src"+s+"test"+s+"resources"+s+"TestData"+s+"temperature.json";
        EndDeviceIds endDeviceIds;
        String loggerNumber;
        try {
            JsonObject myObject=(JsonObject) new JsonParser().parse(new FileReader(filePath));
            loggerNumber=TestDataHolder.randomLoggerNumber();
             endDeviceIds=new EndDeviceIds("eui_"+loggerNumber,loggerNumber);

            JsonObject ob=new JsonObject();
            ob.add("end_device_ids",new Gson().toJsonTree(endDeviceIds));
            ob.add("received_at",myObject.get("received_at"));
            ob.add("uplink_message",myObject.get("uplink_message"));
            //System.out.println(ob);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        JSONParser parser=new JSONParser();
        try {
            Object obj=parser.parse(new FileReader(filePath));
            JSONObject jsonObject=(JSONObject)obj;
            jsonObject.put("end_device_ids",new Gson().toJsonTree(endDeviceIds));
            //System.out.println(jsonObject);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }


        try {
            Object obj = parser.parse(new FileReader(filePath));
            JSONObject jsonObject=(JSONObject)obj;
            Loggers loggers=new Loggers(loggerNumber,"MR_810T");
            //logger.add(loggers);
            jsonObject.put("loggers",new Gson().toJsonTree(new ArrayList<>(Arrays.asList(loggers))));
            System.out.println(jsonObject);

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }*/
        System.out.println(TestDataHolder.randomLoggerNumber());

    }
}
