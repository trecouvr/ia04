package tdia04;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Sudoku {

	protected Cellule[][] tab=new Cellule[9][9];
	
	public Sudoku(String filepath){
		Scanner sc;
		try {
			sc = new Scanner(new File(filepath));
			
			int i=0, j=0;
			for(i=0;i<9;i++){
				for(j=0;j<9;j++){
					tab[i][j]=new Cellule(sc.nextInt());
				}
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public void afficherTab() {
		for(Cellule[] ligne : tab){
			for(Cellule v : ligne){
				System.out.print(v.getVal()+" ");
			}
			System.out.print("\n");
		}
	}
	
	public Cellule[] getCellulesForRank(int r){
		Cellule[] c = null;
		if (0 <= r && r < 9){
			c = tab[r];
		}
		else if (9 <= r && r < 18){
			c = new Cellule[9];
			for (int i=0; i<9; ++i){
				c[i] = tab[i][r-9];
			}
		}
		else {
			c = new Cellule[9];
			int r0 = r-18;
			for (int a=0; a<3; ++a){
				for (int b=0; b<3; ++b){
					c[a+b*3] = tab[(r0/3)*3 + a][(r0%3) * 3 + b];
				}
			}
		}
		return c;
	}
	
	public boolean isDone(){
		for (Cellule[] cells : tab){
			for (Cellule c : cells){
				if (c.mValeur == 0)
					return false;
				
			}
		}
		return true;
	}
	
}
