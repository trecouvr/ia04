package tdia04;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

        Runtime rt = Runtime.instance();
		try {
			
	        Profile p = new ProfileImpl("properties");
	        AgentContainer mc = rt.createMainContainer(p);
	        AgentController ac;
	        
			ac = mc.createNewAgent(
					"ChatAgent1", "tdia04.ChatAgent", new Object[]{4});
	        ac.start();
	        
	        ac = mc.createNewAgent(
					"MultAgent", "tdia04.MultAgent", new Object[]{4});
	        ac.start();
	        
	        
	        Profile p2 = new ProfileImpl("lcoalhost", 
	        			Integer.parseInt(p.getParameter("port", "8080")),
	        			mc.getPlatformName()); 
	        AgentContainer mc2 = rt.createAgentContainer(p2);
	        AgentController ac2;
	        ac2 = mc2.createNewAgent(
					"ChatAgent2", "tdia04.ChatAgent", new Object[]{4});
	        ac2.start();
	        
	        
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
