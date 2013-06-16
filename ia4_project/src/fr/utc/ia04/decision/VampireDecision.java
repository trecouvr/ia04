package fr.utc.ia04.decision;

import fr.utc.ia04.agent.Agent;
import fr.utc.ia04.agent.Human;
import fr.utc.ia04.behaviour.Behaviour;
import fr.utc.ia04.behaviour.CompositeBehaviour;
import fr.utc.ia04.behaviour.EatHumanBehaviour;
import fr.utc.ia04.behaviour.speBehaviour.WalkInDirectionBehaviour;
import fr.utc.ia04.behaviour.speBehaviour.WalkNearAgentBehaviour;
import fr.utc.ia04.perception.Stimulus;
import fr.utc.ia04.perception.StimulusBag;
import fr.utc.ia04.simulation.Beings;
import fr.utc.ia04.simulation.SimulationConstants;

public class VampireDecision extends AbstractDecision {

	public VampireDecision(Human h) {
		super(h);
		// TODO Auto-generated constructor stub
	}

	public void makeDecision(Beings beings, StimulusBag b) {
		
		String hightCat = b.getCategoryOfHightStimulus();
		Stimulus s = b.poll(hightCat);
		Behaviour newBeh = null;
		boolean changeBeh;
		changeBeh = (!h.getBehaviour().preCond() || h.getBehaviour().isDone());
		
		// Check if agent is doing Nothing and See nothing
		if(h.getBehaviour().getId().equalsIgnoreCase(SimulationConstants.STATE_DONOTHING) 
				&& hightCat == null) {
			newBeh = new WalkInDirectionBehaviour(
							this.h, 
							beings.random.nextDouble()*Math.PI*2);
		}
		else{
			while( hightCat != null 
					&& ( newBeh==null || newBeh.isDone() ) 
					&& ( changeBeh || h.getBehaviour().evalGain() + 0.01 < s.getIntensity() ) ){
				
				Double experimentedGain = null;
				
				// Choice process
				if( hightCat.equals( SimulationConstants.PERC_MAPBORDER )){			// Detect MapBorder
					//System.out.println("fuit le bord");
					newBeh = new WalkInDirectionBehaviour(this.h, ((Double)s.getSource()) + Math.PI );
				}
				else if( hightCat.equals( SimulationConstants.PERC_HUMAN)){			// Energy need
					//System.out.println("On va bouffer de l'humain !");
					newBeh = new CompositeBehaviour(h,
								new WalkNearAgentBehaviour(h, (Agent)s.getSource(), SimulationConstants.DIST_NEAR),
								new EatHumanBehaviour(h, (Human)s.getSource()));
					experimentedGain = this.getExperimentedGain(newBeh);
				}
				else{
					//System.out.println("on va au hasard");
					newBeh = new WalkInDirectionBehaviour(this.h, beings.random.nextDouble()*Math.PI*2);	// Default Behaviour
				}
				
				/*if( experimentedGain != null && experimentedGain < 0 )
					newBeh = null;*/
				
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
