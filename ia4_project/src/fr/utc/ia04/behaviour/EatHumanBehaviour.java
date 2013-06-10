package fr.utc.ia04.behaviour;

import fr.utc.ia04.agent.Human;
import fr.utc.ia04.simulation.Beings;
import fr.utc.ia04.simulation.SimulationConstants;

public class EatHumanBehaviour extends ProximityBehaviour {

	protected Human other;
	public EatHumanBehaviour(Human h, Human o) {
		super(h, SimulationConstants.STATE_EATINGHUM, o, SimulationConstants.DIST_NEAR);
		this.other = o;
	}
	
	@Override
	public boolean preCond() {
		return h.isVampire();
	}

	@Override
	public void doAction(Beings b, double dt) {
		// TODO fixer coeffs
		other.setEnergy(other.getEnergy()-dt*2.0);  // décrémente l'énergy de l'humain
		h.setEnergy(h.getEnergy()+dt*2.0);			// monte l'énergy du vampire
	}

	@Override
	public double evalGain() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public boolean isDone() {
		return other.getEnergy() <= 0 || h.getEnergy() >= SimulationConstants.CHAR_MAX_ENERGY;
	}

}
