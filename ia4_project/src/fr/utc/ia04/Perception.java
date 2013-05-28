package fr.utc.ia04;

import sim.field.grid.Grid2D;
import sim.util.Bag;

public class Perception extends Module {

	public Perception(Agent a) {
		super(a);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void postAction(Beings beings) {
		// TODO Auto-generated method stub
	}

	@Override
	public void preAction(Beings beings) {
		Human v = (Human)agent;
		if (!v.isVampire) {
			System.err.println("Human "+v+" is not a vampire");
		}
		v.objectInSight.clear();
		Bag b = beings.yard.getVonNeumannNeighbors(v.x, v.y, Grid2D.TOROIDAL, v.perception, true, null, null, null);
		for (Object o : b) {
			v.objectInSight.add(o);
		}
	}
}
