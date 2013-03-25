package tdia04;
import java.util.ArrayList;



public class Cellule {
	public int valeur;
	public ArrayList<Integer> valeurs_possibles=new ArrayList<Integer>();
	
	public Cellule(int v){
		valeur=v;
		if (v==0)
		for (int i=1; i<10; ++i){
			valeurs_possibles.add(i);
		}
	}
	
	public synchronized void removeValsPossibles(ArrayList<Integer> vals){
		valeurs_possibles.removeAll(vals);
	}
	
	public void setVal(int val){
		valeur = val;
		valeurs_possibles.clear();
	}
}