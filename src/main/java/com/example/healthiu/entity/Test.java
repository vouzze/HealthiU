package com.example.healthiu.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "test")
@NoArgsConstructor
public class Test {
    @Id
    private String userLogin;

    @Column
    private String gender;

    @Column
    private int age;

    @Column
    private double height;

    @Column
    private double weight;

    @Column
    private double waistSize;

    @Column
    private double chestSize;

    @Column
    private double hipSize;

    @Column
    private String bloodType;

    @Column
    private String testResult;

    @Column
    private double bmi;

    public Test(String userLogin, String gender, int age, double height,
                double weight, double waistSize, double chestSize,
                double hipSize, String bloodType) {
        this.userLogin = userLogin;
        this.gender = gender;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.waistSize = waistSize;
        this.chestSize = chestSize;
        this.hipSize = hipSize;
        this.bloodType = bloodType;
    }

    public Test(String userLogin, String gender, int age, double height,
                double weight, double waistSize, double chestSize,
                double hipSize, String bloodType, String testResult,
                double bmi) {
        this.userLogin = userLogin;
        this.gender = gender;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.waistSize = waistSize;
        this.chestSize = chestSize;
        this.hipSize = hipSize;
        this.bloodType = bloodType;
        this.testResult = testResult;
        this.bmi = bmi;
    }
}
