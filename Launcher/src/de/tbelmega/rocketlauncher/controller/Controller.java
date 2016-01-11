package de.tbelmega.rocketlauncher.controller;

import ch.ntb.usb.USBException;
import de.tbelmega.rocketlauncher.communication.Receiver;
import de.tbelmega.rocketlauncher.launcher.RocketLauncher;
import de.tbelmega.rocketlauncher.observer.Observer;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by Thiemo on 11.01.2016.
 */
public class Controller implements Observer {

    public static final int DELAY = 10000; //minimum count of milliseconds between two shots
    public static final String JSON_PROPERTY_DEGREES_TIMES_TEN = "degrees_times_ten";

    private RocketLauncher launcher;
    private Receiver receiver;
    private long lastShot = 0;

    public void initSystem() throws IOException, TimeoutException {
        receiver = new Receiver();
        receiver.addObserver(this);

        launcher = new RocketLauncher();
        launcher.reset();
        //launcher.prepareFire(); //uncomment if launcher stopped in an odd state

        receiver.run();
    }

    @Override
    public void update(JSONObject data) {
        try {
            if (System.currentTimeMillis() - lastShot > DELAY){
                launch(data);
            }
        } catch (USBException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void launch(JSONObject data) throws JSONException, USBException {
        int angle = data.getInt(JSON_PROPERTY_DEGREES_TIMES_TEN);
        launcher.aimUp(angle);
        launcher.fire();
        launcher.prepareFire();
        lastShot = System.currentTimeMillis();

        launcher.reset();
    }

    public static void main (String[] args){
        try {

            new Controller().initSystem();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
