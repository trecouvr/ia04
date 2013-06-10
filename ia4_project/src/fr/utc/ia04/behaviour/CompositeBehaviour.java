package fr.utc.ia04.behaviour;

import fr.utc.ia04.agent.Human;
import fr.utc.ia04.simulation.Beings;

public class CompositeBehaviour extends Behaviour {

	private Behaviour b1;
	private Behaviour b2;
	private boolean firstBeha;
	
	public CompositeBehaviour(Human h, Behaviour b1, Behaviour b2) {
		super(h);
		this.firstBeha = true;
		this.b1 = b1;
		this.b2 = b2;
	}
	
	public String getId(){
		if(firstBeha)
			return b1.getId();
		else
			return b2.getId();
	}

	@Override
	public boolean preCond() {
		return b1.preCond();
	}

	@Override
	public void doAction(Beings b, double dt) {
		if(firstBeha && b1.isDone())
			firstBeha = false;
		
		if(!firstBeha && !b2.preCond())
			firstBeha = true;
		
		if(firstBeha)
			b1.doAction(b, dt);
		else
			b2.doAction(b, dt);
	}

	@Override
	public boolean isDone() {
		return b2.isDone();
	}

	@Override
	public double evalGain() {
		if(firstBeha)
			return b1.evalGain();
		else
			return b2.evalGain();
	}

}
