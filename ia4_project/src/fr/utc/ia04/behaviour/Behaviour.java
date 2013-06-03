package fr.utc.ia04.behaviour;

import fr.utc.ia04.agent.Human;
import fr.utc.ia04.simulation.Beings;

public abstract class Behaviour {
	protected String id;
	protected Human h;

	/*
	 * Abstract Methods
	 */
	public abstract boolean preCond();
	public abstract void doAction(Beings b, double dt);
	public abstract boolean isDone();
	public abstract double evalGain();
	
	/*
	 * Constructor
	 */
	public Behaviour(Human h, String id) {
		super();
		this.h = h;
		this.id = id;
	}
	
	/*
	 * Getter
	 */
	public String getId() {return id;}
	
	
}
