
package com.nhnacademy.parkingsystem;

import com.nhnacademy.car.Car;
import com.nhnacademy.entrance.Entrance;
import com.nhnacademy.exit.Exit;
import com.nhnacademy.parkinglot.Parkinglot;
import com.nhnacademy.user.User;

public class ParkingSystem {
    Parkinglot parkinglot = new Parkinglot();
    Exit exit = new Exit();
    Entrance entrance = new Entrance();

    public void parkingCar(Car car, String area) {
        parkinglot.parking(car, area);
    }

    public int alertParkingLotSize() {
        return parkinglot.alertSpaceSize();
    }

    public String findCarArea(String carNumber) {
        return parkinglot.findArea(carNumber);
    }

    public void outParkingLot(Car car, String area) {
        parkinglot.outArea(car, area);
    }

    public int alertleavingLotSize() {
        return parkinglot.alertSpaceSize();
    }


    public synchronized void GoIn(Car car) {
        String area = entrance.scan(car.getCarNumber());
        this.parkingCar(car, area);
        this.alertParkingLotSize();

    }

    public synchronized void GoOut(User user) {
        String area = this.findCarArea(user.getCar().getCarNumber());
        this.outParkingLot(user.getCar(), area);
        this.alertleavingLotSize();
        exit.pay(user);
    }

}
