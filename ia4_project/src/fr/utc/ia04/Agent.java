package fr.utc.ia04;

import java.util.ArrayList;
import sim.engine.SimState;
import sim.engine.Steppable;
import sim.engine.Stoppable;
import sim.util.Int2D;

public abstract class Agent implements Steppable {
	private static final long serialVersionUID = 7389923094761696870L;
	
	public int x;
	public int y;
	public Stoppable stoppable;
	
	protected ArrayList<Module> modules;
	
	protected Behaviour behaviour;
	
	
	public Agent(Int2D location) {
		super();
		this.x = location.x;
		this.y = location.y;
		this.modules = new ArrayList<Module>();
		System.out.println("constructor");
	}

	public void step(SimState arg0) {
		for (Module m : modules) m.preAction((Beings)arg0);
		//behaviour.doAction(); // faire l�action de ce tour
		for (Module m : modules) m.postAction((Beings)arg0);
	}

	public String toString(){
		return this.getClass().getName();
	}
	
	protected void meurt(Beings beings) {
		beings.yard.set(x, y, null);
		stoppable.stop();
	}
}
