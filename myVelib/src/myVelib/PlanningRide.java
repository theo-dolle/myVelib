package myVelib;

public class PlanningRide {
	
	private Station source;
	private Station destination;
	private Bicycle bicycle;
	private User user;
	private double timeBicycleTaken;
	private double timeBicycleGivenBack;
	
	PlanningRide(Station source, Station destination, User user) {
		this.source = source;
		this.destination = destination;
		this.user = user;
	}
	
	private void setDestination(Station destination) {
		this.destination = destination;
	}
	
	public String toString() {
		return "\n Departure : station n� " +  source.getStationID() + " | Arrival : station n� " + destination.getStationID() +
				(UserHasABicycle() ? "" : " | " + this.bicycle.toString());
		
	}
	public Station getStationSource() {
		return this.source;
	}
	
	public Station getStationDestination() {
		return this.destination;
	}
	protected void addBicycle(Bicycle bicycle, double time) {
		if (this.bicycle != null) {
			System.err.println("You already got a bicycle in this ride");
		} else {
			this.bicycle = bicycle;
			timeBicycleTaken = time;
		}		
	}
	
	public boolean UserHasABicycle() {
		return (this.bicycle == null);
	}
	
	public Bicycle getBicycle() {
		return this.bicycle;
	}
	
	public void setTimeGivenBack(double time) {
		this.timeBicycleGivenBack = time;
	}
	
	public double getTimeGivenBack() {
		return this.timeBicycleGivenBack ;
	}
	
	public double getTimeTaken() {
		return this.timeBicycleTaken ;
	}

}
