package no.hvl.dat100ptc.oppgave5;

import static no.hvl.dat100ptc.oppgave3.GPSUtils.formatDouble;
import static no.hvl.dat100ptc.oppgave3.GPSUtils.formatTime;


import javax.swing.JOptionPane;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;

public class ShowRoute extends EasyGraphics {

	private static int MARGIN = 50;
	private static int MAPXSIZE = 800;
	private static int MAPYSIZE = 600;

	private GPSPoint[] gpspoints;
	private GPSComputer gpscomputer;
	
	public ShowRoute() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		gpscomputer = new GPSComputer(filename);

		gpspoints = gpscomputer.getGPSPoints();

	}

	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		makeWindow("Route", MAPXSIZE + 2 * MARGIN, MAPYSIZE + 2 * MARGIN);

		showRouteMap(MARGIN + MAPYSIZE);
		
		showStatistics();
	}

	// antall x-pixels per lengdegrad
	public double xstep() {

		double maxlon = GPSUtils.findMax(GPSUtils.getLongitudes(gpspoints));
		double minlon = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));

		double xstep = MAPXSIZE / (Math.abs(maxlon - minlon)); 

		return xstep;
	}

	// antall y-pixels per breddegrad
	public double ystep() {
	
		double ystep;
		
		// TODO - START
		
		double maxlat = GPSUtils.findMax(GPSUtils.getLatitudes(gpspoints));
		double minlat = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));
		
		ystep = MAPYSIZE / (Math.abs(maxlat - minlat));
		
		return ystep;

		// TODO - SLUTT
		
	}

	public void showRouteMap(int ybase) {

		// TODO - START
		double minLon = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));
		double minLat = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));
		setColor(0, 255, 0);
		for (int i = 0; i < gpspoints.length; i ++) {
			
			fillCircle(MARGIN + (int)((gpspoints[i].getLongitude() - minLon) * xstep()),
					ybase - (int)((gpspoints[i].getLatitude() - minLat) * ystep()),
					3);
			
			if (i + 1 < gpspoints.length) {
			drawLine(MARGIN + (int)((gpspoints[i].getLongitude() - minLon) * xstep()),
					ybase - (int)((gpspoints[i].getLatitude() - minLat) * ystep()),
					MARGIN + (int)((gpspoints[i + 1].getLongitude() - minLon) * xstep()),
					ybase - (int)((gpspoints[i + 1].getLatitude() - minLat) * ystep()));
			
			}
			
			
		}
		
	}

	private static double WEIGHT = 80.0;

	public void showStatistics() {

		int TEXTDISTANCE = 20;

		setColor(0,0,0);
		setFont("Courier",12);
		
		int x = MARGIN;
		int y = MARGIN;
		
		String time = "Total Time     :" + formatTime(gpscomputer.totalTime()) + " ";
		String distance = "Total distance :" + formatDouble(gpscomputer.totalDistance() / 1000) + " km ";
		String elevation = ("Total elevation:" + formatDouble(gpscomputer.totalElevation()) + " m ");
		String speed = ("Max speed      :" + formatDouble(gpscomputer.maxSpeed()) + " ");
		String avgSpeed = ("Average speed  :" + formatDouble(gpscomputer.averageSpeed()) + " ");
		String energy = ("Energy         :" + formatDouble(gpscomputer.totalKcal(WEIGHT)) + " ");
		
		drawString(time, x, y);
		y += TEXTDISTANCE;
		drawString(distance, x, y);
		y += TEXTDISTANCE;
		drawString(elevation, x, y);
		y += TEXTDISTANCE;
		drawString(speed, x, y);
		y += TEXTDISTANCE;
		drawString(avgSpeed, x, y);
		y += TEXTDISTANCE;
		drawString(energy, x, y);
		y += TEXTDISTANCE;
		
		
	}

}
