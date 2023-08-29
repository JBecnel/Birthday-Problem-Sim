package statsim;               // class is part of model package


/*       Jeremy Becnel                        3/29/05

   This class defines a simple date object to be used by the applet.
	 It is a date that carries only the day (and integer) and the
	 month (another integer).
*/


public class MyDate   {

//-------------------------FIELDS--------------------------

private int day;
private int month;



//-----------------------CONSTRUCTORS-------------------------

  public MyDate ()    {
    /* This method creates a default MyDate object.

       Pre : none

       Post: a MyDate object is created.
    */
		this(1,1);
	}
		
	
  public MyDate (int m, int d)    {
    /* This constructor creates a MyDate object with
		   day given by d and month by m
    */
		this.day = d;
		this.month=m;		
	}
	
//=======================METHODS==================

public int getDay() {
		return this.day;
}

public int getMonth() {
		return this.month;
}

public void setDay(int d) {
		this.day =d;
}

public void setMonth(int m) {
		this.month=m;
}



public String toString() {
	String s = "";
	int m = this.getMonth();
	if (m  == 1)
			s = "Jan";
	else if (m == 2)
			s = "Feb";
	else if (m == 3)
			s = "Mar";	
	else if (m == 4)
			s = "Apr";	
	else if (m == 5)
			s = "May";	
	else if (m == 6)
			s = "June";	
	else if (m == 7)
			s = "July";	
	else if (m == 8)
			s = "Aug";
	else if (m == 9)
			s = "Sep"; 
	else if (m == 10)
			s = "Oct"; 
	else if (m == 11)
			s = "Nov"; 	
	else if (m == 12)
			s = "Dec";
	
	s = s + " " + this.getDay();
	
	return s;
}

}  // end class

