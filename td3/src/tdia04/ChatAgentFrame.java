package tdia04;

import jade.gui.GuiEvent;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatAgentFrame extends JFrame implements PropertyChangeListener {
	
	protected ChatAgent myAgent;
	protected GridLayout mLayout;
	protected JTextArea mTextArea;
	protected JTextField mTextField;
	protected JPanel mpanel;
	
	public ChatAgentFrame(ChatAgent agent) {
		super();
		myAgent = agent;
		initialize();
	}
	
	private void initialize() {
		this.setPreferredSize(new Dimension(400, 400));
		this.setSize(400, 400);
		
		mpanel = new JPanel();
		mpanel.setLayout(new BorderLayout());
		
		
		mTextArea = new JTextArea(10,10);
		mTextField = new JTextField(10);
		mTextField.addActionListener(new MyActionListener());
		
		JScrollPane spane = new JScrollPane(mTextArea);
		
		mpanel.add(spane, BorderLayout.CENTER);
		mpanel.add(mTextField, BorderLayout.PAGE_END);
		
		this.setContentPane(mpanel);
		this.setTitle(myAgent.getName());
		this.setVisible(true);
	}

	private void toAgent() {
		String txt = mTextField.getText();
		mTextField.setText("");
		
		GuiEvent ev = new GuiEvent(this,ChatAgent.TEXT_EVENT);
		ev.addParameter(txt);
		System.out.println("send to agent : "+txt);
		myAgent.postGuiEvent(ev);
	}

	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		mTextArea.append((String) arg0.getNewValue() + "\n");
	}
	
	class MyActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			toAgent();
		}
	}
}