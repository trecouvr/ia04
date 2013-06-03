package fr.utc.ia04.decision;

import fr.utc.ia04.agent.Human;
import fr.utc.ia04.behaviour.WalkInDirectionBehaviour;
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
		
		if(h.getBehaviour().getId().equalsIgnoreCase(SimulationConstants.STATE_DONOTHING)){
			h.setBehaviour(new WalkInDirectionBehaviour(this.h, beings.random.nextDouble()*Math.PI*2));
		}
			
		Stimulus s = b.poll(SimulationConstants.PERC_MAPBORDER);
		if( s!=null ){
			h.setBehaviour(new WalkInDirectionBehaviour(this.h, ((Double)s.getSource()) + Math.PI ));
		}
	}

}
