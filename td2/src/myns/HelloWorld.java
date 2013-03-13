package myns;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;




public class HelloWorld {

    public static void main(String[] args) {
        System.out.println("Hello, World");
        
        Runtime rt = Runtime.instance();
		try {
			
	        Profile p = new ProfileImpl("properties");
	        AgentContainer mc = rt. createMainContainer(p);
	        AgentController ac;
	        
			ac = mc.createNewAgent(
					"FactAgent", "myns.FactAgent", new Object[]{4});
	        ac.start();
	        
			ac = mc.createNewAgent(
					"MultAgent1", "myns.MultAgent", new Object[]{5});
	        ac.start();
	        
			ac = mc.createNewAgent(
					"MultAgent2", "myns.MultAgent", new Object[]{5});
	        ac.start();
	        
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
    }

}