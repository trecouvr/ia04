package fr.utc.ia04.metabolism;

import fr.utc.ia04.agent.Human;

public class HumanMetabolism extends AbstractMetabolism {

	public HumanMetabolism(Human a) {
		super(a);
	}

	@Override
	public void doAction(double dt) {
		// Metabolism Part
		h.setEnergy(	h.getEnergy() -	10*dt);
		h.setAwake(		h.getAwake() -	3.125*dt);
		h.setSocial(	h.getSocial() -	2*dt);
		
		// Priotity Part
		h.setPrioCoefEnergy(	2*h.getEnergy()/100 );
		h.setPrioCoefAwake(		2*h.getAwake()/100 );
		h.setPrioCoefSocial(	2*h.getSocial()/100 );
		
		setGlobalHealth();
	}

}
