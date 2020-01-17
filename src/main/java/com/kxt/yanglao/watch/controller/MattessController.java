package com.kxt.yanglao.watch.controller;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.deser.DataFormatReaders;
import com.kxt.yanglao.watch.controller.service.TaskService;
import com.kxt.yanglao.watch.jpa.entity.Mattess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@RestController
public class MattessController {

    @Autowired
    private TaskService taskService;

    @RequestMapping(value = "/mattess", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String SaveMattess(@RequestBody String json) throws Exception {
        String e = java.net.URLDecoder.decode(json, "UTF-8");
        String strReturn = "";
        Map<String,String> map=new HashMap<String,String>();
        if (e != null) {
            Mattess matess = JSONObject.parseObject(e, Mattess.class);
            if (matess != null) {
                taskService.saveinfor(matess);
                //返回接收成功
                map.put("result","success");
                strReturn = JSONObject.toJSONString(map);
                return strReturn;
            }
        }
        //返回接收失败
        map.put("result","fai");
        strReturn = JSONObject.toJSONString(map);
        return strReturn;
    }
}
