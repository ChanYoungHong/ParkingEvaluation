package com.nhnacademy.parkingsystem;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import com.nhnacademy.car.Car;
import com.nhnacademy.entrance.Entrance;

import com.nhnacademy.exception.NotEnoughMoneyException;
import com.nhnacademy.exit.Exit;
import com.nhnacademy.user.User;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ParkingsystemTest {

    ParkingSystem parkingSystem;
    User user;
    Exit exit;

    @BeforeEach
    void setUp() {
        parkingSystem = new ParkingSystem();
        exit = new Exit();
    }

    Entrance entrance = new Entrance();

    @Test
    @DisplayName("주차장에 차가 들어온다.")
    void car_is_coming_at_parkinglot() {
        String carNum1 = "123A 5678";
        Car car = new Car(carNum1, LocalDateTime.now());
        assertThat(entrance.scan(carNum1)).isEqualTo(carNum1);
    }

    @Test
    @DisplayName("주차장에 주차한다")
    void go_parking_at_specific_area() {
        Car car = new Car("12허 3333", LocalDateTime.now());
        parkingSystem.parkingCar(car, "A-1");
        assertThat(parkingSystem.alertParkingLotSize()).isEqualTo(1);
    }

    @Test
    @DisplayName("주차된 차가 A-1에 들어가도록 한다")
    void check_parking_car_area() {
        Car car = new Car("12허 3333", LocalDateTime.now());
        parkingSystem.parkingCar(car, "A-1");
        assertThat(parkingSystem.findCarArea("12허 3333")).isEqualTo("A-1");
    }

    @Test
    @DisplayName("주차장에서 차가 나간다")
    void mycar_go_out_from_parkinglot() {
        Car car = new Car("12허 3333", LocalDateTime.now());
        parkingSystem.outParkingLot(car, "A-1");
        assertThat(parkingSystem.alertleavingLotSize()).isEqualTo(0);
    }

    @Test
    @DisplayName("돈이 없으면 나갈 수 없습니다.")
    void if_you_dont_have_money_Exception() {
        String carNumber = "12허 3333";
        Car car = new Car(carNumber);
        user = new User(car, -1000);

        assertThatThrownBy(() -> exit.pay(user))
            .isInstanceOf(NotEnoughMoneyException.class)
            .hasMessageContaining("잔액", "부족");
    }

    @Test
    @DisplayName("요금표로 나가는 차 계산하기")
    void we_need_to_price_list() {

        long TimeStandard1 = 1801; // 30분 1초 - 1000원
        long TimeStandard2 = 600; // 10분당 - 500원
        long TimeStandard3 = 86400; // 하루 - 10_000원
        long TimeStandard4 = 172800; // 이틀 - 20_000원
        long price = 1000;
        String carNumber = "12허 3333";
        Car car = new Car(carNumber);

        User user1 = new User(car, 1500);
        User user2 = new User(car, 2000);
        User user3 = new User(car, 3000);
        User user4 = new User(car, 10000);
        User user5 = new User(car, 20000);

        LocalDateTime time = LocalDateTime.now();

        LocalDateTime endTime1 = ChronoUnit.SECONDS.addTo(time, 1801);
        long gapTime1 = (ChronoUnit.SECONDS.between(time, endTime1));

        if (TimeStandard1 < gapTime1) {   // 30분 1초 < 30분    1801 < 1800
            price = 1000;
        } else if (TimeStandard1 > gapTime1) { // 30분 1초 > 30분 1801 > 1800
            price = 1500;
        }
        user1.setMoney(1500);
        assertThat(user.getMoney()).isEqualTo(1500);

        LocalDateTime endTime2 = ChronoUnit.SECONDS.addTo(time, 3000);
        long gapTime2 = (ChronoUnit.SECONDS.between(time, endTime2));

        if (gapTime2 >= 3000) {
            price = 2000;
        }
        user2.setMoney(2000);
        assertThat(user.getMoney()).isEqualTo(2000);

        LocalDateTime endTime3 = ChronoUnit.SECONDS.addTo(time, 3601);
        long gapTime3 = (ChronoUnit.SECONDS.between(time, endTime3));

        if (gapTime3 >= 3601) {
            price = 3000;
        }
        user3.setMoney(3000);
        assertThat(user.getMoney()).isEqualTo(3000);

        LocalDateTime endTime4 = ChronoUnit.SECONDS.addTo(time, 86400);
        long gapTime4 = (ChronoUnit.SECONDS.between(time, endTime4));

        if (gapTime4 >= TimeStandard3) {
            price = 10000;
        }
        user4.setMoney(10000);
        assertThat(user.getMoney()).isEqualTo(10000);

        LocalDateTime endTime5 = ChronoUnit.SECONDS.addTo(time, 172800);
        long gapTime5 = (ChronoUnit.SECONDS.between(time, endTime5));

        if (gapTime5 >= TimeStandard4) {
            price = 20000;
        }
        user5.setMoney(20000);
        assertThat(user.getMoney()).isEqualTo(20000);
    }
}
