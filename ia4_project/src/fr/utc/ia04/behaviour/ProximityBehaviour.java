package fr.utc.ia04.behaviour;

import fr.utc.ia04.agent.Agent;
import fr.utc.ia04.agent.Human;

public abstract class ProximityBehaviour extends Behaviour {

	Agent other;
	double minDist;
	
	public ProximityBehaviour(Human h, String id, Agent o, double minDist) {
		super(h, id);
		this.minDist = minDist;
		this.other = o;
	}
	
	public boolean preCond() {
		return h.distance(other) <= minDist;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		
		ProximityBehaviour beh = (ProximityBehaviour) obj;
		return this.other == beh.other;
	}
	
}
