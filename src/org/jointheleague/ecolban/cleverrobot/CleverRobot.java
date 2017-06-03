package org.jointheleague.ecolban.cleverrobot;

/*********************************************************************************************
 * Vic's ultrasonic sensor running with Erik's Clever Robot for Pi
 * version 0.9, 170227
 **********************************************************************************************/
import java.io.IOException;

import org.jointheleague.ecolban.rpirobot.IRobotAdapter;
import org.jointheleague.ecolban.rpirobot.IRobotInterface;
import org.jointheleague.ecolban.rpirobot.SimpleIRobot;

public class CleverRobot extends IRobotAdapter {
	Sonar sonar = new Sonar();

	public CleverRobot(IRobotInterface iRobot) {
		super(iRobot);
	}

	public static void main(String[] args) throws Exception {
		System.out.println("Try event listner, rev Monday 2030");
		IRobotInterface base = new SimpleIRobot();
		CleverRobot rob = new CleverRobot(base);
		rob.setup();
		while (rob.loop()) {
		}
		rob.shutDown();

	}

	private void setup() throws Exception {
		driveDirect(200, 200);
	}

	private boolean loop() throws Exception {
		isBumpLeft();
		isBumpRight();
		readSensors(100);
		isLightBump();

		if (isLightBump()) {
			driveDirect(50, 50);

		}
		if (isBumpLeft()) {
			driveDirect(-100, -100);
			Thread.sleep(2000);
			driveDirect(0, 500);
			Thread.sleep(1000);
			driveDirect(200, 200);
		}
		if (isBumpRight()) {
			driveDirect(-100, -100);
			Thread.sleep(2000);
			driveDirect(500, 0);
			Thread.sleep(1000);
			driveDirect(200, 200);
		}
		return true;
	}

	private void shutDown() throws IOException {
		reset();
		stop();
		closeConnection();
	}
}
