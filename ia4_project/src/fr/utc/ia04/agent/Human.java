package fr.utc.ia04.agent;

import java.util.ArrayList;

import fr.utc.ia04.behaviour.Behaviour;
import fr.utc.ia04.behaviour.speBehaviour.DoNothingBehaviour;
import fr.utc.ia04.decision.AbstractDecision;
import fr.utc.ia04.decision.HumanAwakeDecision;
import fr.utc.ia04.decision.VampireDecision;
import fr.utc.ia04.metabolism.AbstractMetabolism;
import fr.utc.ia04.metabolism.HumanMetabolism;
import fr.utc.ia04.metabolism.VampireMetabolism;
import fr.utc.ia04.perception.AbstractPerception;
import fr.utc.ia04.perception.HumanAwakePerception;
import fr.utc.ia04.perception.StimulusBag;
import fr.utc.ia04.perception.VampirePerception;
import fr.utc.ia04.simulation.Beings;
import fr.utc.ia04.simulation.SimulationConstants;

import sim.engine.SimState;
import sim.util.Double2D;

public class Human extends Agent {
	private static final long serialVersionUID = -597396855800601330L;
	
	protected String name;
	
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
	
	protected double globalHealth;
	
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
	private double timeLife;
	
	/*
	 * Liste des vampires connus
	 */
	private ArrayList<Human> knownVampire;
	
	/*
	 * Constructeur
	 */
	public Human(Double2D location, String name) {
		super(location);
		// Modules
		this.metabolism =	new HumanMetabolism(this);
		this.perception =	new HumanAwakePerception(this);
		this.decision =		new HumanAwakeDecision(this);
		this.behaviour =	new DoNothingBehaviour(this);
		
		// Méta
		this.energy = 0.75 * SimulationConstants.CHAR_MAX_ENERGY;
		this.awake = 0.75 * SimulationConstants.CHAR_MAX_AWAKE;
		this.social = 0.75 * SimulationConstants.CHAR_MAX_SOCIAL;
		
		// Characs
		this.isVampire = false;
		this.perceptionSkills = 17.0;
		this.speed = 4.0;
		this.timeLife = 0.0;
		
		this.knownVampire = new ArrayList<Human>();
		
		this.name=name;
	}

	/*
	 * Getters et Setters
	 */
	public double getEnergy() {return energy;}
	public void setEnergy(double energy) {
		this.energy = Math.max(0, Math.min(SimulationConstants.CHAR_MAX_ENERGY, energy));
	}
	public double getAwake() {return awake;}
	public void setAwake(double awake) {
		this.awake = Math.max(0, Math.min(SimulationConstants.CHAR_MAX_AWAKE, awake));
	}
	public double getSocial() {return social;}
	public void setSocial(double social) {
		this.social = Math.max(0, Math.min(SimulationConstants.CHAR_MAX_SOCIAL, social));
	}
	public ArrayList<Human> getKnownVampire(){return this.knownVampire;}
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
	public double getGlobalHealth() {return globalHealth;}
	public void setGlobalHealth(double globalHealth) {this.globalHealth = globalHealth;}
	public double getTimeLife() {return timeLife;}
	public AbstractMetabolism getMetabolism() {return metabolism;}
	public void setMetabolism(AbstractMetabolism metabolism) {this.metabolism = metabolism;}
	public AbstractPerception getPerception() {return perception;}
	public void setPerception(AbstractPerception perception) {this.perception = perception;}
	public AbstractDecision getDecision() {return decision;}
	public void setDecision(AbstractDecision decision) {this.decision = decision;}
	public Behaviour getBehaviour() {return behaviour;}
	public void setBehaviour(Behaviour behaviour) {this.behaviour = behaviour;}

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
	
		
		// Metabolism
		metabolism.doAction(dt);
		
		// Perception
		StimulusBag bs = this.perception.makePerception(b, dt);
		
		// Decision
		this.decision.makeDecision(b, bs);
		
		// Action
		this.behaviour.doAction(b, dt);
		
		// Update Time Life
		this.timeLife += dt;
		
		
		if(this.globalHealth <=0)
			die((Beings)arg0);
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
	 * 		La position dont on veut que l'agent s'approche
	 * à une distance de nearDist.
	 * @param nearDist
	 * 		La distance à laquelle l'on souhaite se rapprocher
	 * 
	 * @return
	 * 		La distance réellement parcourue.
	 */
	public double move(Beings b, double dt, Double2D pos, double nearDist){
		Double2D move = pos.subtract(position);
		double l = move.length();
		
		if( l < nearDist )
			return 0.0;
		else{
			double lMax = dt*this.getSpeed();
			
			if(l > lMax)
				move = move.multiply(lMax/l);
			else
				move = move.multiply((l-nearDist)/l);
			
			Double2D newLocation = this.getPosition().add(move);
			this.setPosition(b, newLocation);
			
			return move.length();
		}
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
		this.social=SimulationConstants.CHAR_MAX_SOCIAL;
		this.awake=SimulationConstants.CHAR_MAX_AWAKE;
		this.prioCoefSocial = 0.0;
		this.prioCoefAwake = 0.0;
		this.decision = new VampireDecision(this);
		this.perception = new VampirePerception(this);
	}
	
	//toString method to display the labelled Portrayal of Human Agent
	public String toString(){
		//String label = "" + (int)(this.globalHealth*100);
		String label;
		if (this.isVampire){
			label= ((SimulationConstants.DISP_SHOW_NAMES)?this.name:"") + " (" + (int)(this.globalHealth*100) + ")";
		}
		else{
			label= ((SimulationConstants.DISP_SHOW_NAMES)?this.name:"") + " (" + (int)(this.globalHealth*100) + ") |"+this.knownVampire.size();
		}
		
		return label;
	}
	
	public double distance(Agent o) {
		Double2D p = o.getPosition();
		// todo normalize
		return position.distance(p);
	}
	
	public void addKnownVampire (Human vampireToAdd) {
		
		if (this.knowThisVampire(vampireToAdd)){
			return;
		}
		else {
			if (vampireToAdd.isVampire){
				this.knownVampire.add(vampireToAdd);
			}
		}
			
		
	}
	
	public Human pickARandomKnownVampire(){
		int nb = this.knownVampire.size();
		int randomIndex = (int)(Math.random() * (nb-1));
		
		return this.knownVampire.get(randomIndex);
	}
	
	
	
	public boolean knowSomeone(){
		if (this.knownVampire != null && this.knownVampire.size()>=1){
			return true;
		}
		return false;
	}
	
	public boolean knowThisVampire (Human vampire){
		if (this.knownVampire.contains(vampire)){
			return true;
		}
		else{
			return false;
		}
	}

}
