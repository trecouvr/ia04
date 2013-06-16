package fr.utc.ia04.metabolism;

import fr.utc.ia04.agent.Human;
import fr.utc.ia04.simulation.SimulationConstants;

public class VampireMetabolism extends AbstractMetabolism {

	public VampireMetabolism(Human h) {
		super(h);
	}
	
	@Override
	public void doAction(double dt) {
		// Metabolism Part
		h.setEnergy(h.getEnergy() -	SimulationConstants.METABOLISM_COEF_ENERGY*dt);
		
		// Priotity Part
		h.setPrioCoefEnergy(1-h.getEnergy()/SimulationConstants.CHAR_MAX_ENERGY);
		
		setGlobalHealth();
	}

}
