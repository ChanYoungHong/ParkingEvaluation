package com.nhnacademy.parkingsystem;

public enum PriceList implements Calculatable {

    THIRTYMIN(30, 1000) {
        @Override
        public int pay(int time, int price) {
            for (int i = time; i < time; time++) {
                if (time == 30) {
                    price = 1000;
                }
            }
            return price;
        }
    },
    TENMIN(10, 500) {
        @Override
        public int pay(int time, int price) {
            for (int i = time; i < time; time++) {
                if (time >= 10) {
                    price += 500;
                }
            }
            return price;
        }
    },
    FIRSTDAY(1440, 10_000) {
        @Override
        public int pay(int time, int price) {
            return price;
        }
    },
    SECONDDAY(2880, 20_000) {
        @Override
        public int pay(int time, int price) {
            if (time == 2880) {
                price = 20_000;
            }

            return price;
        }
    };

    private final int time;
    private final int price;

    PriceList(int time, int price) {
        this.time = time;
        this.price = price;
    }

}
