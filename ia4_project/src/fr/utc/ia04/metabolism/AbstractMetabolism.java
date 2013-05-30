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

}
