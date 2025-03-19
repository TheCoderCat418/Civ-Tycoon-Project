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
        for (Tile t : occupiedArea.t) {
            //Find the one next to roads
            if(true){ //Next to road
            //Create house by seeing how far you can extend. (directly relates to the number of people, max size 3x3)
            // Has the house class and is isshued a tick entry. holds people(person[]) and monitors them to upgrade the house. should also have some random needs
            // House should be knolageable of the stats around it (e.g distance away from fire station etc.)
            }
        }
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
