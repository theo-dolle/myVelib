package myVelib;

public class User {
	
	private String name;
	private static int nextnumericalID;
	private int UserID;
	private float timecreditbalance;
	SubscriptionPossibility subscription;
	private double[] GPScoordinate = new double[2];
	
	User(String name) {
		
		try {
			this.name = name;
			timecreditbalance = 0;
			this.UserID = nextnumericalID;
			nextnumericalID++;
			this.subscription = new WithoutSubscription();
			this.GPScoordinate[0] = 0;
			this.GPScoordinate[1] = 0;
			
		} catch (NullPointerException e) {
		System.err.println("You tried to enter a null name !");
	}
	}
	
	User(String name, float timecreditbalance, SubscriptionPossibility subscription, double latitude, double longitude) {
		
		try {
			this.name = name;
			this.timecreditbalance = timecreditbalance;
			this.UserID = nextnumericalID;
			nextnumericalID++;
			this.subscription = subscription;
			this.GPScoordinate[0] = latitude;
			this.GPScoordinate[1] = longitude;
		} catch (NullPointerException e) {
			System.err.println("You tried to enter a null name or subscription !");
		}
	}
	
	public static void main(String[] args) {
		
	}
	
	public double getUserLat() {
		return this.GPScoordinate[0];
	}
	
	public double getUserLong() {
		return this.GPScoordinate[1];
	}
	
	public int payement(float time, Bicycle bicycle) {
		int cost = subscription.cost(time, bicycle, this);
		System.out.println("The cost of the rent is " + cost + " �");
		return cost;
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getUserID() {
		return UserID;
	}

	public void setUserID(int userID) {
		UserID = userID;
	}

	public float getTimecreditbalance() {
		return timecreditbalance;
	}

	public void setTimecreditbalance(float f) {
		this.timecreditbalance = f;
	}
	
	public void setSubscription(SubscriptionPossibility sub) {
		this.subscription = sub;
		
	}

	

}