package tdia04;

import jade.core.AID;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;

public class AgentAnalyse extends Agent {
	
	@Override
	protected void setup() {
		super.setup();
		
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(getAID());
		ServiceDescription sd = new ServiceDescription();
		sd.setType("Analyse");
		sd.setName("Analyse");
		dfd.addServices(sd);
		try {
			DFService.register(this, dfd);
		}
		catch (FIPAException fe) {
			fe.printStackTrace();
		}
		
		ACLMessage req = new ACLMessage(ACLMessage.SUBSCRIBE);
		req.setContent("inscription");
		req.addReceiver(new AID("AgentSimulation", AID.ISLOCALNAME));
		this.send(req);
		
		
		this.addBehaviour(new AgentAnalyseBehaviour());
	}

	
}
