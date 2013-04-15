package tdia04;
import jade.gui.GuiEvent;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class AppView extends JFrame implements PropertyChangeListener{
	JTextField inputName=new JTextField(10);
	JTextArea mTextArea=new JTextArea(10,10);
	JScrollPane mScrollPane= new JScrollPane(mTextArea);
	protected AppAgent myAgent;
	
	public AppView(AppAgent myAgent){
		this.myAgent=myAgent;
		
		this.setPreferredSize(new Dimension(400,400));
		this.setSize(new Dimension(1000,800));
		JPanel mPanel=new JPanel();
		mPanel.setLayout(new BorderLayout());
		mPanel.add(inputName,BorderLayout.NORTH);
		mPanel.add(mScrollPane,BorderLayout.CENTER);
		inputName.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				onPressEnter();
			}
		});
		this.setContentPane(mPanel);
		setVisible(true);
		
		
	}
	
	protected void onPressEnter() {
		String name = inputName.getText();
		
		GuiEvent ev=new GuiEvent(this, 0);
		ev.addParameter(name);
		System.out.println("Send : "+name);
		myAgent.postGuiEvent(ev);
	}
	


	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		mTextArea.setText(arg0.getNewValue().toString());
	}
}
