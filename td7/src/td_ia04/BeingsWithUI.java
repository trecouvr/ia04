package td_ia04;

import java.awt.Color;

import javax.swing.JFrame;

import sim.display.Controller;
import sim.display.Display2D;
import sim.display.GUIState;
import sim.engine.SimState;
import sim.portrayal.grid.ObjectGridPortrayal2D;
import sim.portrayal.simple.OvalPortrayal2D;

public class BeingsWithUI extends GUIState {
	public static int FRAME_SIZE = 400;
	public Display2D display;
	public JFrame displayFrame;
	ObjectGridPortrayal2D yardPortrayal =
			new ObjectGridPortrayal2D();
	public BeingsWithUI(SimState state) {
		super(state);
	}
	public void start() {
		super.start(); setupPortrayals();
	}
	public void load(SimState state) {

	}
	public void setupPortrayals() {
		Beings beings = (Beings) state;
		yardPortrayal.setField(beings.yard );
		yardPortrayal.setPortrayalForClass(Insecte.class, getInsectePortrayal());
		yardPortrayal.setPortrayalForClass(Nourriture.class, getNourriturePortrayal());
		display.reset();
		display.setBackdrop(Color.yellow);
		display.repaint();
	}
	public void init(Controller c) {
		super.init(c);
		display = new Display2D(FRAME_SIZE,FRAME_SIZE,this);
		display.setClipping(false);
		displayFrame = display.createFrame();
		displayFrame.setTitle("Beings");
		c.registerFrame(displayFrame);
		displayFrame.setVisible(true);
		display.attach( yardPortrayal, "Yard" );
	}

	private OvalPortrayal2D getInsectePortrayal() {
		OvalPortrayal2D r = new OvalPortrayal2D();
		r.paint = Color.RED;
		r.filled = true;
		return r;
	}
	
	private OvalPortrayal2D getNourriturePortrayal() {
		OvalPortrayal2D r = new OvalPortrayal2D();
		r.paint = Color.GREEN;
		r.filled = true;
		return r;
	}
}