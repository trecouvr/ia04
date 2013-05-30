package fr.utc.ia04.decision;

import fr.utc.ia04.Module;
import fr.utc.ia04.agent.Human;
import fr.utc.ia04.perception.StimulusBag;
import fr.utc.ia04.simulation.Beings;

public abstract class AbstractDecision extends Module {
	
	/*
	 * Constructor
	 */
	public AbstractDecision(Human h) {
		super(h);
	}

	/*
	 * Method
	 */
	public abstract void makeDecision(Beings beings, StimulusBag b);
}