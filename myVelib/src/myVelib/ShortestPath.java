package myVelib;

import java.util.ArrayList;

public class ShortestPath implements RidePreferences {

	@Override
	public PlanningRide compute(ArrayList<Station> stations, User user, double[] destination) throws ComputingRideImpossibleException {
		
		
		try {
			//We take one possible value for the distance if there is at least one station
			double distance = Math.pow(user.getUserLat()-stations.get(0).getStationLat(),2) + Math.pow(user.getUserLong()-stations.get(0).getStationLong(),2) ;
			Station departure = stations.get(0);
			Station arrival = stations.get(1);
			for (Station s1 : stations) {
				for (Station s2 : stations) {
					
					if (!s1.equals(s2)) { //station arrival != departure
						double distanceNew = Math.sqrt(Math.pow(user.getUserLat()-s1.getStationLat(),2) 
								+ Math.pow(user.getUserLong()-s1.getStationLong(),2)) + //walk between the position of the user and the station
								Math.sqrt(Math.pow(s1.getStationLat()-s2.getStationLat(),2) +
								Math.pow(s1.getStationLong()-s2.getStationLong(),2))+ // between the 2 stations
								Math.sqrt(Math.pow(destination[0]-s2.getStationLat(),2)
										+ Math.pow(destination[1]-s2.getStationLong(),2)); // walk between the station and the destination
						if (distanceNew < distance) { //and s1 et s2 possiblement pris
							departure = s1;
							arrival = s2;
							distance = distanceNew;
						}
								
					}
				}
				
			}
			
			return new PlanningRide(departure, arrival, user);
		}
		catch (NullPointerException e) {
			System.out.println(e);
		}
		throw new ComputingRideImpossibleException();
		
	} 
	
	
	

}
