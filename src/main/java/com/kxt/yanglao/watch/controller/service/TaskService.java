package com.kxt.yanglao.watch.controller.service;

import com.kxt.yanglao.watch.jpa.entity.Mattess;
import com.kxt.yanglao.watch.jpa.entity.MattessInfo;
import com.kxt.yanglao.watch.jpa.entity.MattessItems;
import com.kxt.yanglao.watch.jpa.reposity.MattessInfoRepository;
import com.kxt.yanglao.watch.jpa.reposity.MattessItemsRepository;
import com.kxt.yanglao.watch.jpa.reposity.MattessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;
import java.util.UUID;

@Service
public class TaskService {

    @Autowired
    private MattessRepository mattessRepository;
    @Autowired
    private MattessInfoRepository mattessInfoRepository;
    @Autowired
    private MattessItemsRepository mattessItemsRepository;

    @Async("taskExecutor")
    public void saveinfor(Mattess matess) throws Exception {
        if (matess != null) {
            UUID uuid = UUID.randomUUID();
            Date dd=new Date();
            SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time=sim.format(dd);
            matess.setGuid(uuid.toString());
            matess.setAddTime(time);
            mattessRepository.save(matess);
            if (matess.getInfo() != null) {
                MattessInfo info = matess.getInfo();
                info.setDeviceID(matess.getDeviceID());
                info.setGuid(uuid.toString());
                info.setAddTime(time);
                String utcTime = info.getUploadTime();
                info.setUploadTime(DateConvert(utcTime));
                mattessInfoRepository.save(info);
            }
            if (matess.getItems() != null) {
                for (int i=0; i < matess.getItems().size(); i++) {
                    MattessItems items = matess.getItems().get(i);
                    items.setDeviceID(matess.getDeviceID());
                    items.setGuid(uuid.toString());
                    items.setAddTime(time);
                    mattessItemsRepository.save(items);
                }
            }
        }
    }

    public static String DateConvert(String dateTime) throws ParseException {
        String dateStr = dateTime;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        format.setTimeZone(TimeZone.getTimeZone("GMT+0"));
        Date date = format.parse(dateStr);
        format.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        return dateStr = format.format(date);
    }

}
