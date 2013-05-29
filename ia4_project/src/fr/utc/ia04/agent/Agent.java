package fr.utc.ia04.agent;

import fr.utc.ia04.behaviour.Behaviour;
import fr.utc.ia04.decision.AbstractDecision;
import fr.utc.ia04.metabolism.AbstractMetabolism;
import fr.utc.ia04.perception.AbstractPerception;
import fr.utc.ia04.simulation.Beings;
import sim.engine.SimState;
import sim.engine.Steppable;
import sim.engine.Stoppable;
import sim.util.Int2D;

public abstract class Agent implements Steppable {
	private static final long serialVersionUID = 7389923094761696870L;
	
	/*
	 * Attribut Utilitaire
	 */
	public Stoppable stoppable;
	
	/*
	 * Modules
	 */
	protected AbstractMetabolism	metabolism;
	protected AbstractPerception	perception;
	protected AbstractDecision		decision;
	
	/*
	 * Variables Simulations
	 */
	protected Int2D location;
	protected Behaviour behaviour;
	
	/*
	 * Getters et Setters
	 */
	public AbstractMetabolism getMetabolism() {return metabolism;}
	public void setMetabolism(AbstractMetabolism metabolism) {this.metabolism = metabolism;}
	public AbstractPerception getPerception() {return perception;}
	public void setPerception(AbstractPerception perception) {this.perception = perception;}
	public AbstractDecision getDecision() {return decision;}
	public void setDecision(AbstractDecision decision) {this.decision = decision;}
	public Behaviour getBehaviour() {return behaviour;}
	public void setBehaviour(Behaviour behaviour) {this.behaviour = behaviour;}
	public Int2D getLocation() {return location;}
	public void setLocation(Int2D location) {this.location = location;}
	
	/*
	 * Méthodes
	 */
	
	public Agent(Beings b, Int2D location) {
		super();
		this.location = location;
		//this.modules = new ArrayList<Module>();
		System.out.println("constructor");
	}

	public void step(SimState arg0) {
		//for (Module m : modules) m.preAction((Beings)arg0);
		//behaviour.doAction(); // faire l'action de ce tour
		//for (Module m : modules) m.postAction((Beings)arg0);
	}

	public String toString(){
		return this.getClass().getName();
	}
	
	/*
	 * Actions
	 */
	
	protected void die(Beings beings) {
		beings.yard.set(location.x,location.y, null);
		stoppable.stop();
	}
	
}
