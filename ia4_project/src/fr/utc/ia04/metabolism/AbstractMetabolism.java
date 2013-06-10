package fr.utc.ia04.metabolism;

import fr.utc.ia04.Module;
import fr.utc.ia04.agent.Human;

/**
 * Est responsable du métabolisme d'un agent humain (qui peut très bien être un vampire).
 * Il calcule les nouvelles valeurs des attributs de l'agent pour une durée de temps écoulée.
 * Il calcule aussi les coéficients de priorité pour les trois besoins (energy,awake,social).
 * 
 * @author Frozarmony
 *
 */
public abstract class AbstractMetabolism extends Module {

	/*
	 * Constructor
	 */
	public AbstractMetabolism(Human h) {
		super(h);
	}

	/*
	 * Method
	 */
	abstract public void doAction(double dt);
	
	public void setGlobalHealth(){
		//h.setGlobalHealth((-0.005*(h.getEnergy())+50) + ((h.getAwake())*-0.005+50) + ((h.getSocial())*-0.005+50));
		//h.setGlobalHealth((Math.exp(h.getAwake()*0.05)+Math.exp(h.getEnergy()*0.05)+Math.exp(h.getSocial()*0.05))*100/445);
		//h.setGlobalHealth(100-(((100-h.getEnergy())+(100-h.getAwake())+(100-h.getSocial()))*100/300));
		h.setGlobalHealth(Math.max(0, Math.min(Math.min(h.getEnergy(), h.getAwake()), h.getSocial())));
	}

}

