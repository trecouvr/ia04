package fr.utc.ia04.decision;

import fr.utc.ia04.agent.Human;
import fr.utc.ia04.behaviour.DoNothingBehaviour;
import fr.utc.ia04.behaviour.WalkInDirectionBehaviour;
import fr.utc.ia04.perception.StimulusBag;
import fr.utc.ia04.simulation.Beings;

public class HumanAwakeDecision extends AbstractDecision {

	public HumanAwakeDecision(Human h) {
		super(h);
	}

	@Override
	public void makeDecision(Beings beings, StimulusBag b) {
		
		if(h.getBehaviour().getClass().equals(DoNothingBehaviour.class));
			h.setBehaviour(new WalkInDirectionBehaviour(this.h, beings.random.nextDouble()*Math.PI*2));
	}

}
