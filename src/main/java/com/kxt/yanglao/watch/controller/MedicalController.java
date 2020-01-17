package com.kxt.yanglao.watch.controller;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.deser.DataFormatReaders;
import com.kxt.yanglao.watch.jpa.entity.Medical;
import com.kxt.yanglao.watch.jpa.reposity.MedicalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class MedicalController {

    @Autowired
    private MedicalRepository medicalRepository;

    @RequestMapping(value = "/medical",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String SaveMedical(@RequestBody String json) throws UnsupportedEncodingException {
        String e = java.net.URLDecoder.decode(json, "UTF-8");
        String strReturn = "";
        if(e != null) {
            JSONObject jsonObj=JSONObject.parseObject(e);
            String strCstmName = jsonObj.getString("cstmName");
            String strIdCard = jsonObj.getString("idCard");
            String strHighPressure = jsonObj.getString("highPressure");
            String strLowPressure = jsonObj.getString("lowPressure");
            String strPulse = jsonObj.getString("pulse");
            String strTemperature = jsonObj.getString("temperature");
            String strOxygen = jsonObj.getString("oxygen");
            String strBpm = jsonObj.getString("bpm");
            String strBloodSugar = jsonObj.getString("bloodSugar");
            String strSugarType = jsonObj.getString("sugarType");
            String strUa = jsonObj.getString("ua");
            String strChol = jsonObj.getString("chol");
            String strHr = jsonObj.getString("hr");
            String strEcgImg = jsonObj.getString("ecgImg");
            String strHeight = jsonObj.getString("height");
            String strWeight = jsonObj.getString("weight");
            String strBMI = jsonObj.getString("bmi");
            String strMeasureTime = jsonObj.getString("measureTime");

            LocalDateTime date = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            log.info("{} medical：\r\n cstmName:{} \r\n idCard:{} \r\n highPressure:{} \r\n lowPressure:{} \r\n pulse:{} \r\n temperature:{} \r\n " +
                            "oxygen:{} \r\n bpm:{} \r\n bloodSugar:{} \r\n sugarType:{} \r\n  ua:{} \r\n chol:{} \r\n hr:{} \r\n " +
                            "ecgImg:{} \r\n height:{} \r\n weight:{} \r\n BMI:{} \r\n measureTime:{} \r\n",date.format(formatter),strCstmName,strIdCard,strHighPressure,strLowPressure,
                    strPulse,strTemperature,strOxygen,strBpm,strBloodSugar,strSugarType,strUa,strChol,strHr,strEcgImg,strHeight,strWeight,strBMI,strMeasureTime);

            Medical medical = new Medical();
            medical.setCstm_name(strCstmName);
            medical.setId_card(strIdCard);
            medical.setHigh_pressure(strHighPressure);
            medical.setLow_pressure(strLowPressure);
            medical.setPulse(strPulse);
            medical.setTemperature(strTemperature);
            medical.setOxygen(strOxygen);
            medical.setBpm(strBpm);
            medical.setBlood_sugar(strBloodSugar);
            medical.setSugar_type(strSugarType);
            medical.setUa(strUa);
            medical.setChol(strChol);
            medical.setHr(strHr);
            medical.setEcg_img(strEcgImg);
            if(strHeight == null){
                medical.setHeight(0);
            }
            else {
                medical.setHeight(Double.valueOf(strHeight));
            }

            if(strWeight == null){
                medical.setWeight(0);
            }
            else {
                medical.setWeight(Double.valueOf(strWeight));
            }

            if(strBMI == null){
                medical.setBMI(0);
            }
            else {
                medical.setBMI(Double.valueOf(strBMI));
            }
            medical.setMeasure_time(strMeasureTime);
            medicalRepository.save(medical);

            Map<String,String> map=new HashMap<String,String>();
            map.put("success","true");
            map.put("msg","数据接收成功");
            strReturn = JSONObject.toJSONString(map);
        }
        return strReturn;
    }
}
