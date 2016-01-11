package de.tbelmega.rocketlauncher;

/**
 * Created by Thiemo on 10.01.2016.
 */
public enum Command {
    STOP (new byte[]{0x02, 0x20, 0x00,0x00,0x00,0x00,0x00,0x00}),
    LED_ON (new byte[]{0x03, 0x01, 0x00,0x00,0x00,0x00,0x00,0x00}),
    LED_OFF (new byte[]{0x03, 0x00, 0x00,0x00,0x00,0x00,0x00,0x00}),
    UP (new byte[]{0x02, 0x02, 0x00,0x00,0x00,0x00,0x00,0x00}),
    DOWN (new byte[]{0x02, 0x01, 0x00,0x00,0x00,0x00,0x00,0x00}),
    LEFT (new byte[]{0x02, 0x04, 0x00,0x00,0x00,0x00,0x00,0x00}),
    RIGHT (new byte[]{0x02, 0x08, 0x00,0x00,0x00,0x00,0x00,0x00}),
    FIRE(new byte[]{0x02, 0x10, 0x00,0x00,0x00,0x00,0x00,0x00});
    
    private final byte[] byteRepresentation;
    
    Command(byte[] bytes) {
        this.byteRepresentation = bytes;
    }

    public byte[] getByteRepresentation() {
        return byteRepresentation;
    }
}

   