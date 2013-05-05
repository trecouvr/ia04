package tdia04;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

import java.io.ByteArrayOutputStream;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.ModelFactory;


public class LgdAgent extends Agent {
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
					System.out.println("Lgd returns : " + r);
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
		System.out.println(queryString);
		QueryExecution qexec = null; 
		Query q = null; 
		ResultSet dbPediaConcept=null; 
		System.setProperty("http.proxyHost", "proxyweb.utc.fr"); 
		System.setProperty("http.proxyPort", "3128"); 
		try { 
		q = QueryFactory.create(queryString);
		qexec = QueryExecutionFactory.sparqlService("http://linkedgeodata.org/sparql", q ); 
		 dbPediaConcept = qexec.execSelect(); 

			ByteArrayOutputStream boas=new ByteArrayOutputStream();
			ResultSetFormatter.out(boas,dbPediaConcept); 

			 qexec.close(); 
			 return boas.toString();
		} catch (Exception e){ 
		 System.err.println(e.toString()); 
		 return "";
		}
		
	}
}
