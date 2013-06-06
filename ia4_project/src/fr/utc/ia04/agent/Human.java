package fr.utc.ia04.agent;

import fr.utc.ia04.behaviour.Behaviour;
import fr.utc.ia04.behaviour.DoNothingBehaviour;
import fr.utc.ia04.decision.AbstractDecision;
import fr.utc.ia04.decision.HumanAwakeDecision;
import fr.utc.ia04.metabolism.AbstractMetabolism;
import fr.utc.ia04.metabolism.HumanMetabolism;
import fr.utc.ia04.metabolism.VampireMetabolism;
import fr.utc.ia04.perception.AbstractPerception;
import fr.utc.ia04.perception.HumanAwakePerception;
import fr.utc.ia04.perception.StimulusBag;
import fr.utc.ia04.simulation.Beings;
import fr.utc.ia04.simulation.SimulationConstants;

import sim.engine.SimState;
import sim.util.Double2D;

public class Human extends Agent {
	private static final long serialVersionUID = -597396855800601330L;
	
	/*
	 * Modules
	 */
	protected AbstractMetabolism	metabolism;
	protected AbstractPerception	perception;
	protected AbstractDecision		decision;
	protected Behaviour				behaviour;

	/*
	 * Charactéristiques du Métabolisme
	 */
	// Bases
	protected double energy;
	protected double awake;
	protected double social;
	// Dérivées
	protected double prioCoefEnergy;
	protected double prioCoefAwake;
	protected double prioCoefSocial;
	
	/*
	 * Charactéristiques de la Perception
	 */
	// Bases
	private double perceptionSkills;
	
	/*
	 * Charactéristiques générales
	 */
	private double speed; // km/h
	private boolean isVampire;
	
	/*
	 * Constructeur
	 */
	public Human(Double2D location) {
		super(location);
		// Modules
		this.metabolism =	new HumanMetabolism(this);
		this.perception =	new HumanAwakePerception(this);
		this.decision =		new HumanAwakeDecision(this);
		this.behaviour =	new DoNothingBehaviour(this);
		
		// Characs
		this.isVampire = false;
		this.speed = 4.0;
	}

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
	public double getEnergy() {return energy;}
	public void setEnergy(double energy) {this.energy = energy;}
	public double getAwake() {return awake;}
	public void setAwake(double awake) {this.awake = awake;}
	public double getSocial() {return social;}
	public void setSocial(double social) {this.social = social;}
	public double getPrioCoefEnergy() {return prioCoefEnergy;}
	public void setPrioCoefEnergy(double prioCoefEnergy) {this.prioCoefEnergy = prioCoefEnergy;}
	public double getPrioCoefAwake() {return prioCoefAwake;}
	public void setPrioCoefAwake(double prioCoefAwake) {this.prioCoefAwake = prioCoefAwake;}
	public double getPrioCoefSocial() {return prioCoefSocial;}
	public void setPrioCoefSocial(double prioCoefSocial) {this.prioCoefSocial = prioCoefSocial;}
	public boolean isVampire() {return isVampire;}
	public double getPerceptionSkills() {return perceptionSkills;}
	public double getSpeed() {return speed;}
	public void setSpeed(double speed) {this.speed = speed;}

	/*
	 * Step Method
	 * 
	 * (non-Javadoc)
	 * @see fr.utc.ia04.agent.Agent#step(sim.engine.SimState)
	 */
	@Override
	public void step(SimState arg0) {
		super.step(arg0);
		Beings b = (Beings) arg0;
		
		double dt = SimulationConstants.ENV_DT;
		
		// Death Conditions
		//if(awake<=0 || energy<=0 || social<=0)
			//die((Beings)arg0);
		
		// Metabolism
		metabolism.doAction(dt);
		
		// Perception
		StimulusBag bs = this.perception.makePerception(b, dt);
		
		// Decision
		this.decision.makeDecision(b, bs);
		
		// Action
		this.behaviour.doAction(b, dt);
		
	}
	
	/*
	 * Basic Action
	 */

	/**
	 * Fait se faire déplacer l'humain vers un certain point ou
	 * sur un certain point s'il est suffisament proche.
	 * 
	 * @param b
	 * 		L'état courant de la simulation
	 * @param dt
	 * 		Le temps pendant lequel exécuter l'action en heure.
	 * @param pos
	 * 		La position dont on veut que l'agent s'apporche
	 * à une distance de DIST_NEAR.
	 * 
	 * @return
	 * 		La distance réellement parcourue.
	 */
	public double move(Beings b, double dt, Double2D pos){
		Double2D move = pos.subtract(position);
		double l = move.length();
		double lMax = dt*this.getSpeed();
		
		if(l > lMax)
			move = move.multiply(lMax/l);
		else
			move = move.multiply((l-SimulationConstants.DIST_NEAR)/l);
		
		Double2D newLocation = this.getPosition().add(move);
		this.setPosition(b, newLocation);
		
		return move.length();
	}
	
	/**
	 * Fait se faire déplacer l'humain dans une certaine direction 
	 * 
	 * @param b
	 * 		L'état courant de la simulation.
	 * @param dt
	 * 		Le temps pendant lequel exécuter l'action en heure.
	 * @param direction
	 * 		La direction en radian.
	 * 
	 * @return
	 * 		La distance réellement parcourue.
	 */
	public double move(Beings b, double dt, double direction){
		Double2D move = new Double2D(	dt*this.getSpeed()*Math.cos(direction),
										dt*this.getSpeed()*Math.sin(direction));
		this.setPosition(b, this.getPosition().add(move));
		
		return move.length();
	}
	
	public void makeVampire() {
		isVampire = true;
		this.metabolism = new VampireMetabolism(this);
		//this.perception = new AbstractPerception(this);
	}
	
	//toString method to display the labelled Portrayal of Human Agent
	public String toString(){
		String label = String.format("%d | %d | %d", (int)this.energy, (int)this.awake, (int)this.social);
		return label;
	}
}
