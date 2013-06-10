package fr.utc.ia04.metabolism;

import fr.utc.ia04.agent.Human;
import fr.utc.ia04.simulation.SimulationConstants;

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
		h.setPrioCoefEnergy(	1-h.getEnergy()/SimulationConstants.CHAR_MAX_ENERGY );
		h.setPrioCoefAwake(		1-h.getAwake()/SimulationConstants.CHAR_MAX_AWAKE );
		h.setPrioCoefSocial(	1-h.getSocial()/SimulationConstants.CHAR_MAX_SOCIAL );
	}

}
