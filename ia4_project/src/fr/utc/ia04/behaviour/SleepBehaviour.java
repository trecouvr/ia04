package fr.utc.ia04.behaviour;

import fr.utc.ia04.agent.Hotel;
import fr.utc.ia04.agent.Human;
import fr.utc.ia04.simulation.Beings;
import fr.utc.ia04.simulation.SimulationConstants;

public class SleepBehaviour extends ProximityBehaviour {

	public SleepBehaviour(Human h, Hotel o) {
		super(h, SimulationConstants.STATE_SLEEPING, o, SimulationConstants.DIST_INTERACT);
	}

	@Override
	public void doAction(Beings b, double dt) {
		double sleptValue = SimulationConstants.CHAR_REG_SLEEP*dt;
		h.setAwake(h.getAwake()+sleptValue);
		System.out.println("i sleep");
	}

	@Override
	public double evalGain() {
		return SimulationConstants.GAIN_HIGHT;
	}
	
	@Override
	public boolean isDone() {
		return h.getAwake() >= SimulationConstants.CHAR_MAX_AWAKE;
	}
}
