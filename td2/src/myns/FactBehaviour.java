package myns;

import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;

import java.io.StringWriter;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;

public class FactBehaviour extends CyclicBehaviour {
	private static final long serialVersionUID = 2681696320529337766L;

	private class en_cours{
		public int id;
		public int n=0;
		public int f=0;
		public AID sender = null;
		
		public en_cours(){
			id=0; this.n=0; this.f=0; sender=null;
		}
	};
	
	private ArrayList<en_cours> tab = new ArrayList<en_cours>();
	
	
	@Override
	public void action() {
		ACLMessage message = myAgent.receive();
		if (message != null) {
			try {
				Integer.valueOf(message.getContent());
			}
			catch (Exception ex) {
				
				answerMult(message);
				return;
			}
			answer(message);
		}
		else
			block();
		
	}

	private void answerMult(ACLMessage message) {
		ObjectMapper mapper = new ObjectMapper();
		if (message.getPerformative() == ACLMessage.FAILURE) {
			System.out.println("Message = "+message.getContent()+ "\n");
			return;
		}
		
		try {
			OperationReponse orep = mapper.readValue(message.getContent(), OperationReponse.class);
			
			en_cours e = tab.get(orep.id);
			
			e.f = orep.value;
			e.n--;
	
			if (e.n <= 1) {
				ACLMessage req = new ACLMessage(ACLMessage.INFORM);
				req.addReceiver(e.sender);
				req.setContent(String.valueOf(e.f));
				System.out.println("fact : "+String.valueOf(e.f));
				myAgent.send(req);
			}
			else {

				int[] tab ={e.f, e.n};
				OperationRequete or =new OperationRequete("x", tab, e.id);
				
				sendOperation(getMultiplicator(), or);
				
			}
		}
		catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Message = "+message.getContent()+ "\n");
		}
	}
	
	private void answer(ACLMessage message) {
		en_cours e = new en_cours();
		tab.add(e);
		e.id = tab.size()-1;
		e.n = Integer.valueOf(message.getContent()) - 1;
		e.f = 1;
		e.sender = message.getSender();
		
		int[] tab ={e.n+1, e.n};
		
		OperationRequete or = new OperationRequete("x", tab, e.id);
		
		try {
			sendOperation(getMultiplicator(), or);
		} catch (FIPAException ex) {
			ex.printStackTrace();
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
	
	public void sendOperation(AID aid, OperationRequete opReq)
	{
		ACLMessage req = new ACLMessage(ACLMessage.REQUEST);
		req.addReceiver(aid);
		
		ObjectMapper mapper = new ObjectMapper();
		StringWriter sw = new StringWriter();
		
		try {
			mapper.writeValue(sw, opReq);
			String s = sw.toString();
			req.setContent(s);
			myAgent.send(req);
		}
		catch(Exception ex) {ex.printStackTrace();}
	}

}
