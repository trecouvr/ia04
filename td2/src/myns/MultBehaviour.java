package myns;

import jade.core.Agent;
import jade.core.behaviours.WakerBehaviour;
import jade.lang.acl.ACLMessage;

import java.io.StringWriter;

import com.fasterxml.jackson.databind.ObjectMapper;


public class MultBehaviour extends WakerBehaviour {
	private static final long serialVersionUID = -5789718923270791106L;
	ACLMessage m;

	public MultBehaviour(Agent a, long timeout, ACLMessage msg) {
		super(a, timeout);
		m = msg;
	}
	
	@Override
	public void onWake() {
		answer(m);
	}

	private void answer(ACLMessage message) {
		String s = message.getContent();
		System.out.println(myAgent.getLocalName()+ " recv : " + s);
		
		ObjectMapper mapper = new ObjectMapper();
		StringWriter sw = new StringWriter();

		ACLMessage reply = message.createReply();
		
		try {
			OperationRequete or = mapper.readValue(s, OperationRequete.class);
			
			if (or.action.contentEquals("x")) {
				OperationReponse orep = null;
				if (or.args.length != 2) {
					orep = new OperationReponse(-2, "la multiplication ne marche aue avec 2 args", or.id);
				}
				else {
					int n = or.args[0] * or.args[1];
					orep = new OperationReponse(n, "ok!", or.id);
				}
				mapper.writeValue(sw, orep);
				reply.setPerformative(ACLMessage.INFORM);
				System.out.println(myAgent.getLocalName()+ " send : " + sw.toString());
				reply.setContent(sw.toString());
			}
			else {
				OperationReponse orep = new OperationReponse(-1, "action inconnue : '" + or.action + "'", or.id);
				mapper.writeValue(sw, orep);
				reply.setPerformative(ACLMessage.FAILURE);
				reply.setContent(sw.toString());
			}
			myAgent.send(reply);
			
		}
		catch(Exception ex) {ex.printStackTrace();}
		
	}

	
}
