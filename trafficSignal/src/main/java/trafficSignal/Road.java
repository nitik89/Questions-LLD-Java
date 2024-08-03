package trafficSignal;

public class Road {
    private final String id;
    private final String name;
    private Light trafficLight;

    public Road(String id, String name) {
        this.id = id;
        this.name = name;
    
    }

    public void setTrafficLight(Light trafficLight) {
        this.trafficLight = trafficLight;
    }

    public Light getTrafficLight() {
        return trafficLight;
    }

    public String getId() {
        return id;
    }



}
