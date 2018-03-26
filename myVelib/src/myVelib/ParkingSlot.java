package myVelib;

import Exceptions.ParkingSlotFullException;

public class ParkingSlot {
	
	// Attributes
	private static int nextnumericalID;
	private Bicycle bicycle [] = new Bicycle[1];
	private int parkingID;
	private boolean free;
	private boolean usable;
	
	/* ********************************** Creator ******************************* */
	ParkingSlot() {
		this.parkingID = nextnumericalID;
		nextnumericalID++;
		this.usable = true;
		this.free = true;
	}
	
	
	/* ********************************* Methods ********************************* */
	
	/*
	 * Use this function to put a bicycle @bi in the parkingslot if it's available
	 */
	public void addBicycle(Bicycle bi) throws ParkingSlotFullException {
		if (free && usable) {
			bicycle[0] = bi;
			free = false;		
	}
		else if (!free) {
			throw new ParkingSlotFullException(this);
		}
	}
	
	/*
	 * Use this function to remove the bicycle for this parkingslot if it's not free
	 */
	public Bicycle removeBicycle() {
		if (!free) {
			free = true;	
			Bicycle b = (Bicycle)bicycle[0];
			bicycle[0] = null;
			return b;
	}
		else 
			System.out.println("you can't put a bicycle there");
			return null;
	}
	
	/* ************************************ Display *********************************************** */
		
	public String toString() {
		try {
			return "parkingslot " + this.getParkingID()+" : " + ((!free) ? "[bicycle " +this.getBicycle().getID()+"]" : (!usable)? "[OutofOrder]" : "[free]");
		}
		catch (NullPointerException e) {
			System.err.println("The parkingslot is empty ! ");
			return "Problem !";
		}
	}
	
	/* *************************************** Getters / Setters ***************************************** */
	
	public void setInOrder(boolean bool) {
		if (this.free) {
			this.usable = bool;
		}
		else {
			System.out.println("The place is occupied and can't become outoforder");
		}
	}

	public Bicycle getBicycle() {
		return bicycle[0];
	}

	public int getParkingID() {
		return parkingID;
	}

	public boolean isFree() {
		return free;
	}

	public void setFree(boolean free) {
		this.free = free;
	}

	public boolean isUsable() {
		return usable;
	}

}
