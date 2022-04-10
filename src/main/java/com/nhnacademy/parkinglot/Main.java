package com.nhnacademy.parkinglot;

import com.nhnacademy.car.Car;
import com.nhnacademy.parkingsystem.ParkingSystem;
import com.nhnacademy.user.User;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        ParkingSystem system = new ParkingSystem();
        Set<Thread> inAndOutSet = new HashSet<>();

        for (int i = 0; i < 8; i++) {
            String carNumber = "12러 " + (5550 + i);
            Car car = new Car(carNumber, LocalDateTime.now());
            User user = new User(car, 50_000);
            inAndOutSet.add(new Thread(new InAndOut(user, system)));
        }

        inAndOutSet.forEach(thread -> {
            try {
                thread.start();
                thread.join();
            } catch (InterruptedException e) {
                thread.interrupt();
            }
        });
    }
}
