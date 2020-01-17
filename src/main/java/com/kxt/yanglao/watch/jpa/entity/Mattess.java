package com.kxt.yanglao.watch.jpa.entity;

import java.util.Date;
import java.util.List;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "sec_mattess")
@Data

public class Mattess {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String deviceID;
    private String date;
    @Transient
    private List<MattessItems> items;
    @Transient
    private MattessInfo info;
    @Transient
    private MattessStatus status;
    private int hr;
    private int br;
    private int movment;
    private int snore;
    private String fallinsleep;
    private int lightsleep;
    private int deepsleep;
    private int remsleep;
    private int dursleep;
    private int durwake;
    private String onbedtime;
    private String offbedtime;
    private String sleeptime;
    private String waketime;
    private int score;
    @Transient
    private List<String> abnormal;
    private String guid;
    private String addTime;

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setItems(List<MattessItems> items) {
        this.items = items;
    }

    public List<MattessItems> getItems() {
        return items;
    }

    public void setInfo(MattessInfo info) {
        this.info = info;
    }

    public MattessInfo getInfo() {
        return info;
    }

    public void setStatus(MattessStatus status) {
        this.status = status;
    }

    public MattessStatus getStatus() {
        return status;
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

    public void setMovment(int movment) {
        this.movment = movment;
    }

    public int getMovment() {
        return movment;
    }

    public void setSnore(int snore) {
        this.snore = snore;
    }

    public int getSnore() {
        return snore;
    }

    public void setFallinsleep(String fallinsleep) {
        this.fallinsleep = fallinsleep;
    }

    public String getFallinsleep() {
        return fallinsleep;
    }

    public void setLightsleep(int lightsleep) {
        this.lightsleep = lightsleep;
    }

    public int getLightsleep() {
        return lightsleep;
    }

    public void setDeepsleep(int deepsleep) {
        this.deepsleep = deepsleep;
    }

    public int getDeepsleep() {
        return deepsleep;
    }

    public void setRemsleep(int remsleep) {
        this.remsleep = remsleep;
    }

    public int getRemsleep() {
        return remsleep;
    }

    public void setDursleep(int dursleep) {
        this.dursleep = dursleep;
    }

    public int getDursleep() {
        return dursleep;
    }

    public void setDurwake(int durwake) {
        this.durwake = durwake;
    }

    public int getDurwake() {
        return durwake;
    }

    public void setOnbedtime(String onbedtime) {
        this.onbedtime = onbedtime;
    }

    public String getOnbedtime() {
        return onbedtime;
    }

    public void setOffbedtime(String offbedtime) {
        this.offbedtime = offbedtime;
    }

    public String getOffbedtime() {
        return offbedtime;
    }

    public void setSleeptime(String sleeptime) {
        this.sleeptime = sleeptime;
    }

    public String getSleeptime() {
        return sleeptime;
    }

    public void setWaketime(String waketime) {
        this.waketime = waketime;
    }

    public String getWaketime() {
        return waketime;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void setAbnormal(List<String> abnormal) {
        this.abnormal = abnormal;
    }

    public List<String> getAbnormal() {
        return abnormal;
    }
}

