package com.kxt.yanglao.watch.controller;
import com.fasterxml.jackson.databind.deser.DataFormatReaders;
import com.kxt.yanglao.watch.jpa.entity.Tonometer;
import com.kxt.yanglao.watch.jpa.reposity.TonometerRepository;
import com.kxt.yanglao.watch.jpa.reposity.WatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@RestController
public class TonometerController {

    @Autowired
    private TonometerRepository tonometerRepository ;


    @RequestMapping(value = "/xueya")
    private  String SaveXueYa(@RequestParam("imei")String strImei,@RequestParam("time_begin")String strBeginTime,
                                 @RequestParam("dbp")String strDbp,@RequestParam("dbp_l")String strDbp_l,
                                 @RequestParam("sbp")String strSbp,@RequestParam("sbp_h")String strSbp_h,
                                 @RequestParam("user_number")String strUser_Number,@RequestParam("pul")String strPul){

        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


        log.info("{} xueya：\r\n IMEI:{} \r\n BeginTime:{} \r\n DBP:{} \r\n DBP_L:{} \r\n SBP:{} \r\n SBP_H:{} \r\n User_Number;{} \r\n Pul:{} \r\n",date.format(formatter),strImei,strBeginTime,strDbp,strDbp_l,strSbp,strSbp_h,strUser_Number,strPul);

        Tonometer tonometer = new Tonometer();
        tonometer.setImei(strImei);
        tonometer.setTime_begin(strBeginTime);
        tonometer.setDbp(Integer.valueOf(strDbp));
        tonometer.setDbp_l(Integer.valueOf(strDbp_l));
        tonometer.setSbp(Integer.valueOf(strSbp));
        tonometer.setSbp_h(Integer.valueOf(strSbp_h));
        tonometer.setUser_number(strUser_Number);
        tonometer.setPul(Integer.valueOf(strPul));
        tonometerRepository.save(tonometer);
        return "0";
    }
}
