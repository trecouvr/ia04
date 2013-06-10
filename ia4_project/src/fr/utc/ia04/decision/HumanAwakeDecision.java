package fr.utc.ia04.decision;

import fr.utc.ia04.agent.Agent;
import fr.utc.ia04.agent.Human;
import fr.utc.ia04.behaviour.WalkInDirectionBehaviour;
import fr.utc.ia04.behaviour.WalkNearAgentBehaviour;
import fr.utc.ia04.perception.Stimulus;
import fr.utc.ia04.perception.StimulusBag;
import fr.utc.ia04.simulation.Beings;
import fr.utc.ia04.simulation.SimulationConstants;

public class HumanAwakeDecision extends AbstractDecision {

	public HumanAwakeDecision(Human h) {
		super(h);
	}

	@Override
	public void makeDecision(Beings beings, StimulusBag b) {
		
		String hightCat = b.getCategoryOfHightStimulus();
		Stimulus s = b.poll(hightCat);
		
		if(h.getBehaviour().getId().equalsIgnoreCase(SimulationConstants.STATE_DONOTHING) && hightCat == null)
			h.setBehaviour(new WalkInDirectionBehaviour(this.h, beings.random.nextDouble()*Math.PI*2));
		else if( hightCat != null && h.getBehaviour().evalGain() + 0.1 < s.getIntensity() )
			switch(hightCat){
			
			case SimulationConstants.PERC_MAPBORDER:
				h.setBehaviour(new WalkInDirectionBehaviour(this.h, ((Double)s.getSource()) + Math.PI ));
				break;
				
			case SimulationConstants.PERC_AGENT:
				h.setBehaviour(new WalkNearAgentBehaviour(h, (Agent)s.getSource(), SimulationConstants.DIST_NEAR));
				break;
				
			case SimulationConstants.PERC_FASTFOOD:
				h.setBehaviour(new WalkNearAgentBehaviour(h, (Agent)s.getSource(), SimulationConstants.DIST_NEAR));
				break;
				
			case SimulationConstants.PERC_HOTEL:
				h.setBehaviour(new WalkNearAgentBehaviour(h, (Agent)s.getSource(), SimulationConstants.DIST_NEAR));
				break;
				
			default:
				h.setBehaviour(new WalkInDirectionBehaviour(this.h, beings.random.nextDouble()*Math.PI*2));
				break;
				
			}
		
	}

}
