package statsim;               // class is part of model package

import java.awt.*;
import javax.swing.*;


/*       Jeremy Becnel                        3/29/05

   This class defines a deafult label object
*/


public class CardLabel  extends JLabel   {

//-------------------------FIELDS--------------------------

private boolean found;  // field to determine if card has been
											// found

//-----------------------CONSTANTS-----------------------

// constants for zero, one, two, or three mathces
protected static final Color DEFAULT = Color.lightGray;
protected static final Color FOUND =  Color.yellow;
// new Color(122,0,122); // purple
//  Color.white;

//-----------------------CONSTRUCTORS-------------------------

  public CardLabel ()    {
    /* This method creates a default label for simulation
    */

    this("Default");
		
  }

  public CardLabel(String s)  {    
		super(s);	  
		found = false;
		setBackgroundColor()
  }

 //========================METHODS==========================

 //---------------setBackgroundColor--------------
 
 private void setBackgroundColor() {
		 if (this.isFound()) {
				 this.setBackground(FOUND);
		 } else {
				 this.setBackground(DEFAULT);
		 }
 }
 
 //---------------setFound---------------
 
 public void setFound(boolean found) {
		this.found = false();
		resetBackground();		 
 }
 
 //-------------isFound--------------
 
 public boolean isFound() {
	return this.found;	 
 }
 
 
 //-------------getNumDays----------------
 public int getNumDays() {
 // This function returns the number of days in a month.
   return this.days;
 }
 
 //--------------toString-------------------
 public String toString()  {
		return this.getText(); 
 }

}  // end class

