package statsim;

import java.awt.*;
import java.awt.event.*;
import javax.swing.BorderFactory; 
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JPanel; 
import javax.swing.JFrame;
import javax.swing.Box;
import javax.swing.BoxLayout;

/*
 Creates a simple component to act as (look like) a birthday generator
 */
public class BirthDayGenerator extends JPanel {


//===================FIELDS=====================		
		
	private JLabel label;  // label for the  generator
	
//==================CONSTRUCTORS==================
	
  public BirthDayGenerator() {
        super(new GridBagLayout());

        //Keep references to the next few borders,
        //for use in titles and compound borders.
        Border blackline, raisedetched, loweredetched,
               raisedbevel, loweredbevel, empty, titled;

        //A border that puts 10 extra pixels at the sides and
        //bottom of each pane.
        Border paneEdge = BorderFactory.createEmptyBorder(0,10,10,10);

        blackline = BorderFactory.createLineBorder(Color.black);
        raisedetched = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
        loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        raisedbevel = BorderFactory.createRaisedBevelBorder();
        loweredbevel = BorderFactory.createLoweredBevelBorder();
        empty = BorderFactory.createEmptyBorder();


        Border line = BorderFactory.createLineBorder(new Color(122,0,122));

        Border compound;
        compound = BorderFactory.createCompoundBorder(
                                raisedbevel, loweredbevel);
       //addCompForBorder(compound, "compound border (two bevels)",
          //               compoundBorders);

        compound = BorderFactory.createCompoundBorder(
                                  line, compound);
        								 
        
         label = new JLabel(" Birthday Generator ", JLabel.CENTER);
        this.add(label);
        this.setBorder(compound);

    }
		
//====================METHODS=========================

   
//----------------getDateLocation----------------

public Point getGeneratorLocation() {
	return this.label.getLocationOnScreen();	
}
} //end class

