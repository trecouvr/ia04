package fr.utc.ia04.simulation;

import java.awt.Color;
import java.awt.Graphics2D;

import javax.swing.JFrame;

import fr.utc.ia04.agent.FastFood;
import fr.utc.ia04.agent.Human;

import sim.display.Controller;
import sim.display.Display2D;
import sim.display.GUIState;
import sim.engine.SimState;
import sim.portrayal.DrawInfo2D;
import sim.portrayal.continuous.ContinuousPortrayal2D;
import sim.portrayal.simple.OvalPortrayal2D;

public class BeingsWithUI extends GUIState {
	public static int FRAME_SIZE = 500;
	public Display2D display;
	public JFrame displayFrame;
	ContinuousPortrayal2D yardPortrayal;
	
	public BeingsWithUI(SimState state) {
		super(state);
		
		this.yardPortrayal = new ContinuousPortrayal2D();
	}
	
	public void start() {
		super.start(); setupPortrayals();
	}
	
	public void load(SimState state) {

	}
	
	public void setupPortrayals() {
		Beings beings = (Beings) state;
		yardPortrayal.setField(beings.yard );
		yardPortrayal.setPortrayalForClass(Human.class, getHumanPortrayal());
		yardPortrayal.setPortrayalForClass(FastFood.class, getFastFoodPortrayal());
		display.reset();
		display.setBackdrop(Color.white);
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

	private OvalPortrayal2D getHumanPortrayal() {
		OvalPortrayal2D r = new OvalPortrayal2D(1.2){
			@Override
			public void draw(Object o, Graphics2D g, DrawInfo2D info){
				Human h = (Human)o;
				this.paint=Color.RED;
				super.draw(o,g,info);
			}
		};
		r.filled = true;
		return r;
	}
	
	private OvalPortrayal2D getFastFoodPortrayal() {
		OvalPortrayal2D r = new OvalPortrayal2D(1.4);
		r.paint = Color.GREEN;
		r.filled = true;
		return r;
	}
}