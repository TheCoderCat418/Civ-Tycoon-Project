package com.thecodercat418.civtycoon;

public class InfomationController {
    public static double resWant = 0.0;
    public static double comWant = 0.0;
    public static double indWant = 0.0;

    public static int population = 0;
    private static double growthFactor = 0.0;
    public static int cash = 0;

    public static void setup(){
        TickManager.register(() -> {
            update();
        });
    }

    public static void addPopulation(int numOfPeople){
        resWant -= (0.05*numOfPeople);
        population++;
    }

    

    private static void update(){
        resWant += 0.001;
        comWant += 0.001;
        indWant += 0.001;
    }   
}
