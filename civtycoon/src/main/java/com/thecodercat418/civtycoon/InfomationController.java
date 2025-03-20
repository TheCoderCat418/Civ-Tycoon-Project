package com.thecodercat418.civtycoon;

public class InfomationController {
    public static int money = 1000;

    public static int population = 0;
    // private static double growthFactor = 0.0;

    public static void setup() {
        TickManager.register(() -> {
            update();
        });
    }

    public static void addPopulation(int numOfPeople) {
        population+= numOfPeople;
    }

    private static void update() {
        money+=population*2;
    }
}
