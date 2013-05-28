package fr.utc.ia04;

public class MetabolismeHuman extends Metabolisme {

	public MetabolismeHuman(Agent a) {
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
		h.awake--;
		h.energy--;
		h.social--;
	}

}
