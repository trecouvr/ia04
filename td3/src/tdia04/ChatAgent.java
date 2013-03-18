package tdia04;

import jade.core.AID;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import jade.lang.acl.ACLMessage;

import java.beans.PropertyChangeSupport;

public class ChatAgent extends GuiAgent {
	
	PropertyChangeSupport changes =
		new PropertyChangeSupport(this);
	public static int TEXT_EVENT = 0;
	
	
	protected void setup() {
		ChatAgentFrame f = new ChatAgentFrame(this);
		changes.addPropertyChangeListener(f);
		addBehaviour(new ChatReceiverBehaviour());
		addBehaviour(new ChatMultRecvBehaviour());
		
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(getAID());
		ServiceDescription sd = new ServiceDescription();
		sd.setType("Chat");
		sd.setName("Chat");
		dfd.addServices(sd);
		try {
			DFService.register(this, dfd);
		}
		catch (FIPAException fe) {
			fe.printStackTrace();
		}
	}
	
	public void sendEvent (String info) {
		changes.firePropertyChange("line", null, info);
	}
	
	@Override
	protected void onGuiEvent(GuiEvent arg0) {
		System.out.println("recv evt");
		// Tester le type (il peut y en avoir plusieurs)
		if (arg0.getType() == TEXT_EVENT) {
			// Récupération de l’information
			String line = arg0.getParameter(0).toString();
			// Instructions
			System.out.println("agent rcv : "+line);
			ACLMessage req = new ACLMessage(ACLMessage.INFORM);
			req.setContent(line);
			req.setReplyWith("chat");
			sendAll(req);
		}
	}
	
	public void sendAll(ACLMessage req)
	{
		try {
			for (DFAgentDescription dfa : getAgents()) {
				req.addReceiver(dfa.getName());
			}
		} catch (FIPAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.send(req);
	}
	
	protected DFAgentDescription[] getAgents() throws FIPAException {
		DFAgentDescription template = new DFAgentDescription();
		ServiceDescription sdd = new ServiceDescription();
		sdd.setType("Chat");
		sdd.setName("Chat");
		template.addServices(sdd);
		DFAgentDescription[] result = DFService.search(this, template);
		return result;
	}
}