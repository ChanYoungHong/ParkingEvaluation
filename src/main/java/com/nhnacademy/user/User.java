package com.nhnacademy.user;

import com.nhnacademy.car.Car;

public class User {
    private Car car;
    private long money = 30000;

    public User(Car car, long money) {
        this.car = car;
        this.money = money;
    }

    public long getMoney() {
        return this.money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public Car getCar() {
        return car;
    }
}
