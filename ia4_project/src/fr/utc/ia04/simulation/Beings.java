package fr.utc.ia04.simulation;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import fr.utc.ia04.agent.Agent;
import fr.utc.ia04.agent.FastFood;
import fr.utc.ia04.agent.Human;

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
			addAgent(Human.class);
			addAgent(Human.class);
			addAgent(Human.class);
			addAgent(Human.class);
			addAgent(Human.class);
			addAgent(Human.class);
			addAgent(FastFood.class);
			addAgent(FastFood.class);
			addAgent(FastFood.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("rawtypes")
	public void addAgent(Class clss) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Double2D location = randomFreeLocation();
		addAgent(clss, location);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void addAgent(Class clss, Double2D location) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Constructor constructor = clss.getConstructor(Double2D.class);
		Agent agent = (Agent)constructor.newInstance(location);
		yard.setObjectLocation(agent,location);
		Stoppable stoppable=schedule.scheduleRepeating(agent);
		agent.stoppable=stoppable;
	}
	
	public Double2D randomFreeLocation() {
		Double2D location;
		//Object ag = null;
		//do {
			location = new Double2D(	random.nextDouble()*yard.getWidth(),
										random.nextDouble()*yard.getHeight());
			//ag = yard.get(location.x,location.y);
		//} while (ag != null);
		return location;
	}
}
