package com.nhnacademy.car;


import java.time.LocalDateTime;

public class Car {

    private LocalDateTime now;
    private String carNumber;
    private String carArea;

    public Car(String carNumber) {
        this.carNumber = carNumber;
    }

    public Car(String carNumber, LocalDateTime now) {
        this.carNumber = carNumber;
        this.now = now;
    }


    public String getCarNumber() {
        return carNumber;
    }

    public String getCarArea() {
        return carArea;
    }

    public void setCarArea(String carArea) {
        this.carArea = carArea;
    }
}
