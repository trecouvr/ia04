package fr.utc.ia04.metabolism;

import fr.utc.ia04.agent.Agent;
import fr.utc.ia04.agent.Human;
import fr.utc.ia04.simulation.Beings;

public class VampireMetabolism extends AbstractMetabolism {

	public VampireMetabolism(Agent a) {
		super(a);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void preAction(Beings beings) {
		// TODO Auto-generated method stub

	}

	@Override
	public void postAction(Beings beings) {
		Human v = (Human)agent;
		v.setEnergy(v.getEnergy()-1);
	}

}
