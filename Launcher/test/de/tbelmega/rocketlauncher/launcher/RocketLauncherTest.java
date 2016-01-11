package de.tbelmega.rocketlauncher.launcher;

import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

/**
 * Created by Thiemo on 10.01.2016.
 */
public class RocketLauncherTest {

    RocketLauncher rocketLauncher = new RocketLauncher();


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
