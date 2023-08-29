package statsim;               // class is part of model package

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;


/*       Jeremy Becnel                        3/29/06

   This class defines a key or table to help the user read the calendar output
*/


public class KeyPanel extends JPanel   {


//------------------KeyTable----------------

public KeyPanel() {
		// given this panel a grid bag layout
		GridBagLayout gridbag = new GridBagLayout();
    GridBagConstraints c = new GridBagConstraints();
	  this.setLayout(gridbag);
		
		// add a title label "Key"
		c.gridx = 0; 
		c.anchor = GridBagConstraints.CENTER;
		c.gridwidth = 2;
		c.insets = new Insets(0,0,10,0);
		
		
		Font fancyFont = new Font("Serif", Font.BOLD , 20);    
		this.setBorder(
      new TitledBorder(
        LineBorder.createGrayLineBorder(),
        "Key", TitledBorder.LEFT,
      TitledBorder.TOP, fancyFont));
		
		// create labels for the key
		c.insets = new Insets(0,0,0,0);
		c.gridx = 0;	c.gridwidth=1;
		c.anchor = GridBagConstraints.WEST;
		this.add(createLabel( "One Match:            "),c);
		this.add(createLabel("Two Matches:           "),c);
		this.add(createLabel("Three or more Matches: "),c);
		
		// create text boxes with the corresponding color
		c.gridx = 1; 
		this.add( createTextField( MonthPanel.MATCH1),c);
		this.add( createTextField( MonthPanel.MATCH2),c);
		this.add( createTextField( MonthPanel.MATCH3),c);		
  }

	//==================METHODS======================

	private JTextField createTextField(Color c) {
			// This method creates a text field for the panel with
			// the specified color
			JTextField t = new JTextField(" ",3);
			t.setEditable(false);
			t.setBackground(c);
			
			return t;
	}
	
	private JLabel createLabel(String s) {
		// this method creates a lable for the panel
		 JLabel l = new JLabel(s,SwingConstants.LEFT);
		 return l;
	}
	
}
