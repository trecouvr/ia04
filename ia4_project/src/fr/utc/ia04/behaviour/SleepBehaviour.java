package fr.utc.ia04.behaviour;

import fr.utc.ia04.agent.Hotel;
import fr.utc.ia04.agent.Human;
import fr.utc.ia04.simulation.Beings;
import fr.utc.ia04.simulation.SimulationConstants;

public class SleepBehaviour extends ProximityBehaviour {

	public SleepBehaviour(Human h, Hotel o) {
		super(h, SimulationConstants.STATE_SLEEPING, o, 1.0); // TODO fixer la distance minimale
	}

	@Override
	public void doAction(Beings b, double dt) {
		h.setAwake(h.getAwake()+dt); // TODO régler le coeff
	}

	@Override
	public double evalGain() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public boolean isDone() {
		return h.getAwake() >= 1.0 || super.isDone();
	}
}
