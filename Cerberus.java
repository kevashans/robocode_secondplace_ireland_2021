package dkit;
import robocode.Robot;
import java.awt.Color;
import robocode.ScannedRobotEvent;
import static robocode.util.Utils.normalRelativeAngleDegrees;

/**
 * Cerberus - a robot by Dillon & Kevas
 */
public class Cerberus extends Robot
{	
	//Enemy Variables
	double enemyDistance, enemyHeading, enemyBearing;
	String enemyName;

	//Sentry Variables
	double sentryDistance, sentryBearing;
		
	//Our Variables
	int scanCount = 0;
	double robotHeading, robotGunHeading, aimGun, gunHeading;
	
	public void run() 
    {
		
		//Setting the colour
		setAllColors(Color.black);
		setAdjustGunForRobotTurn(true);

		while(true) 
        {
			scan();
			scanCount ++;
			
			//source : http://mark.random-article.com/weber/java/robocode/lesson5.html
			turnGunRight(360);
			turnRight(enemyBearing - 90);
			ahead(100 * 1 - 15 * -1);
		}
	}
	public void onScannedRobot(ScannedRobotEvent e) 
	{		
		//source : https://www.youtube.com/watch?v=4TUwwjP4KNk&t=36s
    	enemyName = e.getName();
		enemyDistance = e.getDistance();
		enemyBearing = e.getBearing();
		gunHeading = getGunHeading();
		robotHeading = getHeading();
		
		if (e.isSentryRobot() == false && scanCount > 0)
		{
			//locking
			aimGun = (robotHeading + enemyBearing)- gunHeading;
			turnGunRight(normalRelativeAngleDegrees(aimGun));//use degrees
        
			if(enemyDistance <= 60 )
			{
				fire(6);
		    }
			else if(enemyDistance > 60 && enemyDistance<= 150)
			{
			    fire(4);
			}
			else
			{
				fire(2);
			}
	    }
	}
}
