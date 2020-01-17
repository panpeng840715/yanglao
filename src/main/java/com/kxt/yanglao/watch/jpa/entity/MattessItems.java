package com.kxt.yanglao.watch.jpa.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "sec_mattess_items")
@Data
public class MattessItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String deviceID;
    private String guid;
    private String time;
    private int cmov;
    private int hr;
    private int br;
    private int snore;
    private int sta;
    private String addTime;

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setCmov(int cmov) {
        this.cmov = cmov;
    }

    public int getCmov() {
        return cmov;
    }

    public void setHr(int hr) {
        this.hr = hr;
    }

    public int getHr() {
        return hr;
    }

    public void setBr(int br) {
        this.br = br;
    }

    public int getBr() {
        return br;
    }

    public void setSnore(int snore) {
        this.snore = snore;
    }

    public int getSnore() {
        return snore;
    }

    public void setSta(int sta) {
        this.sta = sta;
    }

    public int getSta() {
        return sta;
    }
}
