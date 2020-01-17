package com.kxt.yanglao.watch.jpa.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.sql.Timestamp;

@Entity
@Table(name = "sec_watch_log")
@Data
public class Watch {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String imei;
    private String data_Type;
    private String begin_Time;
    private String end_Time;
    private String city;
    private String address;
    private Double lon;
    private Double lat;
    private Integer heart_Rate;
    private String location_Type;
    private Integer steps;
    private String sleep_Data;
    private Integer remaining_Power;
    private String switch_Type;
    private String memo;
}
