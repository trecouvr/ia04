package fr.utc.ia04.behaviour.speBehaviour;

import fr.utc.ia04.agent.FastFood;
import fr.utc.ia04.agent.Human;
import fr.utc.ia04.behaviour.ProximityBehaviour;
import fr.utc.ia04.simulation.Beings;
import fr.utc.ia04.simulation.SimulationConstants;

public class EatBehaviour extends ProximityBehaviour {

	public EatBehaviour(Human h, FastFood o) {
		super(h, SimulationConstants.STATE_EATING, o, SimulationConstants.DIST_INTERACT); // TODO fixer la distance minimale
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
		return h.getPrioCoefEnergy() <= 0.1;
	}

}
