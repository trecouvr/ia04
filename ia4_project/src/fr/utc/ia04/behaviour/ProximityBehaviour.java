package fr.utc.ia04.behaviour;

import fr.utc.ia04.agent.Agent;
import fr.utc.ia04.agent.Human;

public abstract class ProximityBehaviour extends Behaviour {

	Agent other = null;
	double minDist = 0;
	
	public ProximityBehaviour(Human h, String id, Agent o, double minDist) {
		super(h, id);
		this.minDist = minDist;
		this.other = o;
	}
	
	public boolean preCond() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isDone() {
		return h.distance(other) > minDist;
	}
}
