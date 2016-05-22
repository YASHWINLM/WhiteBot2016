package org.jointheleague.iaroc;

import android.os.SystemClock;

import ioio.lib.api.IOIO;
import ioio.lib.api.exception.ConnectionLostException;
import org.wintrisstech.irobot.ioio.IRobotCreateAdapter;
import org.wintrisstech.irobot.ioio.IRobotCreateInterface;
import org.jointheleague.iaroc.sensors.UltraSonicSensors;

public class Brain extends IRobotCreateAdapter {
    private final Dashboard dashboard;
    public UltraSonicSensors sonar;

    public Brain(IOIO ioio, IRobotCreateInterface create, Dashboard dashboard)
            throws ConnectionLostException {
        super(create);
        sonar = new UltraSonicSensors(ioio);
        this.dashboard = dashboard;
    }

    /* This method is executed when the robot first starts up. */
    public void initialize() throws ConnectionLostException {
        dashboard.log("Hello! I'm a Clever Robot!");
    }
    /* This method is called repeatedly. */
    public void loop() throws ConnectionLostException {
        readSensors(SENSORS_BUMPS_AND_WHEEL_DROPS);
        readSensors(27);
        if (getWallSignal() > 0){
            driveDirect(500,500);
        }
        else {

            driveDirect(500, 500);
            SystemClock.sleep(300);
            driveDirect(500, 100);
            SystemClock.sleep(500);
        }
        if (isBumpRight() && isBumpLeft()){
            dashboard.log("front");
            driveDirect(0, -500);
            SystemClock.sleep(750);
        }
        else if (isBumpLeft()){
            dashboard.log("left");
            driveDirect(0, -500);
            SystemClock.sleep(500);
        }
        else if (isBumpRight()){
            dashboard.log("right");
            driveDirect(0, -500);
            SystemClock.sleep(500);
        }
        dashboard.log(getWallSignal() + "");
    }
}