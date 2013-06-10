package fr.utc.ia04.simulation;

import fr.utc.ia04.agent.Agent;
import fr.utc.ia04.agent.FastFood;
import fr.utc.ia04.agent.Human;
import fr.utc.ia04.agent.Hotel;

import sim.engine.SimState;
import sim.engine.Stoppable;
import sim.field.continuous.Continuous2D;
import sim.util.Double2D;

public class Beings extends SimState {
	private static final long serialVersionUID = -7476975926693044771L;
	
	public Continuous2D yard = new Continuous2D(SimulationConstants.ENV_DISCRETIZATION,SimulationConstants.ENV_SIZE,SimulationConstants.ENV_SIZE);

	public Beings(long seed) {
		super(seed);
	}

	public void start() {
		super.start();
		yard.clear();
		try {
			for(int i=0; i<SimulationConstants.NUM_FASTFOOD; i++){
				FastFood f = new FastFood(randomFreeLocation());
				addAgent(f);
			}
			for(int i=0; i<SimulationConstants.NUM_HOTEL; i++){
				Hotel h = new Hotel(randomFreeLocation());
				addAgent(h);
			}
			for(int i=0; i<SimulationConstants.NUM_HUMAN; i++) {
				Human h = new Human(randomFreeLocation());
				if (i==2) {
					h.makeVampire();
				}
				addAgent(h);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void addAgent(Agent a) {
		yard.setObjectLocation(a,a.getPosition());
		Stoppable stoppable=schedule.scheduleRepeating(a);
		a.stoppable=stoppable;
	}
	
	public Double2D randomFreeLocation() {
		Double2D location;
		//Object ag = null;
		//do {
			location = new Double2D(	(0.15*yard.getWidth())+random.nextDouble()*yard.getWidth()/1.5,
										(0.15*yard.getHeight())+random.nextDouble()*yard.getHeight()/1.5);
			//ag = yard.get(location.x,location.y);
		//} while (ag != null);
		return location;
	}
}
