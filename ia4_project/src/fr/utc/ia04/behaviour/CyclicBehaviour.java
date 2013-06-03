package fr.utc.ia04.behaviour;

import fr.utc.ia04.agent.Human;

public abstract class CyclicBehaviour extends Behaviour {

	/**
	 * Constructeur Générique
	 * 
	 * @param id
	 * 		Id de l'action en cours (état)
	 */
	public CyclicBehaviour(Human h, String id) {
		super(h, id);
	}

	public boolean isDone(){
		return false;
	}

}
