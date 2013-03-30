package tdia04;
import java.util.ArrayList;



public class Cellule {
	protected int mValeur;
	protected ArrayList<Integer> mValeursPossibles=new ArrayList<Integer>();
	
	public Cellule(int v){
		mValeur=v;
		if (v==0)
		for (int i=1; i<10; ++i){
			mValeursPossibles.add(i);
		}
	}
	
	public synchronized void removeValsPossibles(ArrayList<Integer> vals){
		mValeursPossibles.removeAll(vals);
	}
	
	public synchronized boolean valIsPossible(int val){
		return mValeursPossibles.contains(val);
	}
	
	public synchronized int getBinaryPossibles(){
		int a = 0;
		for (int i : mValeursPossibles){
			a += (1 << i);
		}
		return a;
	}
	
	public int numberOfPossible(){
		return mValeursPossibles.size();
	}
	
	public void setVal(int val){
		mValeur = val;
		mValeursPossibles.clear();
	}
	
	public int getVal() {
		return mValeur;
	}
	
	public void setValWithLastValPossible() throws Exception{
		if (mValeursPossibles.size() != 1){
			throw new Exception("mValeursPossibles.size() == "+ mValeursPossibles.size());
		}
		setVal(mValeursPossibles.get(0));
	}
}