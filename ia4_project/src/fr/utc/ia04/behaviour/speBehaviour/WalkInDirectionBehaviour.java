package fr.utc.ia04.behaviour.speBehaviour;

import fr.utc.ia04.agent.Human;
import fr.utc.ia04.behaviour.CyclicBehaviour;
import fr.utc.ia04.simulation.Beings;
import fr.utc.ia04.simulation.SimulationConstants;

public class WalkInDirectionBehaviour extends CyclicBehaviour {

	private double direction;
	private double walkedDist;
	
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
		this.walkedDist += this.h.move(b, dt, direction);
		
	}

	@Override
	public double evalGain() {
		return SimulationConstants.GAIN_LOW / this.walkedDist;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		
		WalkInDirectionBehaviour other = (WalkInDirectionBehaviour) obj;
		if (Double.doubleToLongBits(direction) != Double
				.doubleToLongBits(other.direction))
			return false;
		return true;
	}

}
