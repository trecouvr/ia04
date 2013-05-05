package tdia04;
import java.io.ByteArrayOutputStream;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Model;
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
					String r=execSparql(msg.getContent());
					ACLMessage rep=msg.createReply();
					if(r.isEmpty()) rep.setContent("Inconnu dans la BDD");
					else rep.setContent(r);
					send(rep);
				}
				else block();
			}
		});
	}

	private String execSparql(String queryString){
		Query query = QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.create(query, model); 
		ResultSet results = qexec.execSelect(); 
		ByteArrayOutputStream boas=new ByteArrayOutputStream();
		ResultSetFormatter.out(boas,results); 
		qexec.close(); 
		return boas.toString();
	}
}
