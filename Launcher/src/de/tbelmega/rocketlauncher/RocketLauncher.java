package de.tbelmega.rocketlauncher;

import ch.ntb.usb.USBException;

import static de.tbelmega.rocketlauncher.Command.*;

/**
 * Created by Thiemo on 10.01.2016.
 */
public class RocketLauncher {

    public static final int MAX_RANGE_UP_DOWN = 2000;
    public static final int MAX_RANGE_RIGHT_LEFT = 6000;
    public static final int DURATION_FROM_RIGHT_TO_MIDDLE_POSITION = 2760;
    public static final int DURATION_FROM_BOTTOM_TO_HORIZONTAL = 200;

    /**
     * number of milliseconds to move upwards for 0,1° angle
     */
    public static final double UPWARDS_DURATION_COEFFICIENT = 100.0 / 36.8;

    private final RocketLauncherUSBDevice usbDevice = new RocketLauncherUSBDevice();
    private RocketLauncherBlinkThread blink = new RocketLauncherBlinkThread(this);




    /**
     * Stops blinking, turns LED off
     * and sets the launcher to a horizontal, middle position.
     *
     * @throws USBException
     */
    public void reset() throws USBException {
        this.stopBlinking();
        this.execute(LED_OFF, 1);

        this.execute(DOWN, MAX_RANGE_UP_DOWN);
        this.execute(LEFT, MAX_RANGE_RIGHT_LEFT);
        this.execute(RIGHT, DURATION_FROM_RIGHT_TO_MIDDLE_POSITION);
        this.execute(UP, DURATION_FROM_BOTTOM_TO_HORIZONTAL);
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
        this.execute(FIRE, 1800);
    }

    public void fire() throws USBException {
        this.execute(FIRE, 1500);
    }

    public void blink(long interval) throws InterruptedException, USBException {
        RocketLauncher.this.execute(Command.LED_ON, 1);
        Thread.sleep(interval);
        RocketLauncher.this.execute(Command.LED_OFF, 1);
        Thread.sleep(interval);
    }

    public void startBlinking(long interval) {
        blink.setInterval(interval);
        blink.run();
    }

    public void stopBlinking() {
        blink.stop();
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
