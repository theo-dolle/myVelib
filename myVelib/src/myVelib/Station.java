package myVelib;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Station {
	
	private ArrayList<ParkingSlot> parkingslot = new ArrayList<ParkingSlot>();
	private ArrayList<ParkingSlot> freeparkingslot = new ArrayList<ParkingSlot>();
	private ArrayList<ParkingSlot> occupiedparkingslot = new ArrayList<ParkingSlot>();
	private Map<String, Integer> NumberBicycle = new HashMap<>();
	private boolean inorder;
	private double[] GPScoordinate =  new double[2];
	private static int nextnumericalID;
	private int StationID;
	
	Station(){
		this.inorder = true;
		this.GPScoordinate[0] = 0;
		this.GPScoordinate[1] = 0;
		this.StationID = nextnumericalID;
		nextnumericalID++;
		NumberBicycle.put("Electrical",0);
		NumberBicycle.put("Mechanical", 0);
	}
	
	Station(double latitude, double longitude){
		this.inorder = true;
		this.GPScoordinate[0] = latitude;
		this.GPScoordinate[1] = longitude;
		this.StationID = nextnumericalID;
		nextnumericalID++;
		NumberBicycle.put("Electrical",0);
		NumberBicycle.put("Mechanical", 0);
	}
	
	Station(ArrayList<ParkingSlot> parkingslot, double latitude, double longitude) {
		
		try {
			this.parkingslot = parkingslot;
			this.inorder = true;
			this.GPScoordinate[0] = latitude;
			this.GPScoordinate[1] = longitude;
			this.StationID = nextnumericalID;
			nextnumericalID++;
			NumberBicycle.put("Electrical", 0);
			NumberBicycle.put("Mechanical", 0);
		
			//initialisation of the free parkingslot list by testing each parkingslot of the station
			for (ParkingSlot p : parkingslot) {
				if (p.isFree())
					freeparkingslot.add(p);
				else {
					occupiedparkingslot.add(p);
					updateNumberBicyclePlus(p.getBicycle());
				
				
			}
		}
		}
		
		catch (NullPointerException e ) {
			System.err.println("Wrong credits to initialize this station ! You must have entered a null entry");
		}
	}
		
		
	
	public static void main(String[] args) throws StationOfflineException, ParkingSlotFullException, StationEmptyException, StationFullException {
		Bicycle b = new ElectricalBicycle();
		Bicycle b2 = new ElectricalBicycle();
		Bicycle b3 = new MechanicalBicycle();
		Bicycle b4 = new ElectricalBicycle();
		System.out.println(b);
		System.out.println(b2);
		System.out.println(b3);
		System.out.println(b4);
		ParkingSlot p1 = new ParkingSlot();
		ParkingSlot p2 = new ParkingSlot();
		ParkingSlot p3 = new ParkingSlot();
		ParkingSlot p4 = new ParkingSlot();
		System.out.println(p1);
		System.out.println(p2);
		p1.addBicycle(b);
		p2.addBicycle(b3);
		p3.addBicycle(b2);
		p1.removeBicycle();
		ArrayList<ParkingSlot> parkingslot = new ArrayList<ParkingSlot>();
		
		parkingslot.add(p1);
		parkingslot.add(p2);
		
		Station station = new Station(parkingslot, 35.5666d, 43.44444d);
		System.out.println(station);
		Bicycle bb = station.needBicycle();
		station.addParkingSlot(p3);
		System.out.println(station);
		System.out.println(bb);
		station.needBicycle();
		System.out.println(station);
		station.returnBicycle(b4);
		System.out.println(station);
		station.returnBicycle(b);
		System.out.println(station);
		station.returnBicycle(b2);
		System.out.println(station);


		
		
	}
	
	public boolean hasStationBicycle(String type) {
		try {
			return NumberBicycle.get(type) != 0;
		}
		catch (NullPointerException e) {
			System.err.println("A wrong type of bicycle has been asked !");
		}
		return false;
	}
	public double getStationLat() {
		return this.GPScoordinate[0];
	}
	
	public double getStationLong() {
		return this.GPScoordinate[1];
	}
	
	public void addParkingSlot(ParkingSlot p) {
		this.parkingslot.add(p);
		if (p.isFree()) {
			freeparkingslot.add(p);
		} else {
			occupiedparkingslot.add(p);
			updateNumberBicyclePlus(p.getBicycle());
		}
	}
	
	public int getStationID() {
		return this.StationID;
	}
	
	public void becomeOffline() {
		this.inorder = false;
	}
	
	public void slotisfree(ParkingSlot p) {
		if (!freeparkingslot.contains(p)) {
			freeparkingslot.add(p);
			occupiedparkingslot.remove(p);
		}
	}
		
	public void slotisoccupied(ParkingSlot p) {
		if (!occupiedparkingslot.contains(p)) {
			occupiedparkingslot.add(p);
			freeparkingslot.remove(p);
		}
		
	}
	public Bicycle needBicycle() throws StationOfflineException, StationEmptyException {
		if (!this.inorder) {
			throw new StationOfflineException(this);
		} else {
			boolean igotabicycle = false;
			int n = 0;
			while (!igotabicycle && n < occupiedparkingslot.size()) {
				if (occupiedparkingslot.get(n).isUsable()) {
					Bicycle b = occupiedparkingslot.get(n).removeBicycle();
					this.slotisfree(occupiedparkingslot.get(n));
					igotabicycle = true;
					updateNumberBicycleLess(b);
					return b;
					}
			}
				
		}
		throw new StationEmptyException(this);
			
	}
	
	public void returnBicycle(Bicycle bicycle) throws StationOfflineException, StationFullException {
		if (!this.inorder) {
			throw new StationOfflineException(this);
		} else if (freeparkingslot.size() == 0) {
			throw new StationFullException(this);
		} else {
			boolean igotaplace = false;
			int n = 0;
			while (!igotaplace && n < freeparkingslot.size()) {
				try {
					ParkingSlot p = freeparkingslot.get(n);
					if (p.isUsable()) {
						p.addBicycle(bicycle);
						this.slotisoccupied(p);
						igotaplace = true;
						updateNumberBicyclePlus(p.getBicycle());
					}
				}


				catch (ParkingSlotFullException e) {
					
				}
			
		}
		
	}
	}
	
	private void updateNumberBicyclePlus(Bicycle bicycle) {
		if (bicycle instanceof ElectricalBicycle) {
			NumberBicycle.put("Electrical", NumberBicycle.get("Electrical")+1);
		} else if (bicycle instanceof MechanicalBicycle) {
			NumberBicycle.put("Mechanical", NumberBicycle.get("Mechanical")+1);
		}
	}
	
	private void updateNumberBicycleLess(Bicycle bicycle) {
		if (bicycle instanceof ElectricalBicycle) {
			NumberBicycle.put("Electrical", NumberBicycle.get("Electrical")-1);
		} else if (bicycle instanceof MechanicalBicycle) {
			NumberBicycle.put("Mechanical", NumberBicycle.get("Mechanical")-1);
		}
	}
	
	public boolean isFull() {

		for (ParkingSlot p : freeparkingslot) {
			if (p.isUsable()) {
				return false;
			}
		}
		return true;
	}
	
	public boolean isOffline() {
		return !this.inorder;
	}
	
	public String toString() {
		return "-------------------------------------------\n" + 
				"The station " + this.getStationID()+" has " + freeparkingslot.size() + " free slot(s) " + freeparkingslot.toString()+ " and " + occupiedparkingslot.size() 
		+ " occupied slot(s)" + occupiedparkingslot.toString() + "\n There is " + NumberBicycle.get("Electrical") + " electrical bicycle(s) and "+
				NumberBicycle.get("Mechanical") + " mechanical bicycle(s)\n" + "-------------------------------------------"; 
	}
	
}
