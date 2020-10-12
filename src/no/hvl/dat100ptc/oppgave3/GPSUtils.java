package no.hvl.dat100ptc.oppgave3;

import static java.lang.Math.*;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSUtils {

	public static double findMax(double[] da) {

		double max; 
		
		max = da[0];
		
		for (double d : da) {
			if (d > max) {
				max = d;
			}
		}
		
		return max;
	}

	public static double findMin(double[] da) {

		double min;

		min = da[0];
		
		for (double d : da) {
			if (d < min) {
				min = d;
			}
		}
		
		return min;
	}


	public static double[] getLatitudes(GPSPoint[] gpspoints) {

		double [] latitudes = new double[gpspoints.length];
		
		for (int i = 0; i < gpspoints.length; i++) {
			latitudes[i] = gpspoints[i].getLatitude();
		}
		
		return latitudes;
	}

	public static double[] getLongitudes(GPSPoint[] gpspoints) {

		double [] longitudes = new double[gpspoints.length];
		
		for (int i = 0; i < gpspoints.length; i++) {
			longitudes[i] = gpspoints[i].getLongitude();
		}
		
		return longitudes;

	}

	private static int R = 6371000; // jordens radius

	public static double distance(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		double d;
		double latitude1, longitude1, latitude2, longitude2;
		
		double deltaL;
		double deltaP;

		// TODO - START
		
		latitude1 = Math.toRadians(gpspoint1.getLatitude());
		longitude1 = Math.toRadians(gpspoint1.getLongitude());
		
		latitude2 = Math.toRadians(gpspoint2.getLatitude());
		longitude2 = Math.toRadians(gpspoint2.getLongitude());
		
		deltaL = longitude2 - longitude1;
		deltaP = latitude2 - latitude1;
		
		double a = (pow(sin(deltaP / 2), 2)) + cos(latitude1) * cos(latitude2) * pow(sin(deltaL / 2), 2);
		double c = 2 * atan2(sqrt(a), sqrt(1-a));
		
		d = R * c;
		
		return d;
		
		
		
		

		

		// TODO - SLUTT

	}

	public static double speed(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		int secs = gpspoint2.getTime() - gpspoint1.getTime();
		double speed = distance(gpspoint1, gpspoint2) * 3.6 / secs;
		
		return speed;

		

	}

	public static String formatTime(int secs) {

		String timestr;
		String TIMESEP = ":";

		// TODO - START

		int hr, min, sec;
		
		hr = secs / 3600;
		min = (secs % 3600) / 60;
		sec = secs % 60;
		
		timestr = "  ";
				
		if (hr < 10) {
			timestr += "0" + hr;
		} else {
			timestr += hr;
		} 
		
		timestr += TIMESEP;
		
		if (min < 10) {
			timestr += "0" + min;
		} else {
			timestr += min;
		}
		
		timestr += TIMESEP;
		
		if (sec < 10) {
			timestr += "0" + sec;
		} else {
			timestr += sec;
		}
		
		
		return timestr;
		
		// TODO - SLUTT

	}
	private static int TEXTWIDTH = 10;

	public static String formatDouble(double d) {

		

		// TODO - START

		d = Math.round(d * 100) / 100.0;
		String str = "" + d;
		
		String padding = "";
		
		while (padding.length() + str.length() < 10) {
			padding += " ";
		}
		
		return padding + str;

		// TODO - SLUTT 
		
	}
}
