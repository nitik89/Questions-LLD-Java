/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package trafficSignal;

/**
 *
 * @author nitiknarang
 */
public class TrafficSignal {

    public static void main(String[] args) {
        TrafficController trafficController = TrafficController.getInstance();

        // Create roads
        Road road1 = new Road("R1", "Main Street");
        Road road2 = new Road("R2", "Broadway");
        Road road3 = new Road("R3", "Park Avenue");
        Road road4 = new Road("R4", "Elm Street");

        Light trafficLight1 = new Light("TL1", 6000, 3000, 9000);
        Light trafficLight2 = new Light("TL2", 6000, 3000, 9000);
        Light trafficLight3 = new Light("TL3", 6000, 3000, 9000);
        Light trafficLight4 = new Light("TL4", 6000, 3000, 9000);

        // Assign traffic lights to roads
        road1.setTrafficLight(trafficLight1);
        road2.setTrafficLight(trafficLight2);
        road3.setTrafficLight(trafficLight3);
        road4.setTrafficLight(trafficLight4);

        // Add roads to the traffic controller
        trafficController.addRoad(road1);
        trafficController.addRoad(road2);
        trafficController.addRoad(road3);
        trafficController.addRoad(road4);

           // Start traffic control
           trafficController.startTrafficControl();
    }
}
