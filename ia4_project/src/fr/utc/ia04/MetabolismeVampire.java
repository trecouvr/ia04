package fr.utc.ia04;

public class MetabolismeVampire extends Metabolisme {

	public MetabolismeVampire(Agent a) {
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
		v.energy--;
	}

}
