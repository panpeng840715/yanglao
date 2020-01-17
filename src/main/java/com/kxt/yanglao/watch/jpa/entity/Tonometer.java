package com.kxt.yanglao.watch.jpa.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.sql.Timestamp;

@Entity
@Table(name = "sec_tonometer_log")
@Data
public class Tonometer {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private String imei;
    private String time_begin;
    private Integer dbp;
    private Integer dbp_l;
    private Integer sbp;
    private Integer sbp_h;
    private String user_number;
    private Integer pul;
    private String memo;
}
