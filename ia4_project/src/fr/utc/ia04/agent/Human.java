package fr.utc.ia04.agent;

import fr.utc.ia04.decision.AbstractDecision;
import fr.utc.ia04.metabolism.AbstractMetabolism;
import fr.utc.ia04.metabolism.HumanMetabolism;
import fr.utc.ia04.metabolism.VampireMetabolism;
import fr.utc.ia04.perception.AbstractPerception;
import fr.utc.ia04.perception.HumanAwakePerception;
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
	protected double perceptionSkills;
	
	/*
	 * Charactéristiques générales
	 */
	protected boolean isVampire=false;
	
	/*
	 * Constructeur
	 */
	public Human(Beings b, Double2D location) {
		super(b, location);
		this.metabolism =	new HumanMetabolism(this);
		this.perception =	new HumanAwakePerception(this);
		this.decision =		null; // A implémenter
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

	
	@Override
	public void step(SimState arg0) {
		super.step(arg0);
		
		// Death Conditions
		if(awake<=0 || energy<=0 || social<=0)
			die((Beings)arg0);
		
		// Metabolism
		metabolism.doAction(SimulationConstants.ENV_DT);
		
	}
	
	public void makeVampire() {
		isVampire = true;
		this.metabolism = new VampireMetabolism(this);
		//this.perception = new AbstractPerception(this);
	}
}
