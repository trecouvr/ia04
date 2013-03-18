package tdia04;

import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class ChatReceiverBehaviour extends CyclicBehaviour {

	@Override
	public void action() {
		MessageTemplate mt = MessageTemplate.MatchReplyWith("chat");
		ACLMessage message = myAgent.receive(mt);
		if (message != null) {
			String line = message.getContent();
			AID sender = message.getSender();
			((ChatAgent)myAgent).sendEvent(sender.getName() + ": " + line);
			if (!sender.getName().equals(myAgent.getName()) && line.contains("x") && line.contains("?")) {
				line = line.replace("?", "");
				String[] parameters = line.split("x");
				try {
					int n1 = Integer.parseInt(parameters[0].trim());
					int n2 = Integer.parseInt(parameters[1].trim());
				}
				catch (NumberFormatException ex) {
					return;
				}
				System.out.println(sender.getName());
				System.out.println(myAgent.getName());
				try {
					AID multiplicator = getMultiplicator();
					ACLMessage req = new ACLMessage(ACLMessage.REQUEST);
					req.addReceiver(multiplicator);
					req.setContent(line);
					myAgent.send(req);
				} catch (FIPAException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else {
				block();
		}
	}
	
	protected AID getMultiplicator() throws FIPAException {
		DFAgentDescription template = new DFAgentDescription();
		//dfd.setName(getAID());
		ServiceDescription sdd = new ServiceDescription();
		sdd.setType("Operations");
		sdd.setName("Multiplication");
		template.addServices(sdd);
		DFAgentDescription[] result =
				DFService.search(myAgent, template);
		return result[(int)(Math.random() * result.length)].getName();
	}
}