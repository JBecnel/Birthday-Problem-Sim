package statsim;

import java.lang.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

/* This object creates a panel to display the resutls of the the simulation.
*/

public class ResultsPanel extends JPanel {
															
//===================FIELDS====================

	private JTextField txtNumSim;  //. total number of simulations
	private JTextField txtTotalMatches; // total number of mathces
	private JTextField txtTimesMatched; // times a simulation has turned up a match
	private JTextField txtPercent;  // percent of time a match was found

	private JButton cmdClear;   // button to clear the current results
	
	
//===============CONSTRUCTORS==========================
	
	
  public ResultsPanel(ActionListener al) {
		// creates a resutls panel, whose button has an
		// actionlister al
		
		// give the panel a gridbaglayout
		GridBagLayout gridbag = new GridBagLayout();
    GridBagConstraints c = new GridBagConstraints();
	  this.setLayout(gridbag);
		
		// add the title results
		c.gridx = 0; 
		c.anchor = GridBagConstraints.CENTER;
		c.gridwidth = 2;
		c.insets = new Insets(0,0,10,0);
		
		
		Font fancyFont = new Font("Serif", Font.BOLD , 20);    
		this.setBorder(
      new TitledBorder(
        LineBorder.createGrayLineBorder(),
        "Results", TitledBorder.LEFT,
      TitledBorder.TOP, fancyFont));
		
		//JLabel l = new JLabel("Results");
		//Font fancyFont = new Font("Serif", Font.BOLD | Font.ITALIC, 20);
		//   l.setFont(fancyFont);
		// this.add(l,c);
		
		// add labels for each stat
		c.insets = new Insets(0,0,0,0);
		c.gridx = 0;	c.gridwidth=1;
		c.anchor = GridBagConstraints.WEST;
		createLabel(c, "Simulations:       ");
		createLabel(c, "Total Matches:     ");
		createLabel(c, "Found Matches:     ");
		createLabel(c, "Percent:           ");
		
		// add each text field
		c.gridx = 1; 
		txtNumSim = createTextField(c);
		txtTotalMatches = createTextField(c);
		txtTimesMatched = createTextField(c);
		txtPercent = createTextField(c);		
		
		// add the clear button
		c.gridx = 0;
		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(10,0,0,0);
		cmdClear = new JButton("Clear");
		cmdClear.addActionListener(al);
		this.add(cmdClear,c);		
  }

	
//===================METHODS=====================

//----------------createTextField--------------------
	private JTextField createTextField(GridBagConstraints c) {
			// This method creates a text field and adds it
			// to the panel and returns the textfield
			JTextField t = new JTextField("0",4);
			t.setEditable(false);			
			this.add(t,c);			
			return t;
	}
	
//-------------------createLable-------------------
	private void createLabel(GridBagConstraints c, String s) {
		// this method creates a lable and adds it to the panel
		 JLabel l = new JLabel(s,SwingConstants.LEFT);
		 this.add(l,c);
	}
	
	
//---------------------clear--------------------
	public void clear() {
		// this method clears the table of resutls
		txtNumSim.setText("0");
		txtTotalMatches.setText("0");
		txtTimesMatched.setText("0");
		txtPercent.setText("0");
	}
	
//-----------------getIntValue----------------
	private int getIntValue(JTextField txt) {
	  // this method returns the integer value in a given test field
		Integer i = Integer.valueOf(txt.getText());
		return i.intValue();			
	}
	
//-------------------recordSim--------------
	
	public void recordSim() {
		// this method records that simulation has taken place by
		// updating the textbox
		int num = this.getIntValue(txtNumSim) + 1;
		txtNumSim.setText(String.valueOf(num));
	}
	
//--------------------recordMatch--------------
	public void recordMatch() {
		// this method recrods that a match was found
		int num = this.getIntValue(txtTotalMatches) + 1;
		txtTotalMatches.setText(String.valueOf(num));
	}

//----------------recordMatchFound----------
	public void recordMatchFound() {
		// this method records that a match has been found during current
		// simulation
		int num = this.getIntValue(txtTimesMatched) + 1;
		txtTimesMatched.setText(String.valueOf(num));
	}
	
//-----------------updatePercent-----------------------
	public void updatePercent() {
		// this method updates the percent of time matches have been found
		// after a simulation takes place
		double per = this.getIntValue(txtTimesMatched) * 100.0 / this.getIntValue(txtNumSim);
		per = (Math.round(per *10.0) / 10.0); // round to one decimal place
		txtPercent.setText(String.valueOf(per));
	}
	
}
