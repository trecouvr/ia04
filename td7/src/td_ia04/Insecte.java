package td_ia04;

import sim.engine.SimState;
import sim.util.Bag;

public class Insecte extends MySteppable {

	public static int NBRE_POINTS=10;
	public static int ENERGIE_MAX=10;
	public static int CHARGE_MAX=NBRE_POINTS+1;

	public int distanceDeplacement;
	public int distancePerception;
	public int chargeMax;
	
	public int energieActuelle=ENERGIE_MAX;
	public int chargeActuelle=0;

	public Insecte(int x, int y, int distanceDeplacement,int distancePerception, int chargeMax) {
		super(x,y);
		this.distanceDeplacement = 1 + distanceDeplacement;
		this.distancePerception = 1 + distancePerception;
		this.chargeMax = 1+chargeMax;
	}

	public int getDistanceDeplacement() {
		return distanceDeplacement;
	}

	public void setDistanceDeplacement(int distanceDeplacement) {
		this.distanceDeplacement = distanceDeplacement;
	}

	public int getDistancePerception() {
		return distancePerception;
	}

	public void setDistancePerception(int distancePerception) {
		this.distancePerception = distancePerception;
	}

	public int getChargeMax() {
		return chargeMax;
	}

	public void setChargeMax(int chargeMax) {
		this.chargeMax = chargeMax;
	}

	public int getEnergieActuelle() {
		return energieActuelle;
	}

	public void setEnergieActuelle(int energieActuelle) {
		this.energieActuelle = energieActuelle;
	}

	public int getChargeActuelle() {
		return chargeActuelle;
	}

	public void setChargeActuelle(int chargeActuelle) {
		this.chargeActuelle = chargeActuelle;
	}

	public static int LEVEL = 2;
	@Override
	public void step(SimState state) {
		Beings beings = (Beings) state;
		move(beings);
		if(energieActuelle<=0) meurt(beings);
	}
	

	public void move(Beings beings) {
		int x2=beings.yard.stx(x-1);
		Bag currentCase=beings.yard.elements();
		//Object nextCase=beings.yard.get(x2, y);
		
		boolean didSmthg=false;
		
		for(int i=0;i<currentCase.size();i++){
			if(currentCase.get(i).toString()=="td_ia04.Nourriture"){
				Nourriture n=(Nourriture)currentCase.get(i);
				if(energieActuelle < ENERGIE_MAX){
					System.out.println("1");
					//beings.yard.set(x, y, null);
					energieActuelle+=1;
					n.setQuantite(n.getQuantite()-1);
					
				}
				else if(chargeActuelle < CHARGE_MAX){
					System.out.println("2");
					//beings.yard.set(x, y, null);
					chargeActuelle+=1;
					n.quantite-=1;
				}
				didSmthg=true;
			}
		}
		
		if(!didSmthg){
			//beings.yard.elements().remove(this);
			beings.yard.set(x, y, null);
			beings.yard.set(x2, y, this);
			beings.yard.get(x2, y);
			x=x2;
			energieActuelle-=1;
		}
		

	}
}
