package de.tbelmega.rocketlauncher.launcher;

import ch.ntb.usb.USBException;

import static de.tbelmega.rocketlauncher.launcher.Command.*;

/**
 * Created by Thiemo on 10.01.2016.
 */
public class RocketLauncher {

    public static final int MAX_RANGE_UP_DOWN = 2000;
    public static final int MAX_RANGE_RIGHT_LEFT = 6000;
    public static final int DURATION_FROM_RIGHT_TO_MIDDLE_POSITION = 2730;
    public static final int DURATION_FROM_BOTTOM_TO_HORIZONTAL = 200;

    /**
     * number of milliseconds to move upwards for 0,1° angle
     */
    public static final double UPWARDS_DURATION_COEFFICIENT = 100.0 / 36.8;

    private final RocketLauncherUSBDevice usbDevice = new RocketLauncherUSBDevice();



    /**
     * Turns LED off
     * and sets the launcher to a horizontal, middle position.
     *
     * @throws USBException
     */
    public void reset() throws USBException {
        this.execute(LED_OFF, 1);

        this.execute(DOWN, MAX_RANGE_UP_DOWN);
        this.execute(LEFT, MAX_RANGE_RIGHT_LEFT);
        this.execute(RIGHT, DURATION_FROM_RIGHT_TO_MIDDLE_POSITION);
        this.execute(UP, DURATION_FROM_BOTTOM_TO_HORIZONTAL);

        this.execute(LED_ON, 1);
    }


    /**
     * @param command  Use the public "Command" enum to control the turret.
     * @param duration The number of milliseconds to execute the command.
     *                 The FIRE command takes about 3300 milliseconds.
     */
    public void execute(Command command, long duration) throws USBException {
        usbDevice.executeDeviceCommand(command);
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() - start < duration) {
            //Burn Time and let the RocketLauncher execute.
        }
        usbDevice.executeDeviceCommand(STOP);
    }

    public void prepareFire() throws USBException {
        this.execute(FIRE, 1650);
    }

    public void fire() throws USBException {
        this.execute(FIRE, 1650);
    }


    /**
     *
     * @param angleUp angle in 10 * degrees (e.g. 92 for 9,2°)
     */
    public void aimUp(int angleUp) throws USBException {
        long duration = calculateDuration(angleUp);
        this.execute(UP,duration);
    }

    public static long calculateDuration(int i) {
        return Math.round(i * UPWARDS_DURATION_COEFFICIENT);
    }

}
