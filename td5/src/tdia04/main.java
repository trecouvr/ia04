package tdia04;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.SimpleSelector;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import com.hp.hpl.jena.util.iterator.Filter;


public class main {

	public static void main(String[] args) {
	
		
		Runtime rt=Runtime.instance();
		try {
			ProfileImpl profile = new ProfileImpl("properties");
			AgentContainer mc=rt.createMainContainer(profile);
			AgentController ac;
			
			ac=mc.createNewAgent("AppAgent", "tdia04.AppAgent", new Object[]{4});
			ac.start();
			
			ac=mc.createNewAgent("BDD", "tdia04.BDD", new Object[]{4});
			ac.start();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
}
