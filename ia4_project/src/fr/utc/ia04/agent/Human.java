package fr.utc.ia04.agent;

import java.util.ArrayList;

import fr.utc.ia04.decision.AbstractDecision;
import fr.utc.ia04.metabolism.HumanMetabolism;
import fr.utc.ia04.metabolism.VampireMetabolism;
import fr.utc.ia04.perception.AbstractPerception;
import fr.utc.ia04.simulation.Beings;

import sim.engine.SimState;
import sim.util.Int2D;

public class Human extends Agent {
	private static final long serialVersionUID = -597396855800601330L;

	// Jauges
	protected float energy=50;
	protected float awake=50; // fatigue
	protected float social=50;
	
	
	
	public float getEnergy() {
		return energy;
	}

	public void setEnergy(float energy) {
		this.energy = energy;
	}

	public float getAwake() {
		return awake;
	}

	public void setAwake(float awake) {
		this.awake = awake;
	}

	public float getSocial() {
		return social;
	}

	public void setSocial(float social) {
		this.social = social;
	}

	public boolean isVampire() {
		return isVampire;
	}

	public ArrayList<Object> getObjectInSight() {
		return objectInSight;
	}

	public int getPerceptionSkills() {
		return perceptionSkills;
	}



	protected int perceptionSkills=10;
	
	protected boolean isVampire=false;
	
	protected ArrayList<Object> objectInSight = new ArrayList<Object>();
	
	public Human(Beings b, Int2D location) {
		super(b, location);
		this.metabolism =	new HumanMetabolism(this);
		this.perception =	new AbstractPerception(this);
		this.decision =		new AbstractDecision(this);
	}

	@Override
	public void step(SimState arg0) {
		super.step(arg0);
		if(awake==0 || energy==0 || social==0)
			die((Beings)arg0);
	}
	
	public void makeVampire() {
		isVampire = true;
		this.metabolism = new VampireMetabolism(this);
		this.perception = new AbstractPerception(this);
	}
}
