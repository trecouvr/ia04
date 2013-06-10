package fr.utc.ia04.metabolism;

import fr.utc.ia04.agent.Human;

public class VampireMetabolism extends AbstractMetabolism {

	public VampireMetabolism(Human h) {
		super(h);
	}
	
	@Override
	public void doAction(double dt) {
		// Metabolism Part
		h.setEnergy(h.getEnergy() -	0.7*dt);
		
		// Priotity Part
		h.setPrioCoefEnergy(2*h.getEnergy()/100 );
		
		setGlobalHealth();
	}

}
