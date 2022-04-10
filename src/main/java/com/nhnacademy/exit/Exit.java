package com.nhnacademy.exit;

import com.nhnacademy.exception.NotEnoughMoneyException;
import com.nhnacademy.user.User;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Exit {
    private long changedMoney;

    public long pay(User user) {

        long A_HALF_HOUR_SECONDS = 1800; // 30분 - 1000원
        long TEN_MIN_SECONDS = 600; // 10분당 - 500원
        long MAX_FIRST_DAY_SECONDS = 86400; // 하루 - 10_000원
        long OVER_SECOND_DAY_SECONDS = 172800; // 이틀 - 20_000원
        long FIFTY_MIN_SECONDS = 3000; // 50분
        long SIX_HOURS = 21600;
        long SIXTY_ONE = 3601;


        LocalDateTime time = LocalDateTime.now();

        LocalDateTime endTime1 = ChronoUnit.SECONDS.addTo(time, 1801);
        long gapTime1 = (ChronoUnit.SECONDS.between(time, endTime1));


        long money = user.getMoney();
        if (money <= 0) {
            throw new NotEnoughMoneyException("잔액이 부족하여 나갈 수 없습니다!");
        }

        if (gapTime1 > A_HALF_HOUR_SECONDS) { // 1801 > 1800
            int price = 1500;
            changedMoney = user.getMoney() - price;
            if (gapTime1 > A_HALF_HOUR_SECONDS) {
                changedMoney = (user.getMoney() - (price + 500));
            }
            user.setMoney(changedMoney);
        } else if (gapTime1 < A_HALF_HOUR_SECONDS) { // 1801 < 1800
            int price = 1000;
            changedMoney = user.getMoney() - price;
            user.setMoney(changedMoney);
        }

        LocalDateTime endTime2 = ChronoUnit.SECONDS.addTo(time, 3000);
        long gapTime2 = (ChronoUnit.SECONDS.between(time, endTime2));

        if (gapTime2 >= FIFTY_MIN_SECONDS) { // 3000
            int price = 2000;
            changedMoney = user.getMoney() - price;
            user.setMoney(changedMoney);
        }

        LocalDateTime endTime3 = ChronoUnit.SECONDS.addTo(time, 3601);
        long gapTime3 = (ChronoUnit.SECONDS.between(time, endTime3));

        if (gapTime3 >= SIXTY_ONE) {
            int price = 3000;
            changedMoney = user.getMoney() - price;
            user.setMoney(changedMoney);
        }

        LocalDateTime endTime4 = ChronoUnit.SECONDS.addTo(time, 21600);
        long gapTime4 = (ChronoUnit.SECONDS.between(time, endTime4));

        if (gapTime4 >= SIX_HOURS) {
            int price = 10000;
            changedMoney = user.getMoney() - price;
            user.setMoney(changedMoney);
        }

        LocalDateTime endTime5 = ChronoUnit.SECONDS.addTo(time, 84600);
        long gapTime5 = (ChronoUnit.SECONDS.between(time, endTime5));

        if (gapTime5 >= MAX_FIRST_DAY_SECONDS) {
            int price = 10000;
            changedMoney = user.getMoney() - price;
            user.setMoney(changedMoney);
        }

        LocalDateTime endTime6 = ChronoUnit.SECONDS.addTo(time, 169200);
        long gapTime6 = (ChronoUnit.SECONDS.between(time, endTime5));

        if (gapTime5 >= OVER_SECOND_DAY_SECONDS) {
            int price = 20000;
            changedMoney = user.getMoney() - price;
            user.setMoney(changedMoney);

        }
        return user.getMoney();
    }

}
