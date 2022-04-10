package com.nhnacademy.parkinglot;

import com.nhnacademy.parkingsystem.ParkingSystem;
import com.nhnacademy.user.User;

public class InAndOut implements Runnable {
    User user;
    ParkingSystem system;

    public InAndOut(User user, ParkingSystem system) {
        this.user = user;
        this.system = system;
    }

    @Override
    public void run() {
        system.GoIn(user.getCar());
        System.out.println(user.getMoney());
        System.out.println(user.getCar().getCarNumber());
        System.out.println(user.getCar().getCarArea());
        system.GoOut(user);
        System.out.println(user.getMoney());
    }
}
