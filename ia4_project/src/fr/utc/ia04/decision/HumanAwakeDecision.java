package fr.utc.ia04.decision;

import fr.utc.ia04.agent.Agent;
import fr.utc.ia04.agent.FastFood;
import fr.utc.ia04.agent.Hotel;
import fr.utc.ia04.agent.Human;
import fr.utc.ia04.behaviour.AttakBehaviour;
import fr.utc.ia04.behaviour.Behaviour;
import fr.utc.ia04.behaviour.CompositeBehaviour;
import fr.utc.ia04.behaviour.speBehaviour.EatBehaviour;
import fr.utc.ia04.behaviour.speBehaviour.RunAwayBehaviour;
import fr.utc.ia04.behaviour.speBehaviour.SleepBehaviour;
import fr.utc.ia04.behaviour.speBehaviour.SpeakBehaviour;
import fr.utc.ia04.behaviour.speBehaviour.WalkInDirectionBehaviour;
import fr.utc.ia04.behaviour.speBehaviour.WalkNearAgentBehaviour;
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
		boolean changeBeh = false;
		Behaviour newBeh = null;
		
		
		if( 	
				!h.getBehaviour().preCond() ||		// Check if Behaviour is Applicable
				h.getBehaviour().isDone()			// Check if Behaviour is already Done
		  )
			changeBeh = true;
		
		// Check if agent is doing Nothing and See nothing
		if(h.getBehaviour().getId().equalsIgnoreCase(SimulationConstants.STATE_DONOTHING) && hightCat == null)
			newBeh = new WalkInDirectionBehaviour(this.h, beings.random.nextDouble()*Math.PI*2);
		
		else{
			
			while( hightCat != null && ( newBeh==null || newBeh.isDone() ) && ( changeBeh || h.getBehaviour().evalGain() + 0.05 < s.getIntensity() ) ){
				
				// Choice process
				if( hightCat.equals( SimulationConstants.PERC_MAPBORDER )){			// Detect MapBorder
					newBeh = new WalkInDirectionBehaviour(this.h, ((Double)s.getSource()) + Math.PI );
				}
				else if( hightCat.equals( SimulationConstants.PERC_HUMAN)){			// Social need
					newBeh = new CompositeBehaviour(h,new WalkNearAgentBehaviour(h, (Human)s.getSource(), SimulationConstants.DIST_NEAR),new SpeakBehaviour(h, (Human)s.getSource()));
				}
				else if( hightCat.equals( SimulationConstants.PERC_FASTFOOD)){		// Energy need
					newBeh = new CompositeBehaviour(h,new WalkNearAgentBehaviour(h, (Agent)s.getSource(), SimulationConstants.DIST_NEAR),new EatBehaviour(h, (FastFood)s.getSource()));
				}
				else if( hightCat.equals( SimulationConstants.PERC_HOTEL)){			// Awake need
					newBeh = new CompositeBehaviour(h,new WalkNearAgentBehaviour(h, (Hotel)s.getSource(), SimulationConstants.DIST_NEAR),new SleepBehaviour(h, (Hotel)s.getSource()));
				}
				else if( hightCat.equals( SimulationConstants.PERC_VAMPIRE)){		// Vampire risk
					Human vampire = (Human)s.getSource();
					int nbHumans = 0;
					for (Agent a : h.getPerception().agentsInRange) {
						if (a instanceof Human) {
							if (((Human)a).knowThisVampire(vampire)) {
								++nbHumans;
							}
						}
					}
					if (nbHumans < SimulationConstants.HUMAN_MIN_NUMBER_TO_ATTK) {
						newBeh = new RunAwayBehaviour(h, (Agent)s.getSource());
					}
					else {
						newBeh = new CompositeBehaviour(h, 
							new WalkNearAgentBehaviour(h, (Agent)s.getSource(), SimulationConstants.DIST_NEAR),
							new AttakBehaviour(h, (Human)s.getSource())
						);
					}
				}
				else{
					newBeh = new WalkInDirectionBehaviour(this.h, beings.random.nextDouble()*Math.PI*2);	// Default Behaviour
				}
				
				hightCat = b.getCategoryOfHightStimulus();
				s = b.poll(hightCat);
				
			}
			
			if( changeBeh && (newBeh==null || newBeh.isDone()) )
				newBeh = new WalkInDirectionBehaviour(this.h, beings.random.nextDouble()*Math.PI*2);	// Default Behaviour
		
		}
		
		if(newBeh != null)
			this.changeBehaviour(newBeh);		// Change Behaviour
		
	}

}
