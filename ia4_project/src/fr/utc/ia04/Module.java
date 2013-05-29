package fr.utc.ia04;

import fr.utc.ia04.agent.Agent;
import fr.utc.ia04.simulation.Beings;

public abstract class Module {
	
	protected Agent agent;
	
	public Module(Agent a){
		agent=a;
	}
	
	// Méthode appelée avant que  l'agent est fait son action pour ce tour
	public abstract void preAction(Beings beings);
	// Méthode appelée après que  l'agent est effectué son action pour ce tour
	public abstract void postAction(Beings beings);
	
}
