package de.tbelmega.rocketlauncher.observer;

import org.json.JSONObject;

/**
 * Created by Thiemo on 11.01.2016.
 */
public interface Observable {

    public void addObserver(Observer observer);

    public void notifyObserver(JSONObject data);
}
