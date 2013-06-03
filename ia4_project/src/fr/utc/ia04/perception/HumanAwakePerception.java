package fr.utc.ia04.perception;

import sim.util.Bag;
import fr.utc.ia04.agent.Human;
import fr.utc.ia04.simulation.Beings;

public class HumanAwakePerception extends AbstractPerception {

	public HumanAwakePerception(Human h) {
		super(h);
	}

	@Override
	public StimulusBag makePerception(Beings beings, double dt) {
		
		StimulusBag b = new StimulusBag();
		
		//if(  )
		
		Bag bag = beings.yard.getNeighborsWithinDistance(h.getPosition(), h.getPerceptionSkills());
		for (Object o : bag) {
			// To Do
		}
		
		return b;
	}

}
