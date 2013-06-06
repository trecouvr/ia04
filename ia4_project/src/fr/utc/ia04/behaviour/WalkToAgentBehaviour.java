package fr.utc.ia04.behaviour;

import fr.utc.ia04.agent.Agent;
import fr.utc.ia04.agent.Human;
import fr.utc.ia04.simulation.Beings;
import fr.utc.ia04.simulation.SimulationConstants;

public class WalkToAgentBehaviour extends Behaviour {

	private Agent target;
	private final double evaluatedDist;
	private double walkedDist;
	
	public WalkToAgentBehaviour(Human h, Agent a) {
		super(h, SimulationConstants.STATE_WALKING);
		evaluatedDist = h.getPosition().distance(target.getPosition());
		walkedDist = 0;
	}

	@Override
	public boolean preCond() {
		return ( h.getPosition().distance(target.getPosition()) < h.getPerceptionSkills() );
	}

	@Override
	public void doAction(Beings b, double dt) {
		walkedDist += this.h.move(b, dt, target.getPosition());
	}

	@Override
	public boolean isDone() {
		return SimulationConstants.DIST_INTERACT > this.h.getPosition().distance(target.getPosition());
	}

	@Override
	public double evalGain() {
		return 0;
	}

}
