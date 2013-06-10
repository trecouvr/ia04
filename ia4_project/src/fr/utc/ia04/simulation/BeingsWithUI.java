package fr.utc.ia04.simulation;

import java.awt.Color;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import sim.display.Controller;
import sim.display.Display2D;
import sim.display.GUIState;
import sim.engine.SimState;
import sim.portrayal.DrawInfo2D;
import sim.portrayal.continuous.ContinuousPortrayal2D;
import sim.portrayal.simple.ImagePortrayal2D;
import sim.portrayal.simple.LabelledPortrayal2D;
import sim.portrayal.simple.OvalPortrayal2D;
import fr.utc.ia04.agent.FastFood;
import fr.utc.ia04.agent.Hotel;
import fr.utc.ia04.agent.Human;

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
		yardPortrayal.setPortrayalForClass(Hotel.class, getHotelPortrayal());
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
	
	
	private LabelledPortrayal2D getHumanPortrayal(){
		@SuppressWarnings("serial")
		OvalPortrayal2D r = new OvalPortrayal2D(SimulationConstants.DISP_SCALE_AGT){
			@Override
			public void draw(Object o, Graphics2D g, DrawInfo2D info){
				Human h = (Human)o;
				if (h.isVampire()){
					this.paint = new Color((int) (130+125*h.getGlobalHealth()),0,0);
				}else {
					this.paint = new Color(0,0,(int) (130+125*h.getGlobalHealth()));
				}
				
				super.draw(o,g,info);
			}
		};
		r.filled = true;
		
		
		LabelledPortrayal2D l = new LabelledPortrayal2D(r, null);
		return l;
		
	}

	
	
	private ImagePortrayal2D getFastFoodPortrayal() {
		return new ImagePortrayal2D(new ImageIcon("fastfood.png"), SimulationConstants.DISP_SCALE_IMG);
	}
	
	
	private ImagePortrayal2D getHotelPortrayal(){
		return new ImagePortrayal2D(new ImageIcon("hostel.png"), SimulationConstants.DISP_SCALE_IMG);
	}
}