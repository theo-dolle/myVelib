package myVelib;

import java.util.ArrayList;

public class ComputingRide {
	
	private User user;
	private ArrayList<Station> stations;
	private double[] destination = new double[2];
	private RidePreferences ridepref;
	private String typeBicycle;
	
	
	ComputingRide(User user, ArrayList<Station> stations, double latitude, double longitude) {
			this.user = user;
			this.stations = stations;
			this.destination[0] = latitude;
			this.destination[1] = longitude;
			this.ridepref = new FastestPath();
			this.typeBicycle = "Electrical";
	}
	
	ComputingRide(User user, ArrayList<Station> stations, double latitude, double longitude, String type, RidePreferences pref) {
		this.user = user;
		this.stations = stations;
		this.destination[0] = latitude;
		this.destination[1] = longitude;
		this.ridepref = pref;
		this.typeBicycle = type;
}
	
	public PlanningRide computeWay() throws ComputingRideImpossibleException {
		
		try {
			return ridepref.compute(stations, user, destination, typeBicycle);
			
		}
		catch (NullPointerException e) {
			System.out.println(e);
		}
		throw new ComputingRideImpossibleException();
	}

}
