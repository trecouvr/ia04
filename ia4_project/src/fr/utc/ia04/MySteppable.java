package fr.utc.ia04;

import sim.engine.SimState;
import sim.engine.Steppable;
import sim.engine.Stoppable;
import sim.util.Int2D;

public abstract class MySteppable implements Steppable {
	private static final long serialVersionUID = 7389923094761696870L;
	
	public int x;
	public int y;
	public Stoppable stoppable;
	
	@Override
	public abstract void step(SimState arg0);
	
	protected void meurt(Beings beings) {
		beings.yard.set(x, y, null);
		stoppable.stop();
	}

	public MySteppable(Int2D location) {
		super();
		this.x = location.x;
		this.y = location.y;
	}

	public String toString(){
		return this.getClass().getName();
	}
}
