package fr.utc.ia04;

public abstract class Module {
	Agent agent;
	
	public Module(Agent a){
		agent=a;
	}
	
	// méthode appelée avant que l’agent est fait son action pour ce tour
	public abstract void preAction(Beings beings);
	// méthode appelée après que l’agent est effectué son action pour ce tour
	public abstract void postAction(Beings beings);
	
}
