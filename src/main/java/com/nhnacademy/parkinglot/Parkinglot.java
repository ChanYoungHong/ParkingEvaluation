package com.nhnacademy.parkinglot;


import com.nhnacademy.car.Car;
import java.util.ArrayList;

public class Parkinglot {
    ArrayList<Car> spaces = new ArrayList<>();

    public void parking(Car car, String area) {
        car.setCarArea(area);
        spaces.add(car);
    }

    public int alertSpaceSize() {
        return spaces.size();
    }

    public String findArea(String carNum) {
        for (int i = 0; i < spaces.size(); i++) {
            if (spaces.get(i).getCarNumber().equals(carNum)) {
                return spaces.get(i).getCarArea();
            }
        }
        return null;
    }

    public void outArea(Car car, String area) {
        car.setCarArea(area);
        spaces.remove(car);
    }

}
