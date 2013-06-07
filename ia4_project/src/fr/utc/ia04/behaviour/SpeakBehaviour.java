package fr.utc.ia04.behaviour;

import fr.utc.ia04.agent.Human;
import fr.utc.ia04.simulation.Beings;
import fr.utc.ia04.simulation.SimulationConstants;

public class SpeakBehaviour extends ProximityBehaviour {
	
	protected Human other = null;
	
	public SpeakBehaviour(Human h, Human other) {
		super(h, SimulationConstants.STATE_SPEAKING, other, SimulationConstants.DIST_INTERACT);
		this.other = other;
	}
	
	@Override
	public void doAction(Beings b, double dt) {
		h.setSocial(h.getSocial()+dt); // TODO régler le coeff
		// TODO partager les infos sur les vampires
	}

	@Override
	public double evalGain() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public boolean isDone() {
		return h.getSocial() >= 1.0 || super.isDone();
	}

}
