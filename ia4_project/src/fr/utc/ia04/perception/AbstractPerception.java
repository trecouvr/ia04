package fr.utc.ia04.perception;

import fr.utc.ia04.Module;
import fr.utc.ia04.agent.Agent;
import fr.utc.ia04.agent.Human;
import fr.utc.ia04.simulation.Beings;
import sim.field.grid.Grid2D;
import sim.util.Bag;

public class AbstractPerception extends Module {

	public AbstractPerception(Agent a) {
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
		if (!v.isVampire()) {
			System.err.println("Human "+v+" is not a vampire");
		}
		v.getObjectInSight().clear();
		Bag b = beings.yard.getVonNeumannNeighbors(v.getLocation().x, v.getLocation().y, Grid2D.TOROIDAL, v.getPerceptionSkills(), true, null, null, null);
		for (Object o : b) {
			v.getObjectInSight().add(o);
		}
	}
}
