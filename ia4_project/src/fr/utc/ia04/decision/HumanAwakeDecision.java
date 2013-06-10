package fr.utc.ia04.decision;

import fr.utc.ia04.agent.Agent;
import fr.utc.ia04.agent.FastFood;
import fr.utc.ia04.agent.Hotel;
import fr.utc.ia04.agent.Human;
import fr.utc.ia04.behaviour.CompositeBehaviour;
import fr.utc.ia04.behaviour.DoNothingBehaviour;
import fr.utc.ia04.behaviour.EatBehaviour;
import fr.utc.ia04.behaviour.SleepBehaviour;
import fr.utc.ia04.behaviour.SpeakBehaviour;
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
		
		// Check if Behaviour is Applicable
		if( !h.getBehaviour().preCond() )
			this.changeBehaviour(new DoNothingBehaviour(h));

		// Check if Behaviour is already Done
		if( h.getBehaviour().isDone() )
			this.changeBehaviour(new DoNothingBehaviour(h));
		
		// Check if agent is doing Nothing and See nothing
		if(h.getBehaviour().getId().equalsIgnoreCase(SimulationConstants.STATE_DONOTHING) && hightCat == null)
			this.changeBehaviour(new WalkInDirectionBehaviour(this.h, beings.random.nextDouble()*Math.PI*2));
		
		else if( hightCat != null && h.getBehaviour().evalGain() + 0.1 < s.getIntensity() )
			if( hightCat.equals( SimulationConstants.PERC_MAPBORDER )){		// Detect MapBorder
				this.changeBehaviour(new WalkInDirectionBehaviour(this.h, ((Double)s.getSource()) + Math.PI ));
			}
			else if( hightCat.equals( SimulationConstants.PERC_HUMAN)){		// Social need
				this.changeBehaviour(new CompositeBehaviour(h,
						new WalkNearAgentBehaviour(h, (Human)s.getSource(), SimulationConstants.DIST_NEAR),
						new SpeakBehaviour(h, (Human)s.getSource()))
				);
			}
			else if( hightCat.equals( SimulationConstants.PERC_FASTFOOD)){	// Energy need
				this.changeBehaviour(new CompositeBehaviour(h,
						new WalkNearAgentBehaviour(h, (Agent)s.getSource(), SimulationConstants.DIST_NEAR),
						new EatBehaviour(h, (FastFood)s.getSource()))
				);
			}
			else if( hightCat.equals( SimulationConstants.PERC_HOTEL)){		// Awake need
				this.changeBehaviour(new CompositeBehaviour(h,
						new WalkNearAgentBehaviour(h, (Hotel)s.getSource(), SimulationConstants.DIST_NEAR),
						new SleepBehaviour(h, (Hotel)s.getSource()))
				);
			}
			else{
				this.changeBehaviour(new WalkInDirectionBehaviour(this.h, beings.random.nextDouble()*Math.PI*2));
			}
		
	}

}
