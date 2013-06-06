package fr.utc.ia04.behaviour;

import fr.utc.ia04.agent.Human;
import fr.utc.ia04.simulation.Beings;
import fr.utc.ia04.simulation.SimulationConstants;

public class DoNothingBehaviour extends CyclicBehaviour {

	public DoNothingBehaviour(Human h) {
		super(h, SimulationConstants.STATE_DONOTHING);
	}

	@Override
	public boolean preCond() {
		return true;
	}

	@Override
	public void doAction(Beings b, double dt) {
		// Do Nothing
	}

	@Override
	public double evalGain() {
		return SimulationConstants.GAIN_NULL;
	}

}
