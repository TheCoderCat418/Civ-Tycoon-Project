package com.thecodercat418.civtycoon;

public class ZonedArea {
    //Get zoning needs
    //Run random numbers to see if it will be build
    
    public ZoningAction zone = ZoningAction.NONE;

    public ZonedArea(ZoningAction za){
        zone = za;
    }
    public void runTick(){

        //Math.random() * residentalWant > Math.abs(want-1)
    }
}
