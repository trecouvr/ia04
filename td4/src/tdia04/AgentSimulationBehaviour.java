package tdia04;
import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;


public class AgentSimulationBehaviour extends Behaviour {

	protected int compteur=0;
	
	
	@Override
	public void action() {
		ACLMessage message = myAgent.receive();
		if (message != null) {
			ACLMessage req = message.createReply();
			req.setPerformative(ACLMessage.INFORM);
			req.setContent(String.valueOf(compteur));
			((AgentSimulation)myAgent).agents[compteur]=message.getSender();
			System.out.println("Send "+compteur+" to "+message.getSender());
			compteur++;
			myAgent.send(req);
		}
	}

	@Override
	public boolean done() {
		return compteur >= 27;
	}


}
