package td_ia04;

import java.awt.Color;
import java.awt.Graphics2D;

import javax.swing.JFrame;

import sim.display.Controller;
import sim.display.Display2D;
import sim.display.GUIState;
import sim.engine.SimState;
import sim.portrayal.DrawInfo2D;
import sim.portrayal.grid.ObjectGridPortrayal2D;
import sim.portrayal.simple.OvalPortrayal2D;

public class BeingsWithUI extends GUIState {
	public static int FRAME_SIZE = 700;
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
		OvalPortrayal2D r = new OvalPortrayal2D(1.2){
			@Override
			public void draw(Object o, Graphics2D g, DrawInfo2D info){
				Insecte i = (Insecte)o;
				if(i.distancePerception>=0.6*Insecte.NBRE_POINTS)
					this.paint=Color.RED;
				else if(i.chargeMax>=0.6*Insecte.NBRE_POINTS)
					this.paint=Color.BLUE;
				else if(i.distanceDeplacement>=0.6*Insecte.NBRE_POINTS)
					this.paint=Color.BLACK;
				else if(i.distancePerception==0.5*Insecte.NBRE_POINTS)
					this.paint=Color.white;
				else this.paint=Color.MAGENTA;
				super.draw(o,g,info);
			}
		};
		
		r.paint = Color.RED;
		r.filled = true;
		return r;
	}
	
	private OvalPortrayal2D getNourriturePortrayal() {
		OvalPortrayal2D r = new OvalPortrayal2D(1.4);
		r.paint = Color.GREEN;
		r.filled = true;
		return r;
	}
}