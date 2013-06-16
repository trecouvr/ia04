package fr.utc.ia04.behaviour;

import fr.utc.ia04.agent.Human;
import fr.utc.ia04.simulation.Beings;
import fr.utc.ia04.simulation.SimulationConstants;

public class EatHumanBehaviour extends ProximityBehaviour {

	protected Human other;
	protected double chanceToGetVampire;
	
	public EatHumanBehaviour(Human h, Human o) {
		super(h, SimulationConstants.STATE_EATINGHUM, o, SimulationConstants.DIST_NEAR);
		chanceToGetVampire = Math.random();
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
		h.setEnergy(h.getEnergy()+dt*6.0);			// monte l'énergy du vampire
		
		other.addKnownVampire(h);
		if (this.chanceToGetVampire <= 0.1){
			other.makeVampire();
		}
		
	}

	@Override
	public double evalGain() {
		return SimulationConstants.GAIN_MAX;
	}
	
	@Override
	public boolean isDone() {
		return (h.getEnergy() >= SimulationConstants.CHAR_MAX_ENERGY || other.isVampire());
	}

}
