package Exceptions;

import myVelib.Station;

public class StationOfflineException extends Exception {
	
	private Station station;
	
	public StationOfflineException(Station station) {
		// TODO Auto-generated constructor stub
		this.station = station;
	}
	
	public String toString() {
		return "Station n� " + station.getStationID()+" is offline";
	}


}
