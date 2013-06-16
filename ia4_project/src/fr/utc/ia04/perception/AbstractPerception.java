package fr.utc.ia04.perception;

import java.util.ArrayList;

import sim.util.Bag;
import sim.util.Double2D;
import fr.utc.ia04.Module;
import fr.utc.ia04.agent.Agent;
import fr.utc.ia04.agent.Human;
import fr.utc.ia04.simulation.Beings;
import fr.utc.ia04.simulation.SimulationConstants;

public abstract class AbstractPerception extends Module {
	
	public ArrayList<Agent> agentsInRange = new ArrayList<Agent>();
	
	
	/*
	 * Constructor
	 */
	public AbstractPerception(Human h) {
		super(h);
	}

	/*
	 * Abstract Method
	 */
	public abstract StimulusBag makePerception(Beings beings, double dt);
	
	public void fillAgentsInRange(Beings beings) {
		agentsInRange.clear();
		Bag bag = beings.yard.getNeighborsWithinDistance(h.getPosition(), h.getPerceptionSkills());
		for (Object o : bag) {
			if (o instanceof Agent && o != h) {
				agentsInRange.add((Agent)o);
			}
		}
	}
	
	/*
	 * Method tools
	 */
	public Stimulus detectNearestMapBorder(Beings b){
		double size = SimulationConstants.ENV_SIZE/2;
		Double2D center = new Double2D(size,size);
		Double2D vect = h.getPosition().subtract(center);
		
		// La direction du stimulus
		double dir = vect.angle();
		double l = vect.length();
		
		return new Stimulus(-(l/size)*(l/size), dir);
	}
	
	public double distanceIntensity(Agent o) {
		double d = 1-h.distance(o)/h.getPerceptionSkills();
		return d*d;
	}
}
