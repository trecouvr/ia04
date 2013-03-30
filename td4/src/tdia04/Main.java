package tdia04;
import jade.wrapper.AgentContainer;
import jade.core.Profile;
import jade.core.Runtime;
import jade.core.ProfileException;
import jade.core.ProfileImpl;
import jade.wrapper.AgentController;



public class Main {

	public static Sudoku sudoku=new Sudoku("grille3");
	
	public static void main(String[] args) {
		Runtime rt=Runtime.instance();
		try {
			ProfileImpl profile = new ProfileImpl("properties");
			AgentContainer mc=rt.createMainContainer(profile);
			AgentController ac;
			ac=mc.createNewAgent("AgentSimulation", "tdia04.AgentSimulation", new Object[]{4});
			ac.start();
			
			for (int i=0; i<27; ++i){
				ac=mc.createNewAgent("AgentAnalyse"+i, "tdia04.AgentAnalyse", new Object[]{4});
				ac.start();
			}
			sudoku.getCellulesForRank(26);
			return;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
