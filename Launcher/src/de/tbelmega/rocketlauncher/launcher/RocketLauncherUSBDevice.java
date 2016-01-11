package de.tbelmega.rocketlauncher.launcher;

import ch.ntb.usb.Device;
import ch.ntb.usb.USB;
import ch.ntb.usb.USBException;

/**
 * Created by Thiemo on 11.01.2016.
 */
public class RocketLauncherUSBDevice {


    public static final int TIMEOUT = 2000;
    public static final boolean REOPEN_ON_TIMEOUT = false;
    public static final int REQUEST = 0x09;
    public static final int REQUEST_TYPE = 0x21;
    public static final int VALUE = 0;
    public static final int INDEX = 0;
    public static final Short ID_VENDOR = (short) 0x2123;
    public static final Short ID_PRODUCT = (short) 0x1010;

    private Device device = null;

    RocketLauncherUSBDevice(){
        usbSetup();
    }

    /**
     * Open the USB Thunder RocketLauncher
     */
    private void usbSetup(){
        device = USB.getDevice(ID_VENDOR, ID_PRODUCT);
        try {
            device.open(1, 0, -1); // Open the device (Configuration(default), Interface(Control), AlternativeConfig(None))
        } catch (USBException e) {
            System.out.println(
                    "Please check the driver for device VID:"
                            + ID_VENDOR
                            + ", PID:"
                            + ID_PRODUCT);
            e.printStackTrace();
        }
    }

    void executeDeviceCommand(Command command) throws USBException {
        if (device.isOpen()){
            byte[] data = command.getByteRepresentation();
            int length = data.length;
            device.controlMsg(REQUEST_TYPE, REQUEST, VALUE, INDEX, data, length, TIMEOUT, REOPEN_ON_TIMEOUT);
        }
    }
}
