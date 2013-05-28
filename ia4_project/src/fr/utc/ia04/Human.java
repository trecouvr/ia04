package fr.utc.ia04;

import java.util.ArrayList;

import sim.engine.SimState;
import sim.util.Int2D;

public class Human extends Agent {
	private static final long serialVersionUID = -597396855800601330L;

	// Jauges
	protected float energy=50;
	protected float awake=50; // fatigue
	protected float social=50;
	
	protected int perception=10;
	
	protected boolean isVampire=false;
	
	protected ArrayList<Object> objectInSight = new ArrayList<Object>();
	
	public Human(Int2D location) {
		super(location);
		this.modules.add(new MetabolismeHuman(this));
		this.modules.add(new Perception(this));
		this.modules.add(new Decision(this));
	}

	@Override
	public void step(SimState arg0) {
		super.step(arg0);
		if(awake==0 || energy==0 || social==0)
			meurt((Beings)arg0);
	}
	
	public void makeVampire() {
		isVampire = true;
		modules.clear();
		modules.add(new Perception(this));
		modules.add(new MetabolismeVampire(this));
	}
}
