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
		
		int id = fillCircle (MARGIN + (int)((gpspoints[0].getLongitude() - minLon) * xstep()),
					ybase - (int)((gpspoints[0].getLatitude() - minLat) * ystep()),
					3);
		
		for (int i = 0; i < gpspoints.length; i ++) {
//			setColor((int)Math.random() * 100, 255 - i, i);
			moveCircle(id, MARGIN + (int)((gpspoints[i].getLongitude() - minLon) * xstep()),
					ybase - (int)((gpspoints[i].getLatitude() - minLat) * ystep()));
			
			fillCircle(MARGIN + (int)((gpspoints[i].getLongitude() - minLon) * xstep()),
					ybase - (int)((gpspoints[i].getLatitude() - minLat) * ystep()),
					3);
			
			if (i + 1 < gpspoints.length) {
			drawLine(MARGIN + (int)((gpspoints[i].getLongitude() - minLon) * xstep()),
					ybase - (int)((gpspoints[i].getLatitude() - minLat) * ystep()),
					MARGIN + (int)((gpspoints[i + 1].getLongitude() - minLon) * xstep()),
					ybase - (int)((gpspoints[i + 1].getLatitude() - minLat) * ystep()));
			setSpeed(5);
			
			}
			
			
		}
		
		
		
		// TODO - SLUTT
	}

	public void showStatistics() {

		int TEXTDISTANCE = 20;

		setColor(0,0,0);
		setFont("Courier",12);
		
		// TODO - START
		
//		String time = "Total Time     :" + formatTime(GPSComputer.totalTime());
//		String distance = "Total distance :" + formatDouble(totalDistance() / 1000) + " km";
//		String elevation = ("Total elevation:" + formatDouble(totalElevation()) + " m");
//		String speed = ("Max speed      :" + formatDouble(maxSpeed()));
//		String avgSpeed = ("Average speed  :" + formatDouble(averageSpeed()));
//		String energy = ("Energy         :" + formatDouble(totalKcal(WEIGHT)));
		
		// TODO - SLUTT;
	}

}
