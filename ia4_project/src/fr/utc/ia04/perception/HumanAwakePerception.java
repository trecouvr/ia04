package fr.utc.ia04.perception;

import sim.util.Bag;
import sim.util.Double2D;
import fr.utc.ia04.agent.Agent;
import fr.utc.ia04.agent.FastFood;
import fr.utc.ia04.agent.Hotel;
import fr.utc.ia04.agent.Human;
import fr.utc.ia04.simulation.Beings;
import fr.utc.ia04.simulation.SimulationConstants;

public class HumanAwakePerception extends AbstractPerception {

	public HumanAwakePerception(Human h) {
		super(h);
	}

	@Override
	public StimulusBag makePerception(Beings beings, double dt) {
		
		StimulusBag b = new StimulusBag();
		
		Stimulus s = this.detectNearestMapBorder(beings);
		if( s.getIntensity() > 0.9 )
			b.offer(SimulationConstants.PERC_MAPBORDER, s);
		
		Bag bag = beings.yard.getNeighborsWithinDistance(h.getPosition(), h.getPerceptionSkills());
		for (Object o : bag) {
			if (o instanceof FastFood) {
				b.offer(SimulationConstants.PERC_FASTFOOD, stimulusFromAgentLocation((Agent)o));
			}
			else if (o instanceof Hotel) {
				b.offer(SimulationConstants.PERC_HOTEL, stimulusFromAgentLocation((Agent)o));
			}
		}
		
		return b;
	}
	
	public Stimulus stimulusFromAgentLocation(Agent o) {
		Double2D p = o.getPosition();
		// todo normalize
		double d = h.getPosition().distance(p);
		return new Stimulus(d, o);
	}

}
