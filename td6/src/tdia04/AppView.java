package tdia04;
import jade.core.AID;
import jade.gui.GuiEvent;
import jade.lang.acl.ACLMessage;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class AppView extends JFrame implements PropertyChangeListener{
	JTextArea inputText=new JTextArea(10,50);
	JTextArea mTextArea=new JTextArea(10,10);
	JScrollPane mScrollPane= new JScrollPane(mTextArea);
	
	JButton buttonSparql = new JButton("Send SPARQL");
	JButton buttonNom = new JButton("Send Nom");
	JButton buttonCapitale = new JButton("Get capitale");
	
	protected AppAgent myAgent;
	
	public AppView(final AppAgent myAgent){
		
		/*
		ACLMessage msg=new ACLMessage(ACLMessage.REQUEST);
		msg.setContent(
				"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
				"PREFIX lgd: <http://linkedgeodata.org/> \n"+
				"PREFIX lgdo: <http://linkedgeodata.org/ontology/> \n" +
				"SELECT * FROM <http://linkedgeodata.org> \n" +
				"WHERE { \n" +
				" ?country a lgdo:Country . \n" +
				" ?country <http://linkedgeodata.org/property/capital_city> ?city . \n" +
				" ?country <http://linkedgeodata.org/property/wikipedia> ?name . \n" +
				"FILTER ( ?country = <http://linkedgeodata.org/triplify/node435981993> ) \n" +
				"} ");
		msg.addReceiver(new AID("LGD",AID.ISLOCALNAME));
		myAgent.send(msg);
		//*/
		this.myAgent=myAgent;
		
		this.setPreferredSize(new Dimension(400,400));
		this.setSize(new Dimension(1000,800));
		JPanel mPanel=new JPanel();
		mPanel.setLayout(new BorderLayout());
		
		buttonNom.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String query="PREFIX foaf:    <http://xmlns.com/foaf/0.1/>\n" +
						"SELECT DISTINCT ?b ?c \n" +
						"WHERE { ?person foaf:firstname \""+inputText.getText()+"\" .\n" +
								"?person ?b ?c }";
				postQuery(query);
			}
		});
		
		buttonSparql.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String query=inputText.getText();
				postQuery(query);
			}
		});
		
		//http://linkedgeodata.org/triplify/
		buttonCapitale.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String s = mTextArea.getText();
				Pattern pattern = Pattern.compile("(<http://linkedgeodata.org/triplify/node\\d+>)");
				Matcher matcher = pattern.matcher(s);
				while (matcher.find()) {

					ACLMessage msg=new ACLMessage(ACLMessage.REQUEST);
					msg.setContent(
							"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
							"PREFIX lgd: <http://linkedgeodata.org/> "+
							"PREFIX lgdo: <http://linkedgeodata.org/ontology/> \n" +
							"SELECT * FROM <http://linkedgeodata.org> \n" +
							"WHERE { \n" +
							" ?country a lgdo:Country . \n" +
							" ?country <http://linkedgeodata.org/property/capital_city> ?city . \n" +
							" ?country <http://linkedgeodata.org/property/wikipedia> ?name . \n" +
							"FILTER ( ?country = \n" + matcher.group(1) + " ) \n" +
							"} ");
					msg.addReceiver(new AID("LGD",AID.ISLOCALNAME));
					myAgent.send(msg);
					
					//mTextArea.setText(mTextArea.getText()+matcher.group(1));
				}
			}
		});
		
		JPanel north=new JPanel();
		north.add(inputText);
		north.add(buttonSparql);
		north.add(buttonNom);
		north.add(buttonCapitale);
		mPanel.add(north,BorderLayout.NORTH);
		
		mPanel.add(mScrollPane,BorderLayout.CENTER);
		
		this.setContentPane(mPanel);
		setVisible(true);
		
		
	}
	
	protected void postQuery(String query) {		
		GuiEvent ev=new GuiEvent(this, 0);
		ev.addParameter(query);
		System.out.println("Send : "+query);
		myAgent.postGuiEvent(ev);
	}
	


	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		if (arg0.getPropertyName().equals("set"))
			mTextArea.setText(arg0.getNewValue().toString());
		else
			mTextArea.setText(mTextArea.getText()+arg0.getNewValue().toString());
		
	}
}
