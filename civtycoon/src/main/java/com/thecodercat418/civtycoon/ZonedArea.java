package com.thecodercat418.civtycoon;

public class ZonedArea {
    // Get zoning needs
    // Run random numbers to see if it will be build

    public ZoningAction zone = ZoningAction.NONE;
    Territory occupiedArea;

    public ZonedArea(ZoningAction za, Territory occupiedArea) {
        zone = za;
        this.occupiedArea = occupiedArea;
        String color = "";
        switch (za) {
            case RESIDENTIAL:
                color = "green";
                break;
            case COMMERCIAL:
                color = "blue";
                break;
            case INDUSTRIAL:
                color = "orange";
                break;
            default:
                color = "black";
                break;
        }
        for (Tile t : occupiedArea.t) {
            t.linkedChildPane.setStyle("-fx-background-color: " + color + ";");
        }
        TickManager.register(() -> {
            runTick();
        });
    }

    public void runTick() {
        double want = 0;
        switch (zone) {
            case RESIDENTIAL:
                want = InfomationController.resWant;
                break;
            case COMMERCIAL:
                want = InfomationController.comWant;
                break;
            case INDUSTRIAL:
                want = InfomationController.indWant;
                break;

            default:
                break;
        }
        if (Math.random() * want > Math.abs(want - 100)) {
            createBuilding();
        }
    }

    public void createBuilding() {
        // find building placement
        // Create building
        // switch (zone) {
        // case RESIDENTIAL:
        // InfomationController.resWant;
        // break;
        // case COMMERCIAL:
        // InfomationController.comWant;
        // break;
        // case INDUSTRIAL:
        // InfomationController.indWant;
        // break;

        // default:
        // break;
        // }
    }
}
