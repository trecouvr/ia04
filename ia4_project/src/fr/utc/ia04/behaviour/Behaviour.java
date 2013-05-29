package fr.utc.ia04.behaviour;

public abstract class Behaviour {
	protected String id;
	protected float energyCoast;
	protected float tirednessCoast;
	protected float socialCoast;

	public abstract void step();
	public abstract boolean isDone();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public float getEnergyCoast() {
		return energyCoast;
	}

	public void setEnergyCoast(float energyCoast) {
		this.energyCoast = energyCoast;
	}

	public float getTirednessCoast() {
		return tirednessCoast;
	}

	public void setTirednessCoast(float tirednessCoast) {
		this.tirednessCoast = tirednessCoast;
	}

	public float getSocialCoast() {
		return socialCoast;
	}

	public void setSocialCoast(float socialCoast) {
		this.socialCoast = socialCoast;
	}
	
	public abstract void doAction();
}
