package fr.utc.ia04.behaviour;

import fr.utc.ia04.agent.Human;
import fr.utc.ia04.simulation.Beings;

public class CompositeBehviour extends Behaviour {

	Behaviour b1;
	Behaviour b2;
	boolean firstBeha;
	
	public CompositeBehviour(Human h, Behaviour b1, Behaviour b2) {
		super(h);
		
	}

	@Override
	public boolean preCond() {
		return false;
	}

	@Override
	public void doAction(Beings b, double dt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isDone() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double evalGain() {
		// TODO Auto-generated method stub
		return 0;
	}

}
