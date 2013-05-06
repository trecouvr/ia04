package td_ia04;

import sim.engine.SimState;
import sim.engine.Stoppable;
import sim.field.grid.ObjectGrid2D;
import sim.util.Int2D;

public class Beings extends SimState {
	public static int GRID_SIZE = 20;
	public static int NUM_A = 50;
	public static int NUM_B = 50;
	public ObjectGrid2D yard =
			new ObjectGrid2D(GRID_SIZE,GRID_SIZE);

	public Beings(long seed) {
		super(seed);
	}

	public void start() {
		super.start();
		yard.clear();
		addAgentsInsecte();
		addAgentsNourriture();
	}

	private void addAgentsNourriture() {
		for(int i = 0; i < NUM_A; i++) {
			
			Int2D location = new Int2D(random.nextInt(yard.getWidth()),
					random.nextInt(yard.getHeight()) );
			Object ag = null;
			while ((ag = yard.get(location.x,location.y)) != null) {
				location = new Int2D(random.nextInt(yard.getWidth()),
						random.nextInt(yard.getHeight()) );
			}
			Nourriture a = new Nourriture(location.x,location.y);
			yard.set(location.x,location.y,a);
			a.x = location.x;
			a.y = location.y;
			
			Stoppable stoppable=schedule.scheduleRepeating(a);
			a.stoppable=stoppable;
		}
	}

	private void addAgentsInsecte() {
		for(int i = 0; i < NUM_A; i++) {
			
			Int2D location = new Int2D(random.nextInt(yard.getWidth()),
					random.nextInt(yard.getHeight()) );
			Object ag = null;
			while ((ag = yard.get(location.x,location.y)) != null) {
				location = new Int2D(random.nextInt(yard.getWidth()),
						random.nextInt(yard.getHeight()) );
			}
			Insecte a = new Insecte(location.x, location.y, 2,2,2);
			yard.set(location.x,location.y,a);
			a.x = location.x;
			a.y = location.y;
			
			Stoppable stoppable=schedule.scheduleRepeating(a);
			a.stoppable=stoppable;
		}
	}

	public boolean free(int x, int y) {
		if(x<0 || x>GRID_SIZE || y<0 || y>GRID_SIZE) return false;
		return yard.get(x, y)==null;
	}
}
