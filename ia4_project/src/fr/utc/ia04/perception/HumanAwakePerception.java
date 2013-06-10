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
			
			if(!o.equals(h)){
			
				if (o instanceof Human) {
				//TO DO : difference human/vampire
				
					if (h.knowThisVampire((Human)o)){
						b.offer(SimulationConstants.PERC_VAMPIRE, new Stimulus(this.distanceIntensity((Human)o) * -1 , o));
					}
					else{
						b.offer(SimulationConstants.PERC_AGENT, new Stimulus( ( this.distanceIntensity((Human)o) + h.getPrioCoefSocial() ) / 2.0, o) );
					}
					
				}
				else if (o instanceof FastFood) {
					b.offer(SimulationConstants.PERC_FASTFOOD, new Stimulus( ( this.distanceIntensity((FastFood)o) + h.getPrioCoefEnergy() ) / 2.0, o) );
				}
				else if (o instanceof Hotel) {
					b.offer(SimulationConstants.PERC_HOTEL, new Stimulus( ( this.distanceIntensity((Hotel)o) + h.getPrioCoefAwake() ) / 2.0, o) );
				}
			
			}
			
		}
		
		return b;
	}

}
