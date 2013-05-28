package fr.utc.ia04;

public abstract class Module {
	Agent agent;
	
	public Module(Agent a){
		agent=a;
	}
	
	// m�thode appel�e avant que l�agent est fait son action pour ce tour
	public abstract void preAction(Beings beings);
	// m�thode appel�e apr�s que l�agent est effectu� son action pour ce tour
	public abstract void postAction(Beings beings);
	
}
