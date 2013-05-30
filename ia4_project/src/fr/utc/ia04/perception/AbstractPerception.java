package fr.utc.ia04.perception;

import fr.utc.ia04.Module;
import fr.utc.ia04.agent.Human;
import fr.utc.ia04.simulation.Beings;

public abstract class AbstractPerception extends Module {

	/*
	 * Constructor
	 */
	public AbstractPerception(Human h) {
		super(h);
	}

	/*
	 * Method
	 */
	public abstract StimulusBag doAction(Beings beings, double dt);
}
