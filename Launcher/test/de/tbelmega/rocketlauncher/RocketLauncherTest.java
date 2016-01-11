package de.tbelmega.rocketlauncher;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.*;

/**
 * Created by Thiemo on 10.01.2016.
 */
public class RocketLauncherTest {

    RocketLauncher rocketLauncher = new RocketLauncher();

    @Test
    public void testThatLEDisBlinking() throws Exception {
        //arrange
        rocketLauncher.reset();

        //act
        rocketLauncher.blink(500);

    }

    @Test
    public void testThatLEDisBlinkingFor10Seconds() throws Exception {
        //arrange
        rocketLauncher.reset();

        //act
        rocketLauncher.startBlinking(500);
        rocketLauncher.stopBlinking();
    }

    @Test
    public void testThatLauncherFires() throws Exception {
        //arrange
        rocketLauncher.reset();

        //act
        rocketLauncher.prepareFire();
        rocketLauncher.fire();
    }

    @Test
    public void testThatLauncherAims() throws Exception {
        //arrange
        rocketLauncher.reset();

        //act
        rocketLauncher.prepareFire();
        rocketLauncher.aimUp(92);

        rocketLauncher.fire();
    }

    @Test
    public void testThat9Dot2DegreesTake250Milliseconds(){
        //arrange

        //act
        long duration = RocketLauncher.calculateDuration(92);

        //assert
        assertEquals(250, duration);
    }


}
