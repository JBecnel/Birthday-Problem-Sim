package statsim;               // class is part of model package

import java.awt.*;
import javax.swing.*;


/*       Jeremy Becnel                        3/29/05

   This class defines a gui month object.
*/


public class CalendarPanel  extends JPanel   {

//-------------------------FIELDS--------------------------

private MonthPanel[] month;  // array of month panel



//-----------------------CONSTRUCTORS-------------------------

  public CalendarPanel ()    {
    /* This method creates a default calendar panel object.
    */
		
		// give this panel a grid bag layout
    GridBagLayout gridbag = new GridBagLayout();
    GridBagConstraints c = new GridBagConstraints();
	  this.setLayout(gridbag);
   
		// initiate the 12 month panels
		this.month = new MonthPanel[12];
		this.month[0] = new MonthPanel("January", 31);
		this.month[1] = new MonthPanel("February", 29);
		this.month[2] = new MonthPanel("March", 31);
		this.month[3] = new MonthPanel("April", 30);
		this.month[4] = new MonthPanel("May", 31);
		this.month[5] = new MonthPanel("June", 30);
		this.month[6] = new MonthPanel("July", 31);
		this.month[7] = new MonthPanel("August", 31);
		this.month[8] = new MonthPanel("September", 30);
		this.month[9] = new MonthPanel("October", 31);
		this.month[10] = new MonthPanel("November", 30);
		this.month[11] = new MonthPanel("December", 31);

		// add the month panels to this panel
		c.gridy=0;
		c.gridx=0;
		c.insets = new Insets(5,5,5,5);	
		for (int i = 0; i < 12; i++) {
				this.add(month[i],c);
				c.gridx = c.gridx +1;
				if( (i+1) % 3 == 0) {
						c.gridx =0;
						c.gridy = c.gridy +1;
				}
    } // end panel
  }
	
	
//=================METHODS================

//------------------getDate----------------

public MyDate getDate(int day) {
		// Given an integer between 1 and 366, then method returns the
		// calendar date corresponding to that day
		
		for (int i = 0; i < 12; i++) {
				if ((1 <= day) && (day <= this.month[i].getNumDays())) {
				  return (new MyDate((i+1), day));
				}
				else {						
				  day = day - this.month[i].getNumDays();
				}
		} // send for
		
		return (new MyDate()); // if a bad day was given return a default date
}



//------------------writeDate---------------------

// This method write the given date on the appropriate month panel
public void writeDate(MyDate date) {	
				this.writeDate(date.getMonth(), date.getDay());	
}

public void writeDate( int m, int day) {	
				this.month[m-1].setDay(day);	
}
	

//---------------isMatch--------------------

public boolean isMatch(MyDate date) {
		// this method determines if the given date
		// matches a date that is already being displayed
	  int m = date.getMonth() - 1;
		return this.month[m].isMatch(date.getDay());		
}

//---------------recordMatch---------------

public void recordMatch(MyDate date) {
	// given a date that matches a previously diaplayed date
	// this method chagnes the color of the matched date accordingly
	int m = date.getMonth() -1;
	this.month[m].recordMatch(date.getDay());
}

//------------------clear-------------------	
	public void clear() {
		// this method clears all the dates
		for (int i =0; i < 12; i++) {
				this.month[i].clearDates();
		}
  }

	
//----------------getDateLocation----------------

public Point getDateLocation(MyDate d) {
	// this method returns the on screen location of the date
	int m = d.getMonth();
  return this.month[m-1].getDateLocation(d);	
}



}  // end class

