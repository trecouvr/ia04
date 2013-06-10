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
		h.setEnergy(h.getEnergy() -	SimulationConstants.METABOLISM_COEF_ENERGY*dt);
		h.setAwake(h.getAwake() -	SimulationConstants.METABOLISM_COEF_AWAKE*dt);
		h.setSocial(h.getSocial() -	SimulationConstants.METABOLISM_COEF_SOCIAL*dt);
		
		// Priotity Part
		h.setPrioCoefEnergy(1-h.getEnergy()/SimulationConstants.CHAR_MAX_ENERGY);
		h.setPrioCoefAwake(1-h.getAwake()/SimulationConstants.CHAR_MAX_AWAKE);
		h.setPrioCoefSocial(1-h.getSocial()/SimulationConstants.CHAR_MAX_SOCIAL);
		
		setGlobalHealth();
	}

}
