import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;
import javax.swing.*;
import java.applet.Applet;

import statsim.*;


/* 
Jeremy J. Becnel          April 3rd, 2006          LSU

This applet is designed to simulate the Birthday Problem
in Probability
*/



public class BirthdayApplet extends JApplet   implements ActionListener  {

//====================FIELDS==========================		
		
private CalendarPanel calendar;
   // panel holding all the calendar months
private EntryPanel entry;
   // panel for the user to make request 
	 //(Simulate, add, # of sims, # of birthdays);
private ResultsPanel results;
   // panel that holds the results of each simulation
private KeyPanel  key;
   // panel that holds the key describing the matches
private BirthDayGenerator bday;
   // panel holding a fictional birthday generator

	 
	 private boolean paint; // tells paint when animation is taking place
	 private Point point;	  // tells paint where to draw the date string
	 private String dateString; // a string representing the date
	 
//================INIT=============	 
	 
public void init() {		
	// we create a panel to put all the components on
  // with a gridbag layout
	JPanel p = new JPanel();  
  GridBagLayout gridbag = new GridBagLayout();
  GridBagConstraints c = new GridBagConstraints();
	p.setLayout(gridbag);
   
	// create the calendar and add
	this.calendar = new CalendarPanel();
	p.add(calendar, c);
	
	// put all other compnents on a separate panel
	JPanel comPanel = createCommandPanel();
	this.setBackground(key.getBackground());
	p.add(comPanel,c);
	
	// add the panel with all the components 
	this.getContentPane().add(p);	
	
	// clear the calendar for use
	calendar.clear();
	
}  // end init


//===================METHODS======================

private JPanel createCommandPanel() {
	// This method creates a vertical panel 
	// to put all non calendar components on.
	
	// create a panel with a gridbag layout
	JPanel comPanel = new JPanel();	
	GridBagLayout gb = new GridBagLayout();
	GridBagConstraints gbc = new GridBagConstraints();
	comPanel.setLayout(gb);
	
	// create the birthday generator and put it at top
	bday = new BirthDayGenerator();	
	gbc.gridx = 0; gbc.gridy = 0;
	gbc.insets = new Insets(30,20,0,0);
	gbc.anchor = GridBagConstraints.CENTER;
  comPanel.add(bday,gbc);
	
	// create the entry panel and add it under the birthday generator
  entry = new EntryPanel(this);  
  gbc.gridx = 0; gbc.gridy = 1;
	gbc.anchor = GridBagConstraints.WEST;	
	comPanel.add(entry,gbc);
	
	// create the results table and add it under the entry panel
	results = new ResultsPanel(this);	
	gbc.gridx = 0; gbc.gridy = 2;  	
	comPanel.add(results,gbc);	
	
	// create the key and add it to the bottom part of the panel
	key = new KeyPanel();	
	gbc.gridx =0; gbc.gridy = 3;
	comPanel.add(key, gbc);
	
	return comPanel;
}

//---------------actionPerformed-----------------

	public void actionPerformed(ActionEvent e) {
		// this method listeners for any action (clicking of button)
		// and performs the appropriate task
		
		String name =  e.getActionCommand();	
	
		if (name == "Simulate") { // Simulate button clicked			
			this.calendar.clear(); // clear calendar
			this.update(this.getGraphics()); // forces repaint 
			runSimulations(entry.getNumSims(), true); // run simulation		  
		}
		
		if (name == "Clear") {  // clear button click
			this.calendar.clear(); // clear calendar			
			this.results.clear();  // clear results
		}
		
		if (name == "Add") {  // add button clicked
			this.runSimulations(1, false);	// add more birthdays to simulation
			   // that is run 1 simulation without clearing for the specified
				 // number of birthdays
		}
		
		repaint();
  }
	
	
	//-------------runSimulations---------------------
	
	private void runSimulations(int num, boolean record) {
	  // This method runs the number of simulation specified by
		// num and records results only if record = true;
		
			boolean matchFound = false;  // tracks is a match is found
			boolean animate = false; // determines if the user wants animation
			for (int i = 0; i < num; i++) {			
				if (i == (num -1)) // only animate the last simulation					
					animate = this.entry.animateSim();
				
				// run one simulation and determine if any matches 
				// have been found
			  matchFound = simulate(entry.getNumBirthdays(), animate);
				
				
				if (record) {
				  this.results.recordSim(); // record that a simulation
				  if (matchFound) { // if a match has made, record it.
					  this.results.recordMatchFound();
					}
				}
				
				
				if (i < (num - 1)) // clear the calendar before next simulation
				  this.calendar.clear();
								
				results.updatePercent(); // update the percent after each simulation
			} // end for
	}
  
	
	
