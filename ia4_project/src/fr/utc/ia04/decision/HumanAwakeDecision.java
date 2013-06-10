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
			this.changeBehaviour(new WalkInDirectionBehaviour(this.h, beings.random.nextDouble()*Math.PI*2));
		
		else if( hightCat != null && h.getBehaviour().evalGain() + 0.1 < s.getIntensity() )
			if( hightCat.equals( SimulationConstants.PERC_MAPBORDER )){
				this.changeBehaviour(new WalkInDirectionBehaviour(this.h, ((Double)s.getSource()) + Math.PI ));
			}
			else if( hightCat.equals( SimulationConstants.PERC_AGENT)){
				this.changeBehaviour(new WalkNearAgentBehaviour(h, (Agent)s.getSource(), SimulationConstants.DIST_NEAR));
			}
			else if( hightCat.equals( SimulationConstants.PERC_FASTFOOD)){
				this.changeBehaviour(new WalkNearAgentBehaviour(h, (Agent)s.getSource(), SimulationConstants.DIST_NEAR));
			}
			else if( hightCat.equals( SimulationConstants.PERC_HOTEL)){
				this.changeBehaviour(new WalkNearAgentBehaviour(h, (Agent)s.getSource(), SimulationConstants.DIST_NEAR));
			}
			else{
				this.changeBehaviour(new WalkInDirectionBehaviour(this.h, beings.random.nextDouble()*Math.PI*2));
			}
		
	}

}
