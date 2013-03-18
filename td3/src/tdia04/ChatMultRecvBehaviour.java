package tdia04;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class ChatMultRecvBehaviour extends CyclicBehaviour {

	@Override
	public void action() {
		MessageTemplate mt = MessageTemplate.MatchReplyWith("mult");
		ACLMessage message = myAgent.receive(mt);
		if (message != null) {
			ACLMessage req = new ACLMessage(ACLMessage.REQUEST);
			req.setContent(message.getContent());
			req.setReplyWith("chat");
			((ChatAgent) myAgent).sendAll(req);
		}
		else {
			block();
		}
	}

}
