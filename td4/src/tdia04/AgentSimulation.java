package tdia04;
import jade.core.AID;
import jade.core.Agent;


public class AgentSimulation extends Agent {
	
	public AID[] agents=new AID[27];
	
	@Override
	protected void setup() {
		super.setup();
		this.addBehaviour(new AgentSimulationBehaviour());
		this.addBehaviour(new AgentSimulationUpdateBehaviour(this, 1000));
		Main.sudoku.afficher_tab();
	}
	
}
