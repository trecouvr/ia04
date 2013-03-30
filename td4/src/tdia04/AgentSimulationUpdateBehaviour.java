package tdia04;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.WakerBehaviour;
import jade.lang.acl.ACLMessage;

public class AgentSimulationUpdateBehaviour extends WakerBehaviour {

	public AgentSimulationUpdateBehaviour(Agent a, long timeout) {
		super(a, timeout);
	}
	
	@Override
	protected void onWake() {
		super.onWake();
		
		Main.sudoku.afficherTab();
		System.out.println("\n");
		
		ACLMessage req = new ACLMessage(ACLMessage.REQUEST);
		req.setContent("update");
		for (AID a : ((AgentSimulation)myAgent).agents)
			req.addReceiver(a);
		myAgent.send(req);
		
		//System.out.println("isDone="+Main.sudoku.isDone());
		
		if (!Main.sudoku.isDone())
			myAgent.addBehaviour(new AgentSimulationUpdateBehaviour(myAgent, 1000));
		else Main.sudoku.afficherTab();
	}
}
