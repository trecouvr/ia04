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
		double ateValue = SimulationConstants.CHAR_REG_EAT*dt;
		h.setEnergy(h.getEnergy()+ateValue);
	}

	@Override
	public double evalGain() {
		return SimulationConstants.GAIN_HIGHT;
	}
	
	@Override
	public boolean isDone() {
		return h.getEnergy() >= SimulationConstants.CHAR_MAX_ENERGY;
	}

}
