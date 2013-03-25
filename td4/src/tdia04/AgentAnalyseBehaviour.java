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
			if(message.getPerformative()==ACLMessage.INFORM){
				System.out.println(myAgent.getLocalName()+" : "+message.getContent());
				rank=Integer.valueOf(message.getContent());
			}
			else{
				Cellule[] cells = Main.sudoku.getCellulesForRank(rank);
				for (Cellule c : cells){
					if (c.valeur == 0 && c.valeurs_possibles.size() == 1){
						c.setVal(c.valeurs_possibles.get(0));
					}
				}
				ArrayList<Integer> vals = new ArrayList<Integer>();
				for (Cellule c : cells){
					if (c.valeur != 0){
						vals.add(c.valeur);
					}
				}
				for (Cellule c : cells){
					c.removeValsPossibles(vals);
				}
				
			}
		}
	}
}
