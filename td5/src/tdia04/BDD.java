package tdia04;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.SimpleSelector;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;


public class BDD extends Agent {
	OntModel model;
	String foafPrefix;
	String td5Prefix;
	
	protected void setup(){
		model=ModelFactory.createOntologyModel();
		model.read("file:./foaf.n3",null,"TURTLE");
		model.read("file:./persons.n3",null,"TURTLE");
		
		foafPrefix=model.getNsPrefixURI("foaf");
		td5Prefix=model.getNsPrefixURI("td5");
		
		addBehaviour(new CyclicBehaviour() {
			
			@Override
			public void action() {
				ACLMessage msg=myAgent.receive();
				if(msg!=null){
					String r="";
					r+=iteratorToString(get_statements(msg.getContent()))+"\n";
					r+=iteratorToString(get_knows(msg.getContent()));
					ACLMessage rep=msg.createReply();
					if(r.equals("\n")) rep.setContent("Inconnu dans la BDD");
					else rep.setContent(r);
					send(rep);
				}
				else block();
			}
		});
	}

	public ExtendedIterator<Statement> get_statements(String name){
		Resource person=getPersonFromFirstName(name);
		System.out.println(person);
		
		if(person==null) return null;
		else return model.listStatements(new SimpleSelector(person,(Property)null,(Resource)null));
	}
	
	public ExtendedIterator<Statement> get_knows(String name){
		Resource person=getPersonFromFirstName(name);
		Property knows=model.getProperty(foafPrefix+"knows");
		if(person==null) return null;
		else return model.listStatements(new SimpleSelector(person,knows,(Resource)null));
	}
	
	public Resource getPersonFromFirstName(String name){
		Property firstname=model.getProperty(foafPrefix+"firstname");
		ExtendedIterator<Statement> it = model.listStatements(new SimpleSelector((Resource)null,firstname,name));
		System.out.println(it.hasNext());
		if(it.hasNext()) return it.next().getSubject();
		return null;
	}
	
	public String iteratorToString(ExtendedIterator<Statement> it){
		String value="";
		if(it==null) return "";

		while(it.hasNext())
			value+=it.next().toString()+"\n";
		
		return value;
	}

}
