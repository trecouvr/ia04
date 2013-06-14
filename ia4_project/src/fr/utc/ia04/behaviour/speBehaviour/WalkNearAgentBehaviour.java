package fr.utc.ia04.behaviour.speBehaviour;

import fr.utc.ia04.agent.Agent;
import fr.utc.ia04.agent.Human;
import fr.utc.ia04.behaviour.Behaviour;
import fr.utc.ia04.simulation.Beings;
import fr.utc.ia04.simulation.SimulationConstants;

public class WalkNearAgentBehaviour extends Behaviour {

	private Agent target;
	private final double nearDist;
	private final double evaluatedDist;
	private double walkedDist;
	
	public WalkNearAgentBehaviour(Human h, Agent a, double nearDist) {
		super(h, SimulationConstants.STATE_WALKING);
		this.target = a;
		this.nearDist = nearDist;
		evaluatedDist = h.getPosition().distance(target.getPosition());
		walkedDist = 0;
	}

	@Override
	public boolean preCond() {
		return ( h.getPosition().distance(target.getPosition()) < h.getPerceptionSkills() );
	}

	@Override
	public void doAction(Beings b, double dt) {
		walkedDist += this.h.move(b, dt, target.getPosition(), this.nearDist);
	}

	@Override
	public boolean isDone() {
		return this.nearDist > this.h.getPosition().distance(target.getPosition());
	}

	@Override
	public double evalGain() {
		if( walkedDist < evaluatedDist )
			return SimulationConstants.GAIN_HIGHT;
		else
			return SimulationConstants.GAIN_HIGHT * (evaluatedDist / walkedDist);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		
		WalkNearAgentBehaviour other = (WalkNearAgentBehaviour) obj;
		return this.target == other.target;
	}

}
