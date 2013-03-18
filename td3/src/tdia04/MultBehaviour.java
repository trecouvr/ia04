package tdia04;

import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;


public class MultBehaviour extends Behaviour {
	private void answer(ACLMessage message) {
		String par = message.getContent();
		ACLMessage reply = message.createReply();
		reply.setReplyWith("mult");
		if (par.contains("x")) {
			String[] parameters = par.split("x");
			int n = Integer.parseInt(parameters[0].trim())
			* Integer.parseInt(parameters[1].trim());
			reply.setPerformative(ACLMessage.INFORM);
			reply.setContent(String.valueOf(n));
		}
		else {
			reply.setPerformative(ACLMessage.FAILURE);
			reply.setContent("unknown operator");
		}
		myAgent.send(reply);
	}

	@Override
	public void action() {
		ACLMessage message = myAgent.receive();
		if (message != null) {
			answer(message);
		}
		else
			block();

	}

	@Override
	public boolean done() {
		// TODO Auto-generated method stub
		return false;
	}
}
