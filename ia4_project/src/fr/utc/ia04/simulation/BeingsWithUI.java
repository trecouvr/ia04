package fr.utc.ia04.simulation;
import java.awt.Color;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import sim.display.Controller;
import sim.display.Display2D;
import sim.display.GUIState;
import sim.engine.SimState;
import sim.engine.Steppable;
import sim.portrayal.DrawInfo2D;
import sim.portrayal.continuous.ContinuousPortrayal2D;
import sim.portrayal.simple.ImagePortrayal2D;
import sim.portrayal.simple.LabelledPortrayal2D;
import sim.portrayal.simple.OvalPortrayal2D;
import fr.utc.ia04.agent.FastFood;
import fr.utc.ia04.agent.Hotel;
import fr.utc.ia04.agent.Human;

import org.jfree.data.xy.XYSeries;
public class BeingsWithUI extends GUIState {
	public static int FRAME_SIZE = 800;
	public Display2D display;
	public JFrame displayFrame;
	ContinuousPortrayal2D yardPortrayal;

	protected XYSeries series;    // the data series we'll add to
	protected sim.util.media.chart.TimeSeriesChartGenerator chart;  // the charting facility
	protected JFrame chartFrame;
	protected Beings b;

	public BeingsWithUI(SimState state) {
		super(state);
		b = (Beings) state;
		this.yardPortrayal = new ContinuousPortrayal2D();

	}

	public void start() {
		super.start(); 
		setupPortrayals();

		chart.removeAllSeries();
		series = new org.jfree.data.xy.XYSeries(
				"% de vampires connus",
				false);
		chart.addSeries(series, null);

		this.scheduleRepeatingImmediatelyAfter(new Steppable()
		{
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			long last = 0;
			public void step(SimState state)
			{

				if (System.currentTimeMillis() - last > 1000){

					int nbVampire = 0;
					int nbHuman = 0;


					for (Object o : b.yard.allObjects){
						if (o instanceof Human){
							Human h = (Human)o;
							if (h.isVampire())	
								nbVampire++;
							else 
								nbHuman++;
						}

					}
					
					//TODO nbKnowVamp /nbHum /nbVampire
					
					double x = state.schedule.time(); 
					double y = (double)((double)nbVampire /(nbHuman+nbVampire))*100;

					series.add(x, y, true);
					chart.updateChartLater(state.schedule.getSteps());
					last = System.currentTimeMillis();
				}
			}
		});




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



		chart = new sim.util.media.chart.TimeSeriesChartGenerator();
		chart.setTitle("Graph");

		chartFrame = chart.createFrame();
		// perhaps you might move the chart to where you like.
		chartFrame.setVisible(true);
		chartFrame.setTitle("Graphiques");
		chartFrame.pack();
		c.registerFrame(chartFrame);

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