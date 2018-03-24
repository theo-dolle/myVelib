package myVelib;

import java.util.Scanner;

public class Playground {
	
	public static void main(String[] args)  throws AskPlanningRideImpossibleException, StationOfflineException, StationEmptyException, StationFullException {
		Simulation simu = new Simulation(10,1,20);
		System.out.println(simu);
		
		simu.newRide(0, 10, 15, "Electrical", new FastestPath());		
		show(simu);
		simu.takeABicycleInTheStationSource(0, "Electrical", 50);
		show(simu);
		simu.returnABicycleInTheStationDestination(0, 52.5);		
		show(simu);
		simu.newRide(0, 0, 3, "Electrical", new FastestPath());		
		show(simu);
		simu.takeABicycleInTheStationSource(0, "Electrical", 53);
		show(simu);
		simu.returnABicycleInTheStationDestination(0, 54.5);		
		show(simu);
		
		
	}
	
	public static void show(Simulation simu) {
		System.out.println(simu);
	}
	
	
}
