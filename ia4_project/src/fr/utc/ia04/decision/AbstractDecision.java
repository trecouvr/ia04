package fr.utc.ia04.decision;

import fr.utc.ia04.Module;
import fr.utc.ia04.agent.Human;
import fr.utc.ia04.behaviour.Behaviour;
import fr.utc.ia04.perception.StimulusBag;
import fr.utc.ia04.simulation.Beings;
import fr.utc.ia04.simulation.SimulationConstants;

public abstract class AbstractDecision extends Module {
	
	/*
	 * Members
	 */
	private double oldGlobalHealth;
	private double oldTimeLife;
	private BehavioursMemory memory;
	
	/*
	 * Constructor
	 */
	public AbstractDecision(Human h) {
		super(h);
		this.oldGlobalHealth = h.getGlobalHealth();
		this.oldTimeLife = h.getTimeLife();
		this.memory = new BehavioursMemory(SimulationConstants.MEM_MIN_EXP, SimulationConstants.MEM_MAX_EXP);
	}

	/*
	 * Method
	 */
	public abstract void makeDecision(Beings beings, StimulusBag b);
	
	/*
	 * Tool Method
	 */
	protected void changeBehaviour(Behaviour b){
		
		if( !b.equals(h.getBehaviour()) ){
			// Memory Procedure
			double gain = (this.h.getGlobalHealth() - this.oldGlobalHealth) / (this.h.getTimeLife() - this.oldTimeLife);
			this.oldGlobalHealth = this.h.getGlobalHealth();
			this.oldTimeLife = this.h.getTimeLife();
			this.memory.addExp(b, gain);
		
			// Change Procedure
			System.out.println("Change " + b);
			h.setBehaviour(b);
		}
	}
	
	protected Double getExperimentedGain(Behaviour b){
		return this.memory.getExperimentedGain(b);
	}
}
