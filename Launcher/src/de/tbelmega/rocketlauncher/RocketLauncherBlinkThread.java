package de.tbelmega.rocketlauncher;

import ch.ntb.usb.USBException;

/**
 * Created by Thiemo on 10.01.2016.
 */
class RocketLauncherBlinkThread implements Runnable {
    private RocketLauncher launcher;
    private long interval = 500;
    private boolean running;

    RocketLauncherBlinkThread(RocketLauncher launcher){
        this.launcher = launcher;
    }

    @Override
    public void run() {
        running = true;
        try {
            while(running){
                launcher.blink(interval);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (USBException e) {
            throw new RuntimeException(e);
        }
    }

    public void stop() {
        running = false;
    }

    public void setInterval(long interval) {
        this.interval = interval;
    }
}
