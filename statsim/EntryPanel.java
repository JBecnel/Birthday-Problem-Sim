package statsim;

import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class EntryPanel extends JPanel  {

		
//===================FIELDS====================

	private JButton cmdSim;  // button to start simulation
	private JButton cmdAdd;  // adds more bdays to current simulation
	private JTextField txtNumSim; // used to enter the # of simulations to run
	private JTextField txtNumBday; // used to enter the number of bdays to use
	private JCheckBox chkAnimate; // check box to determine whether or not 
						// to animate the simulation

//================CONSTRUCTORS=================
	
  public EntryPanel(ActionListener al) {
		// Given an actionlister to listen for the button
		// this constructor intializes a new entry panel
		
		// set up a gridbag layout
		GridBagLayout gridbag = new GridBagLayout();
    GridBagConstraints c = new GridBagConstraints();
	  this.setLayout(gridbag);
		
		
		c.gridx = 0;	// x position
		c.anchor = GridBagConstraints.WEST; // put components to left
		// add a label and a text box
		createLabel(c, "Enter number of Birthdays: ");
		txtNumBday = createTextField(c, "20");

		c.insets = new Insets(5,0,0,0); // gives 5 points of space from top
		// add a lable and a text box for # of sims
		createLabel(c, "Enter number of Simulations: ");		
		txtNumSim = createTextField(c, "1");
		
		// create and add button panel 
		JPanel cmdPanel = createButtonPanel(al);		
    this.add(cmdPanel,c);
		
		this.chkAnimate = new JCheckBox("Animate", true);
		c.anchor = GridBagConstraints.WEST;
		this.add(chkAnimate,c);
		
 }

//==================METHODS==================


//--------------createButtonPanel------------

private JPanel createButtonPanel(ActionListener al) {
		// this method creates a panel with the Simulate button
		// and Add button
		
		// create new panel with GridBagLayout
		JPanel p = new JPanel();		
		GridBagConstraints c = new GridBagConstraints();
		p.setLayout(new GridBagLayout());
				
		// create the button and have it listened to
    cmdSim = new JButton("Simulate");
		cmdSim.addActionListener(al);
		
		// add button to panel on the left with 10 pts of space from top
		c.insets = new Insets(10,0,0,0);
		c.anchor = GridBagConstraints.WEST;
    p.add(cmdSim,c);		
	
		// create the button and have it listened to
    cmdAdd = new JButton("Add");
		cmdAdd.addActionListener(al);
		
		c.gridx = 1; c.gridy =0;  // put Add button to left of sim
		// add the button to the panel with 10 pts of space from the top
		// and 20 pts of space from the first button
		c.insets = new Insets(10,20,0,0);
    p.add(cmdAdd,c);

    return p;		
	}

	//---------------createTextField--------------------
	private JTextField createTextField(GridBagConstraints c, String s) {
			// given the constriants and a string s, this method 
			// creates (and returns) a new text field added to the panel
			JTextField t = new JTextField(s,2);			
			this.add(t,c);
			
			return t;
	}
	
//---------------createLabel---------------------------
	private void createLabel(GridBagConstraints c, String s) {
		 // this method creates and adds a label to the panel
		 // with text the given string
		 JLabel l = new JLabel(s,SwingConstants.LEFT);
		 this.add(l,c);
	}


//---------------getNumSims----------------

public int getNumSims() {
   // This method returns the number of simulations
	 // from the txtNumSim box
	 Integer I = new Integer(this.txtNumSim.getText());
	 return I.intValue();
}

//----------------getNumBirthdays---------------

public int getNumBirthdays() {
   // This method returns the number of simulations
	 // from the txtNumSim box
	 Integer I = new Integer(this.txtNumBday.getText());
	 return I.intValue();
}

//---------------animateSim-------------------

public boolean animateSim() {
	// this method checks to see if a user has asked to animate the simulation
	return this.chkAnimate.isSelected();
}
	
}
