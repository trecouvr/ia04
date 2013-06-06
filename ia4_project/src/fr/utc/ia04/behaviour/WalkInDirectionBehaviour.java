package fr.utc.ia04.behaviour;

import fr.utc.ia04.agent.Human;
import fr.utc.ia04.simulation.Beings;
import fr.utc.ia04.simulation.SimulationConstants;

public class WalkInDirectionBehaviour extends CyclicBehaviour {

	private double direction;
	
	public WalkInDirectionBehaviour(Human h, double direction) {
		super(h, SimulationConstants.STATE_WALKING);
		this.direction = direction;
	}

	@Override
	public boolean preCond() {
		return true;
	}

	@Override
	public void doAction(Beings b, double dt) {
		this.h.move(b, dt, direction);
		
	}

	@Override
	public double evalGain() {
		return SimulationConstants.GAIN_LOW;
	}

}
