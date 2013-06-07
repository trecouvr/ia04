package fr.utc.ia04.behaviour;

import sim.util.Double2D;
import fr.utc.ia04.agent.Agent;
import fr.utc.ia04.agent.Human;
import fr.utc.ia04.simulation.Beings;
import fr.utc.ia04.simulation.SimulationConstants;

public class RunAwayBehaviour extends CyclicBehaviour {

	protected Agent other = null;
	protected double walkedDist = 0;
	
	public RunAwayBehaviour(Human h, Agent other) {
		super(h, SimulationConstants.STATE_RUNNINGAWAY);
		this.other = other;
		this.walkedDist = 0;
	}

	@Override
	public boolean preCond() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void doAction(Beings b, double dt) {
		Double2D v = h.getPosition();
		v.subtract(other.getPosition());
		walkedDist += this.h.move(b, dt, v.angle());
	}

	@Override
	public double evalGain() {
		// TODO Auto-generated method stub
		return 0;
	}

}
