package tdia04;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;


public class main {

	public static void main(String[] args) {
		//*
		System.out.println("ok");
		Runtime rt=Runtime.instance();
		try {
			ProfileImpl profile = new ProfileImpl("properties");
			AgentContainer mc=rt.createMainContainer(profile);
			AgentController ac;
			
			ac=mc.createNewAgent("AppAgent", "tdia04.AppAgent", new Object[]{4});
			ac.start();
			
			ac=mc.createNewAgent("BDD", "tdia04.BDD", new Object[]{4});
			ac.start();
			
			ac=mc.createNewAgent("LGD", "tdia04.LgdAgent", new Object[]{4});
			ac.start();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//*/
		
		/*
		QueryExecution qexec = null; 
		Query q = null; 
		ResultSet dbPediaConcept=null; 
		System.setProperty("http.proxyHost", "proxyweb.utc.fr"); 
		System.setProperty("http.proxyPort", "3128"); 
		try { 
		 //q = QueryFactory.read("query"); 
		q = QueryFactory.create(
				"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
				"PREFIX lgd: <http://linkedgeodata.org/> \n"+
				"PREFIX lgdo: <http://linkedgeodata.org/ontology/> \n" +
				"SELECT * FROM <http://linkedgeodata.org> \n" +
				"WHERE { \n" +
				" ?country a lgdo:Country . \n" +
				" ?country <http://linkedgeodata.org/property/capital_city> ?city . \n" +
				" ?country <http://linkedgeodata.org/property/wikipedia> ?name . \n" +
				"FILTER ( ?country = <http://linkedgeodata.org/triplify/node435981993> ) \n" +
				"} "
			);
		qexec = QueryExecutionFactory.sparqlService("http://linkedgeodata.org/sparql", q ); 
		 dbPediaConcept = qexec.execSelect(); 
		 ResultSetFormatter.out(dbPediaConcept); 
		} catch (Exception e){ 
		 System.err.println(e.toString()); 
		} finally { 
		 qexec.close(); 
		} //*/
	}
//<http://linkedgeodata.org/triplify/node435981993>
	
	
}
