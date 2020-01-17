package com.kxt.yanglao.watch.jpa.entity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.sql.Timestamp;

@Entity
@Table(name = "sec_medical_exam")
@Data

public class Medical {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private String cstm_name;
    private String id_card;
    private String high_pressure;
    private String low_pressure;
    private String pulse;
    private String temperature;
    private String oxygen;
    private String bpm;
    private String blood_sugar;
    private String sugar_type;
    private String ua;
    private String chol;
    private String hr;
    private String ecg_img;
    private double height;
    private double weight;
    private double BMI;
    private String measure_time;
}
