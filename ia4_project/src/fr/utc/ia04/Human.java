package fr.utc.ia04;

import sim.engine.SimState;
import sim.util.Int2D;

public class Human extends Agent {
	private static final long serialVersionUID = -597396855800601330L;
	
	/*
	 * Give me more food
	 */
	protected double foodEnergy;
	
	/*
	 * Sleep more
	 */
	protected double sleepEnergy;
	
	/*
	 * Speak with others
	 */
	protected double socialEnergy;
	
	
	public Human(Int2D location) {
		super(location);
		this.modules.add(new Metabolisme(this));
		this.modules.add(new Perception(this));
		this.modules.add(new Decision(this));
	}

	@Override
	public void step(SimState arg0) {
		super.step(arg0);
	}

	public double getFoodEnergy() {
		return foodEnergy;
	}

	public void setFoodEnergy(double foodEnergy) {
		this.foodEnergy = foodEnergy;
	}

	public double getSleepEnergy() {
		return sleepEnergy;
	}

	public void setSleepEnergy(double sleepEnergy) {
		this.sleepEnergy = sleepEnergy;
	}

	public double getSocialEnergy() {
		return socialEnergy;
	}

	public void setSocialEnergy(double socialEnergy) {
		this.socialEnergy = socialEnergy;
	}

	@Override
	protected void doAction() {
		// TODO Auto-generated method stub
		
	}

}
