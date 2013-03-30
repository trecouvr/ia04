package tdia04;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

import java.util.ArrayList;

public class AgentAnalyseBehaviour extends CyclicBehaviour {

	protected int rank;
	
	@Override
	public void action() {
		
		ACLMessage message = myAgent.receive();
		if (message != null) {
			Cellule[] cells = Main.sudoku.getCellulesForRank(rank);
			if(message.getPerformative()==ACLMessage.INFORM){
				System.out.println(myAgent.getLocalName()+" : "+message.getContent());
				rank=Integer.valueOf(message.getContent());
				cleanSudoku();
			}
			else{
				// si il ne reste qu'une seule valeur possible, on set
				// la valeur
				for (Cellule c : cells){
					if (c.getVal() == 0 && c.numberOfPossible() == 1){
						System.out.println("Je ("+this+") vais set "+c+" parce que il ne reste plus que une valeur possible");
						try {
							c.setValWithLastValPossible();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				// si une valeur n'est possible que dans une cellule, 
				// on set la valeur dans cette cellule
				for (int i=1; i<10; ++i){
					Cellule cc = null;
					for (Cellule c : cells){
						if (c.getVal() == i){
							cc = null;
							break;
						}
						else if (c.getVal() == 0 && c.valIsPossible(i)) {
							if (cc == null){
								cc = c;
							}
							else {
								cc = null;
								break;
							}
						}
					}
					if (cc != null){
						System.out.println("Je ("+this+") vais set "+cc+" à "+i+" parce que c'est le seul endroit ou je peux mettre cette valeur");
						cc.setVal(i);
					}
				}
				// si deux valeurs ne sont possiles que dans deux cellules, 
				// alors on les retirent des valeurs possibles
				/*for (Cellule cell : cells) {
					if (cell.getVal() != 0 && cell.numberOfPossible() == 2){
						Cellule cc = null;
						int b = cell.getBinaryPossibles();
						for (Cellule c : cells){
							if (c.getBinaryPossibles() == b) {
								if (cc == null){
									cc = c;
								}
								else {
									cc = null;
									break;
								}
							}
						}
						if (cc != null){
							ArrayList<Integer> valsToRemove = new ArrayList<Integer>();
							for (int i=0; i<10; ++i){
								if (((1<<i) & b) != 0){
									valsToRemove.add(i);
								}
							}
							for (Cellule c : cells){
								c.removeValsPossibles(valsToRemove);
							}
						}
					}
				}
				*/
				cleanSudoku();
			}
		}
		else{
			block();
		}
	}
	
	protected void cleanSudoku(){
		Cellule[] cells = Main.sudoku.getCellulesForRank(rank);
		// retirer les valeurs possibles en fonctions des 
		// valeurs déjà set
		ArrayList<Integer> vals = new ArrayList<Integer>();
		for (Cellule c : cells){
			if (c.getVal() != 0){
				vals.add(c.getVal());
			}
		}
		for (Cellule c : cells){
			c.removeValsPossibles(vals);
		}
	}
}
