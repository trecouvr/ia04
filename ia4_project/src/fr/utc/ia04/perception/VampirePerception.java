package fr.utc.ia04.perception;

import sim.util.Bag;
import fr.utc.ia04.agent.Human;
import fr.utc.ia04.simulation.Beings;
import fr.utc.ia04.simulation.SimulationConstants;

public class VampirePerception extends AbstractPerception {

	public VampirePerception(Human h) {
		super(h);
		// TODO Auto-generated constructor stub
	}

	@Override
	public StimulusBag makePerception(Beings beings, double dt) {
		
		StimulusBag b = new StimulusBag(0.0);
		
		Stimulus s = this.detectNearestMapBorder(beings);
		if( s.getIntensity() > 0.9 )
			b.offer(SimulationConstants.PERC_MAPBORDER, s);
		
		Bag bag = beings.yard.getNeighborsWithinDistance(h.getPosition(), h.getPerceptionSkills());
		for (Object o : bag) {
			if(o != h){
				if (o instanceof Human) {
					Human ho = (Human)o;
					if (!ho.isVampire()) {
						b.offer(SimulationConstants.PERC_HUMAN, 
								new Stimulus(
										0.3*this.distanceIntensity(ho) + 0.7*h.getPrioCoefEnergy(),
										o));
					}
				}
			}
		}
		
		return b;
	}


}
