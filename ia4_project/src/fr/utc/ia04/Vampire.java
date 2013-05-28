package fr.utc.ia04;

import sim.engine.SimState;
import sim.util.Int2D;

public class Vampire extends Agent {
	private static final long serialVersionUID = -3640169757194966570L;
	
	/*
	 * Give me more food
	 */
	protected double foodEnergy;
	
	public Vampire(Int2D location) {
		super(location);
		// TODO Auto-generated constructor stub
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

	@Override
	protected void doAction() {
		// TODO Auto-generated method stub
		
	}

}
