package fr.utc.ia04;

public class Metabolisme extends Module {

	public Metabolisme(Agent a) {
		super(a);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void preAction(Beings beings) {
		// TODO Auto-generated method stub

	}

	@Override
	public void postAction(Beings beings) {
		agent.awake--;
		agent.energy--;
		agent.social--;
	}

}
