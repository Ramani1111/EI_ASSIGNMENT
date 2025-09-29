package observer;

import java.util.*;

// Simple Observer pattern: WeatherStation notifies displays about temperature change.
interface Observer {
    void update(int temperature);
}

interface Subject {
    void addObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();
}

class WeatherStation implements Subject {
    private final List<Observer> observers = new ArrayList<>();
    private int temperature;

    public void setTemperature(int t) {
        this.temperature = t;
        notifyObservers();
    }

    public int getTemperature() { return temperature; }

    public void addObserver(Observer o) { observers.add(o); }
    public void removeObserver(Observer o) { observers.remove(o); }
    public void notifyObservers() {
        for (Observer o : observers) o.update(temperature);
    }
}

class Display implements Observer {
    private final String name;
    Display(String name){ this.name = name; }
    public void update(int temperature) {
        System.out.println(name + " display: temperature updated -> " + temperature + "°C");
    }
}

public class Main {
    public static void main(String[] args) {
        WeatherStation station = new WeatherStation();
        Display d1 = new Display("ControlRoom");
        Display d2 = new Display("Mobile");
        station.addObserver(d1);
        station.addObserver(d2);

        station.setTemperature(25);
        station.setTemperature(28);
    }
}
