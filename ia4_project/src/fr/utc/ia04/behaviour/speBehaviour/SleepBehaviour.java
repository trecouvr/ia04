package fr.utc.ia04.behaviour.speBehaviour;

import fr.utc.ia04.agent.Hotel;
import fr.utc.ia04.agent.Human;
import fr.utc.ia04.behaviour.ProximityBehaviour;
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
	}

	@Override
	public double evalGain() {
		return SimulationConstants.GAIN_HIGHT;
	}
	
	@Override
	public boolean isDone() {
		return h.getPrioCoefAwake() <= 0.1;
	}
}
