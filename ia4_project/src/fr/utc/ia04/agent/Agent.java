package fr.utc.ia04.agent;

import fr.utc.ia04.behaviour.Behaviour;
import fr.utc.ia04.simulation.Beings;
import sim.engine.SimState;
import sim.engine.Steppable;
import sim.engine.Stoppable;
import sim.util.Double2D;

public abstract class Agent implements Steppable {
	private static final long serialVersionUID = 7389923094761696870L;
	
	/*
	 * Attribut
	 */
	public Stoppable stoppable;
	protected Double2D position;
	protected Behaviour behaviour;
	
	/*
	 * Getters et Setters
	 */
	public Behaviour getBehaviour() {return behaviour;}
	public void setBehaviour(Behaviour behaviour) {this.behaviour = behaviour;}
	public Double2D getPosition() {return position;}
	public void setPosition(Beings b, Double2D location) {b.yard.setObjectLocation(this, location); this.position = location;}
	
	/*
	 * Méthodes
	 */
	
	public Agent(Double2D location) {
		super();
		this.position = location;

		System.out.println(this.getClass().getName() + " created");
	}

	public void step(SimState arg0) {
		// Do Nothing
	}

	public String toString(){
		return this.getClass().getName();
	}
	
	/*
	 * Actions
	 */
	protected void die(Beings beings) {
		beings.yard.removeObjectsAtLocation(this.position);
		stoppable.stop();
	}
	
}