	//-----------------simulation-------------------
	
	private boolean simulate(int n, boolean animate) {
			// This method runs one simulation using n birthdays
			// and animate the simulation is animate is true;
			boolean matchFound = false;
			MyDate date = new MyDate();
			for (int i = 0; i < n; i++) {					
					date = this.getRandomDate(); // generate a random date
					
					if (animate) { // animate if neccessary
						this.paint = true; // tells the paint method to paint
						this.animate(date);
					}
					
					// if current date matches a posted pate
					if (this.calendar.isMatch(date)) {
						this.results.recordMatch();	// record the match in resutls
						this.calendar.recordMatch(date); // record the match  on the calendar
						matchFound = true;  // record that match was found
						
					} else { // current date does not match any posted date
					  this.calendar.writeDate(date); // record date on calendar
					} // end else
					
					if (animate) { // clear animation before continuing
						this.paint = false; // tells paint that the animation is finished
				  	this.update(this.getGraphics()); // update to get remaing graphics off
					}
					
			}  //end for
			
			this.update(this.getGraphics()); // display dates momentarily
			return matchFound;
	}
	
	
	//-----------getRandomDate----------------
	
	private MyDate getRandomDate() {
	    // this method returns a random date for the simulation
			double r = Math.random() * 366;  // random number b/t 0 and 366
			int day = (int) Math.ceil(r); // random integer b/t 0 and 366
			return this.calendar.getDate(day); // date corresponding to the integer
	}
					
	
	//------------animate----------------
	
	private void animate(MyDate d) {
		 // This method animates the date going to the
		 // its proper location.
		 
		 Point p0 = this.getLocationOnScreen(); // location of applet
		 Point p1 = bday.getGeneratorLocation(); // location of bday generator
		 
		 // starting positon, to the right of the birthday genetor,
		 // in the middle of the generator.
		 // Note: p1 - p0 gives location of birthday generator in applet 
		 Point start = new Point(p1.x - p0.x-50, p1.y - p0.y+15); 
		 
		 // ending location in the locaiton of the date on the calendar
		 Point end = calendar.getDateLocation(d);
		 // change location relative to applet
		 end.x = end.x - p0.x; end.y = end.y - p0.y;
		 
		 // get string date
		 String date = d.toString();
		  
		 int length = Math.abs(end.x - start.x); // length to move in the x direction
		 int width = Math.abs(end.y - start.y);  // lenght to move in the y direction
		 
		 // number of dates to draw
		 int num = (int) Math.round(Math.max(length,width)/20.0);
		 
		 // increment left in the x direction
		 double incX =  -length/((double) num);;
		 
		 // increment down in the y direction
		 double incY = width/((double) num);
		 
		 Point point = start;  // start at start DUH!
		 for (long i = num; i >= 0; i--) {
				drawDate(date, point);  // draw the date at the given point
			
				// increment the x and y cooridinate of the point 
				point.x = (int) Math.round(point.x + incX);
				point.y = (int) Math.round(point.y + incY);
				
				 try {
				   Thread.sleep(5); // pause for a bit, then update
					 this.update(this.getGraphics());					 
				 } catch (InterruptedException e) { date = "Excep";};
				 
		 } 
		 
		 // draw the last end point upon completion
		 point = end;
		 drawDate(date, point);		 
	}
	
//--------------drawDate---------------------------

	private void drawDate(String s, Point p) {
			// this method draws the given string at the given position			
			this.dateString = s;
			this.point = p;
			repaint();			
	}
	
	
//------------------paint-------------------
	public void paint (Graphics g) {
		// overrides the Applets paint method
		
		super.paint(g); // do the regular stuff
		
		if (paint) { // if animation is taking place
	    g.setColor(Color.yellow);
		  //g.fill3DRect(point.x - 10,point.y -13, 50,20, true);
			//g.fillRoundRect(point.x - 10,point.y -13, 50,20, 10,10);
			g.fillOval(point.x - 10,point.y -13, 50,20);			
			g.setFont((new Font("Times New Roman", Font.BOLD,12)));
			g.setColor((new Color(122,0,122)));			
			g.drawString(dateString, point.x,point.y);			
		}
	}
}  // end class







