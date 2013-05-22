package fr.utc.ia04;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import sim.engine.SimState;
import sim.engine.Stoppable;
import sim.field.grid.ObjectGrid2D;
import sim.util.Int2D;

public class Beings extends SimState {
	private static final long serialVersionUID = -7476975926693044771L;
	
	public static int GRID_SIZE = 100;
	public ObjectGrid2D yard =
			new ObjectGrid2D(GRID_SIZE,GRID_SIZE);

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
			addAgent(Vampire.class);
			addAgent(Vampire.class);
			addAgent(Vampire.class);
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
		Int2D location = randomFreeLocation();
		addAgent(clss, location);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void addAgent(Class clss, Int2D location) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Constructor constructor = clss.getConstructor(Int2D.class);
		MySteppable agent = (MySteppable)constructor.newInstance(location);
		yard.set(agent.x,agent.y,agent);
		Stoppable stoppable=schedule.scheduleRepeating(agent);
		agent.stoppable=stoppable;
	}
	
	public Int2D randomFreeLocation() {
		Int2D location;
		Object ag = null;
		do {
			location = new Int2D(random.nextInt(yard.getWidth()),
				random.nextInt(yard.getHeight()));
			ag = yard.get(location.x,location.y);
		} while (ag != null);
		return location;
	}
}
