package myns;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class CyclicMult extends CyclicBehaviour {
	private static final long serialVersionUID = -5880326092578734398L;

	@Override
	public void action() {
		ACLMessage message = myAgent.receive();
		if (message != null) {
			myAgent.addBehaviour(new MultBehaviour(myAgent, (int)(Math.random() * 3000), message));
		}
		else
			block();

	}

}
