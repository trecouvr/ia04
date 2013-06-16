package fr.utc.ia04.perception;

import sim.util.Bag;
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
		
		StimulusBag b = new StimulusBag(0.3);
		
		Stimulus s = this.detectNearestMapBorder(beings);
		if( s.getIntensity() > 0.9 )
			b.offer(SimulationConstants.PERC_MAPBORDER, s);
		
		Bag bag = beings.yard.getNeighborsWithinDistance(h.getPosition(), h.getPerceptionSkills());
		for (Object o : bag) {
			
			if(o != h) {
				
				if (o instanceof Human) {
					if (h.knowThisVampire((Human)o)){
						b.offer(SimulationConstants.PERC_VAMPIRE, new Stimulus( this.distanceIntensity((Human)o) , o));
					}	
					else{
						b.offer(SimulationConstants.PERC_HUMAN, new Stimulus( 0.3*this.distanceIntensity((Human)o) + 0.7*h.getPrioCoefSocial(), o) );
					}
				}
				else if (o instanceof FastFood) {
					b.offer(SimulationConstants.PERC_FASTFOOD, new Stimulus( 0.3*this.distanceIntensity((FastFood)o) + 0.7*h.getPrioCoefEnergy(), o) );
				}
				else if (o instanceof Hotel) {
					b.offer(SimulationConstants.PERC_HOTEL, new Stimulus( 0.3*this.distanceIntensity((Hotel)o) + 0.7*h.getPrioCoefAwake(), o) );
				}
			
			}
			
		}
		
		return b;
	}

}
