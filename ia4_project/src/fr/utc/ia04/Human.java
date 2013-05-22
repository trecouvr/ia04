package fr.utc.ia04;

import sim.engine.SimState;
import sim.util.Int2D;

public class Human extends MySteppable {
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
		// TODO Auto-generated constructor stub
	}

	@Override
	public void step(SimState arg0) {
		// TODO Auto-generated method stub

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

}
