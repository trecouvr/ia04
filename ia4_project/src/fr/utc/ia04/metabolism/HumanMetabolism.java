package fr.utc.ia04.metabolism;

import fr.utc.ia04.agent.Agent;
import fr.utc.ia04.agent.Human;
import fr.utc.ia04.simulation.Beings;

public class HumanMetabolism extends AbstractMetabolism {

	public HumanMetabolism(Agent a) {
		super(a);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void preAction(Beings beings) {
		// TODO Auto-generated method stub

	}

	@Override
	public void postAction(Beings beings) {
		Human h = (Human)agent;
		h.setAwake(h.getAwake()-1);
		h.setEnergy(h.getEnergy()-1);
		h.setSocial(h.getSocial()-1);
	}

}
