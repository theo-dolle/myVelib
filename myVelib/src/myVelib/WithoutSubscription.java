package myVelib;

public class WithoutSubscription implements SubscriptionPossibility {

	@Override
	public void getCredit(float time, User user) {
		System.out.println("You can't obtain credits since you are not a suscriber");
		
	}

	@Override
	public int cost(float time, Bicycle bicycle, User user) {
		if (bicycle instanceof ElectricalBicycle) {
			return (int) (2*Math.ceil(time));
		} else {
			return (int) (Math.ceil(time));
		
		}
	}
	

}
