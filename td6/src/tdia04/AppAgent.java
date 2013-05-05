package tdia04;
import java.beans.PropertyChangeSupport;

import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import jade.lang.acl.ACLMessage;


public class AppAgent extends GuiAgent{
	
	AppView mView;
	PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	
	public void setup(){
		mView=new AppView(this);
		
		pcs.addPropertyChangeListener(mView);
		
		addBehaviour(new CyclicBehaviour() {
			
			@Override
			public void action() {
				ACLMessage msg=myAgent.receive();
				if(msg!=null){
					System.out.println("AppAgent receive : "+msg.getContent());
					String property = "";
					if (msg.getSender().getLocalName().equals("LGD"))
						property = "add";
					else
						property = "set";
					pcs.firePropertyChange(property, null, msg.getContent());
				}
				else block();
			}
		});
	}

	@Override
	protected void onGuiEvent(GuiEvent arg0) {
		ACLMessage msg=new ACLMessage(ACLMessage.REQUEST);
		msg.setContent(arg0.getParameter(0).toString());
		msg.addReceiver(new AID("BDD",AID.ISLOCALNAME));
		send(msg);
	}

}
