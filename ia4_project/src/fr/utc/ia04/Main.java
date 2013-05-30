package fr.utc.ia04;

import fr.utc.ia04.simulation.Beings;
import fr.utc.ia04.simulation.BeingsWithUI;
import sim.display.Console;

public class Main {

	public static void main(String[] args) {
		runUI();
	}
	
	
	public static void runUI() {
		Beings model = new Beings(System.currentTimeMillis());
		BeingsWithUI gui = new BeingsWithUI(model);
		Console console = new Console(gui);
		console.setVisible(true);
	}

}
