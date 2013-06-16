package fr.utc.ia04.behaviour.speBehaviour;

import fr.utc.ia04.agent.Human;
import fr.utc.ia04.behaviour.ProximityBehaviour;
import fr.utc.ia04.simulation.Beings;
import fr.utc.ia04.simulation.SimulationConstants;

public class SpeakBehaviour extends ProximityBehaviour {
	protected double chanceToHaveInfo;
	protected Human other = null;
	protected int nbStep = 0 ;
	
	public SpeakBehaviour(Human h, Human other) {
		super(h, SimulationConstants.STATE_SPEAKING, other, SimulationConstants.DIST_INTERACT);
		this.chanceToHaveInfo = Math.random();
		this.other = other;
	}
	
	@Override
	public void doAction(Beings b, double dt) {
		h.setSocial(h.getSocial()+dt*SimulationConstants.CHAR_REG_SOCIAL);
		
		nbStep++;
		
		int probability;
		
		if (nbStep >=10){
			//Si plus de 10 steps à parler, la proba d'échanger des infos est de 1;
			probability = 1;
		}
		else{
			//Si moins de 10 steps à parler, la proba est de step/10 (cad 20% si step 2, 40 si step 4)
			probability = nbStep/10;
		}
		
		
		if (this.chanceToHaveInfo < probability && h.knowSomeone()) //Si l'acteur connaît au moins un vampire et que la proba est respectée alors l'acteur échange des infos
		{
				Human vamp = h.pickARandomKnownVampire();
				this.other.addKnownVampire(vamp);
		}
		
		
		
	}

	@Override
	public double evalGain() {
		return SimulationConstants.GAIN_HIGHT;
		
	}
	
	@Override
	public boolean isDone() {
		return h.getPrioCoefSocial() <= 0.1;
	}

}
