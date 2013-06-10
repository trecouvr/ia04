package fr.utc.ia04.behaviour;

import fr.utc.ia04.agent.FastFood;
import fr.utc.ia04.agent.Human;
import fr.utc.ia04.simulation.Beings;
import fr.utc.ia04.simulation.SimulationConstants;

public class EatBehaviour extends ProximityBehaviour {

	public EatBehaviour(Human h, FastFood o) {
		super(h, SimulationConstants.STATE_EATING, o, 1.0); // TODO fixer la distance minimale
	}

	@Override
	public void doAction(Beings b, double dt) {
		h.setEnergy(h.getEnergy()+dt); // TODO régler le coeff
	}

	@Override
	public double evalGain() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public boolean isDone() {
		return h.getEnergy() >= SimulationConstants.CHAR_MAX_ENERGY;
	}

}
