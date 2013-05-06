package td_ia04;
import sim.display.Console;
import sim.display.GUIState;
import sim.engine.SimState;

public class BeingsMain {


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