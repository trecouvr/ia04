package fr.utc.ia04.behaviour.speBehaviour;

import sim.util.Double2D;
import fr.utc.ia04.agent.Agent;
import fr.utc.ia04.agent.Human;
import fr.utc.ia04.behaviour.Behaviour;
import fr.utc.ia04.simulation.Beings;
import fr.utc.ia04.simulation.SimulationConstants;

public class RunAwayBehaviour extends Behaviour {

	protected Agent other = null;
	protected double agentDist = 0;
	
	public RunAwayBehaviour(Human h, Agent other) {
		super(h, SimulationConstants.STATE_RUNNINGAWAY);
		this.other = other;
		Double2D v = other.getPosition().subtract(h.getPosition());
		agentDist = v.length();
	}

	@Override
	public boolean preCond() {
		return true;
	}

	@Override
	public void doAction(Beings b, double dt) {
		Double2D v = h.getPosition().subtract(other.getPosition());
		agentDist = v.length();
		this.h.move(b, dt, v.angle());
	}

	@Override
	public double evalGain() {
		double g = 1-(agentDist/SimulationConstants.DIST_SECURE);
		return SimulationConstants.GAIN_MAX*g*g;
	}

	@Override
	public boolean isDone() {
		return SimulationConstants.DIST_SECURE<=agentDist;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		
		RunAwayBehaviour other = (RunAwayBehaviour) obj;
		return this.other == other.other;
	}

}
