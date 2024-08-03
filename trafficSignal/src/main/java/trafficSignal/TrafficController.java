package trafficSignal;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TrafficController {
    private static TrafficController instance;
    private final Map<String, Road> roads;

    private TrafficController() {
        roads = new ConcurrentHashMap<>();
    }
    public static synchronized TrafficController getInstance() {
        if (instance == null) {
            instance = new TrafficController();
        }
        return instance;
    }

    public void addRoad(Road road) {
        roads.put(road.getId(), road);
    }

    public void removeRoad(String roadId) {
        roads.remove(roadId);
    }


    public void startTrafficControl(){
        //change traffic light according to the duration
        for(Road road:roads.values()){
            Light trafficLight=road.getTrafficLight();
            new Thread(()->{
                while(true){
                    try {
                        //complete this block
                        System.out.println("RED");
                        Thread.sleep(trafficLight.getRedDuration());
                        trafficLight.changeSignal(Signal.GREEN);
                        System.out.println("GREEN");
                        Thread.sleep(trafficLight.getGreenDuration());
                        trafficLight.changeSignal(Signal.YELLOW);
                        System.out.println("YELLOW");
                        Thread.sleep(trafficLight.getYellowDuration());
                        trafficLight.changeSignal(Signal.RED);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();;
        }
    }
    public void handleEmergency(String roadId) {
        Road road = roads.get(roadId);
        if (road != null) {
            Light trafficLight = road.getTrafficLight();
            trafficLight.changeSignal(Signal.GREEN);
            // Perform emergency handling logic
            // ...
        }
    }
}
