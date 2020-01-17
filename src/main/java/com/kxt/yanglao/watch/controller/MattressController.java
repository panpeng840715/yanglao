package com.kxt.yanglao.watch.controller;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.deser.DataFormatReaders;
import com.kxt.yanglao.watch.jpa.entity.Tonometer;
import com.kxt.yanglao.watch.jpa.reposity.TonometerRepository;
import com.kxt.yanglao.watch.jpa.reposity.WatchRepository;
import com.kxt.yanglao.watch.jpa.util.HttpClientService;
import com.kxt.yanglao.watch.jpa.util.VeDate;
import org.apache.http.NameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.Null;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class MattressController {

    @Value("${mattress.url}")
    private String strUrl;

    @Value("${mattress.token}")
    private String strToken;

    @RequestMapping(value = "/sensorinfo")
    private Object GetSensorInfo(@RequestParam("uuid") String strUUID) {

        String url = strUrl + "/v1/app/SensorInfo";
        Object[] params = new Object[]{"UUID"};
        Object[] values = new Object[]{strUUID};
        List<NameValuePair> paramsList = HttpClientService.getParams(params, values);
        try {
            Object result = HttpClientService.sendGet(url, paramsList,strToken);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/querygroupdata")
    private Object QueryGroupData(@RequestParam("uuids") String strUUIDs){

        String url = strUrl + "/v2/data/QueryGroupData";
        Map<String,String> map=new HashMap<String,String>();
        map.put("UUIDs",strUUIDs);
        String sendString = "";
        sendString = JSONObject.toJSONString(map);
        try {
            Object result = HttpClientService.sendPost(url, sendString,strToken);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/updatethreshold")
    private Object SetThreshold(@RequestParam("uuid") String strUUID,@RequestParam("minHeartRange") String strMinHeartRange,
                                @RequestParam("maxHeartRange") String strMaxHeartRange,
                                @RequestParam("minRespirationRange") String strMinRespirationRange,
                                @RequestParam("maxRespirationRange") String strMaxRespirationRange,
                                @RequestParam("alarmDelay") String strAlarmDelay){

        String url = strUrl + "/v2/device/UpdateThreshold";

        Map<String,String> map=new HashMap<String,String>();
        map.put("UUID",strUUID);
        map.put("minHeartRange",strMinHeartRange);
        map.put("maxHeartRange",strMaxHeartRange);
        map.put("minRespirationRange",strMinRespirationRange);
        map.put("maxRespirationRange",strMaxRespirationRange);
        map.put("alarmDelay",strAlarmDelay);
        String sendString = "";
        sendString = JSONObject.toJSONString(map);
        try {
            Object result = HttpClientService.sendPost(url, sendString,strToken);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/sessionlist")
    private  Object GetSessionList(@RequestParam("uuids") String strUUIDs,
                                   @RequestParam("FromDate")String strFromDate,
                                   @RequestParam("ToDate")String strToDate){

        String strStart = VeDate.strToDateLong(strFromDate);
        String strEnd = VeDate.strToDateLong(strToDate);
        String url = strUrl + "/v1/app/SessionList";
        Map<String,String> map = new HashMap<>();
        map.put("UUIDs",strUUIDs);
        map.put("FromDate",strStart.toString());
        map.put("ToDate",strEnd.toString());

        String sendString = "";
        sendString = JSONObject.toJSONString(map);
        try {
            Object result = HttpClientService.sendPost(url,sendString,strToken);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @RequestMapping(value = "/sessiondata")
    private Object GetSessionData(@RequestParam("uuid") String strUUID,
                                  @RequestParam("SessionId") String strSessionID,
                                  @RequestParam(value ="DataProperty",required = false,defaultValue = "") String strDataProperty){

        String url = strUrl + "/v1/app/SessionData";

        Object[] params = new Object[]{"UUID","SessionId","DataProperty"};
        Object[] values = new Object[]{strUUID,strSessionID,strDataProperty};
        List<NameValuePair> paramsList = HttpClientService.getParams(params, values);

        try {
            Object result = HttpClientService.sendGet(url,paramsList,strToken);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/newsessiondata")
    private Object GetNewSessionData(@RequestParam("uuid")String strUUID){

        String url = strUrl + "/v3/data/GetNewSessionData";
        Object[] params = new Object[]{"UUID",};
        Object[] values = new Object[]{strUUID};
        List<NameValuePair> paramsList = HttpClientService.getParams(params, values);
        try {
            Object result = HttpClientService.sendGet(url,paramsList,strToken);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "sleepquality")
    private  Object GetSleepQuality(@RequestParam("uuid") String strUUID,
                                    @RequestParam("FromDate") String strFromDate,
                                    @RequestParam(value="FromTime",required = false,defaultValue = "") String strFromTime,
                                    @RequestParam("EndDate") String strEndDate,
                                    @RequestParam(value = "EndTime",required = false,defaultValue = "") String strEndTime){

        String url = strUrl + "/v1/app/SleepQuality";
        Object[] params = new Object[]{"UUID","FromDate","FromTime","EndDate","EndTime"};
        Object[] values = new Object[]{strUUID,strFromDate,strFromTime,strEndDate,strEndTime};
        List<NameValuePair> paramsList = HttpClientService.getParams(params, values);
        try {
            Object result = HttpClientService.sendGet(url,paramsList,strToken);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
