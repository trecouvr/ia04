package fr.utc.ia04.behaviour;

import fr.utc.ia04.agent.Human;
import fr.utc.ia04.simulation.Beings;
import fr.utc.ia04.simulation.SimulationConstants;

public class AttakBehaviour extends ProximityBehaviour {

	protected Human other = null;
	
	public AttakBehaviour(Human h, Human o) {
		super(h, SimulationConstants.STATE_ATTAKING, o, SimulationConstants.DIST_INTERACT);
		this.other = o;
	}

	@Override
	public void doAction(Beings b, double dt) {
		other.setEnergy(other.getEnergy()-dt*SimulationConstants.HUMAN_ATTK);
	}

	@Override
	public double evalGain() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public boolean isDone() {
		return other.getEnergy() <= 0.0;
	}
}
