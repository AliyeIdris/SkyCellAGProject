package com.skycellag.utilities;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.skycellag.payloads.sensorpayload.EndDeviceIds;
import com.skycellag.payloads.sensorpayload.MainPayload;
import org.json.JSONObject;

import java.io.*;
import java.util.Properties;

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
        String s=File.separator;
        String filePath=System.getProperty("user.dir")+s+"src"+s+"test"+s+"resources"+s+"TestData"+s+"sensorData.json";
        try {
            JsonObject myObject=(JsonObject) new JsonParser().parse(new FileReader(filePath));
            String loggerNumber=TestDataHolder.loggerNumber();
            EndDeviceIds endDeviceIds=new EndDeviceIds("eui_"+loggerNumber,loggerNumber);

            JsonObject ob=new JsonObject();
            ob.add("end_device_ids",new Gson().toJsonTree(endDeviceIds));
            ob.add("received_at",myObject.get("received_at"));
            ob.add("uplink_message",myObject.get("uplink_message"));
            System.out.println(ob);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
