package command;

import java.util.Map;

import Gameground.Simulation;

public class Offline implements Command {
	
	String name;
	
	Offline(){
		this.name = "offline";
	}

	@Override
	public void test(String[] mots, Map<String, Simulation> simulations, String[] lignes, int ligne) {
		
		if(mots.length == 3)
		{
			try {
				String velibnetworkName = mots[1];
				int stationID = Integer.parseInt(mots[2]);
				simulations.get(velibnetworkName).getStations().get(stationID).becomeOffline();
				System.out.println("\n The station " +  stationID + " of the simulation " + velibnetworkName + " has become offline!");
			}
			catch(NumberFormatException e)
			{
				System.out.println("\n\n" +"Vous avez rentr� un format non adapt� � la ligne " + ligne + " dans les arguments de la commande \"" + lignes[ligne] + "\"");
				System.out.println("offline <velibnetworkName, stationID>");
			}
			catch (NullPointerException e) {
				System.out.println("\n One of the arguments of the command \"" + lignes[ligne] + "\" you entered is not correct, has not been defined in the simulation, or you have not even entered a name of an existing simulation");
				System.out.println("offline <velibnetworkName, stationID>");
			}
		}
		else
		{
			System.out.println("\n\n" +"Pas le bon nombre d'arguments avec la commande \"" + lignes[ligne] + "\" � la ligne " + ligne);
			System.out.println("offline <velibnetworkName, stationID>");
		}
		
	}

}
