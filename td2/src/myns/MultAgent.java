package myns;

import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;


public class MultAgent extends Agent {
	private static final long serialVersionUID = 4213489884158920500L;

	protected void setup() {
		System.out.println(getLocalName()+ "--> Hello");
		

		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(getAID());
		ServiceDescription sd = new ServiceDescription();
		sd.setType("Operations");
		sd.setName("Multiplication");
		dfd.addServices(sd);
		try {
			DFService.register(this, dfd);
		}
		catch (FIPAException fe) {
			fe.printStackTrace();
		}
		
		CyclicMult b = new CyclicMult();
		addBehaviour(b);

	}

}
