/*
Daiwik Swaminathan
4.2.2018
Calendar.java
*/
import java.awt.*;import javax.swing.*;import java.util.*;import java.awt.event.*;//Friday is first day in year 0
//-100: jan 1 is saturday
public class Calendar implements ActionListener
{
	String[] days = {"Sunday", "Monday", "Tuesday","Wednesday","Thursday","Friday","Saturday"};
	String[] months = {"January","February","March","April","May","June","July","August","September","October","November", "December"};
	int[] numOfDays = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	String[][][] date = new String[6000][12][1];
	JFrame frame;
	JLabel test;
	JTextField yearSelect;
	JComboBox<String> monthSelecter;
	int count;
	boolean canSet;
	int startDay = 5;
	int lastDay = 7;
	int dayCount;
	int subNum;
	int month = 2;
	boolean isBehind;
	boolean isMainYear = true;
	int actionCount;
	int year = 5017;
	public static void main(String[] args)
	{
		Calendar prog = new Calendar();
		prog.run();
	}
	public void run()
	{	
		frame = new JFrame("Calendar");
		frame.setLocation(0, 0);
		frame.setSize((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(), (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight());
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setFocusable(true);
		frame.setLayout(new GridLayout(8,7));
		date[year][month][0] = "Year:5017.Month;2/StartingDay-5<LastDay>7";
		//System.out.println(date[year][month][0]);
		addForward();
		addBackward();
		monthSelecter = new JComboBox<String>();
		monthSelecter.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		monthSelecter.setFont(new Font("Ariel", Font.BOLD, 15));
		yearSelect = new JTextField("2018");
		yearSelect.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		yearSelect.setHorizontalAlignment(JTextField.CENTER);
		yearSelect.setFont(new Font("Ariel", Font.BOLD, 25));
		for(int i=0;i<months.length; i++) monthSelecter.addItem(months[i]);
		monthSelecter.setSelectedItem(months[2]);
		//System.out.println(date.length);
		monthSelecter.addActionListener(this);
		yearSelect.addActionListener(this);
		setCalendar();
		frame.setVisible(true);
	}
	public void addForward()
	{
		int limit = 0;
		for(int i=5017; i<6000; i++)
		{
		//System.out.println("Hello1: "+i);
			year = i;
			if(i==5017) limit = 3;
			else limit=0;
			for(int j=limit; j<12; j++)
			{
			//System.out.println("Hello2: "+j);
				month = j;
				//System.out.println("month is " + months[j] + " and year is " + (year+1-3000) + " and here is numOfDays " + numOfDays[j]);
				if(month==1 && (year+1)%4==0){ numOfDays[1] = 29;}
				else numOfDays[1] = 28;
				for(int k=0; k<1; k++)
				{
				int check = month-1;
				if(check==-1) check=11;
				//System.out.println("Hello3: "+k);
					if(numOfDays[check]==31)
   					{
   						startDay += 3;
   						if(startDay==8) startDay = 1;
   						if(startDay==9) startDay = 2;
   						if(startDay==10) startDay = 3;
   					}
   					else if(month==2)
   					{
   						if((year+1)%4!=0)startDay+=0;
   						else startDay+=1;
   						if(startDay==8) startDay = 1;
   						if(startDay==9) startDay = 2;
   					}
   					else 
   					{
   						startDay+=2;
   						if(startDay==8) startDay = 1;
   						if(startDay==9) startDay = 2;
   					}
   					if(numOfDays[check]==31)
   					{
   						lastDay += 2;
   						if(lastDay==8) lastDay = 1;
   						if(lastDay==9) lastDay = 2;
   					}
   					else 
   					{
   						if((year+1)%4!=0)lastDay+=1;
   						else lastDay = startDay;
   						if(lastDay==8) lastDay = 1;
   						if(lastDay==9) lastDay = 2;
   					}
   					date[i][j][k] = "Year:"+i+".Month;"+j+"/StartingDay-"+startDay+"<LastDay>"+lastDay;
   					//System.out.println(date[i][j][k]);
				}
			}
		}
		year = 5017;
		month = 2;
		startDay = 5;
		lastDay = 7;
		//System.out.println("Hello: "+date[5017][2][0]);
	}
	public void addBackward()
	{
	int limit = 0;
		for(int i=5017; i>=0; i--)
		{
		//System.out.println("Hello1: "+i);
			year = i;
			if(i==5017) limit = 1;
			else limit = 11;
			for(int j=limit; j>=0; j--)
			{
			//System.out.println("Hello2: "+j);
				month = j;
				if(month==1 && (year+1)%4==0){ numOfDays[1] = 29;}//2?
				else numOfDays[1] = 28;
				for(int k=0; k>=0; k--)
				{
				//System.out.println("Hello3: "+k);
					if(numOfDays[month]==31)
					{
						startDay-=3;
   						if(startDay==-2) startDay = 5;
   						if(startDay==-1) startDay = 6;
   						if(startDay==0) startDay = 7;
					}
					else if(numOfDays[month]==30)
					{
						startDay-=2;
   						//if(startDay==-2) startDay = 5;
   						if(startDay==-1) startDay = 6;
   						if(startDay==0) startDay = 7;
					}
					else if(numOfDays[month]==29)
					{
						startDay--;
						if(startDay==-1) startDay=7;
					}
					int check2 = month+1;
					if(check2==12) check2 = 0;
					if(numOfDays[check2]==30)
					{
						lastDay-=2;
   						//if(lastDay==-2) lastDay = 5;
   						if(lastDay==-1) lastDay = 6;
   						if(lastDay==0) lastDay = 7;
					}
					else 
					{
						lastDay-=3;
   						if(lastDay==-2) lastDay = 5;
   						if(lastDay==-1) lastDay = 6;
   						if(lastDay==0) lastDay = 7;
					}
   					//System.out.println(i + " " + j + " " + k);
   					if(startDay==-2) startDay = 5;
   						if(startDay==-1) startDay = 6;
   						if(startDay==0) startDay = 7;
   					date[i][j][k] = "Year:"+i+".Month;"+j+"/StartingDay-"+startDay+"<LastDay>"+lastDay;
   					//System.out.println(date[i][j][k]);
				}
			}
		}
		year = 5017;
		month = 2;
		startDay = 5;
		lastDay = 7;
	}
	public void setCalendar()
	{
		for(int i=0; i<56; i++)
		{
			if((year-2999)%4==0) numOfDays[1] = 29;
   			else numOfDays[1] = 28;
   			String temp = date[year][month][0];
   			//System.out.println("hi, real year is "+ year+"year is " + ((year+2999)%4) + " and month is " + months[month] + " and days in it is " + 
   			//numOfDays[month]);
			test = new JLabel(""+i, JLabel.CENTER);
			test.setFont(new Font("Ariel", Font.BOLD, 15));
			if(i>4)test.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			if(i>=14) count++;
			if(count==startDay) {canSet=true;subNum=i-1;}
			if(canSet) {test.setFont(new Font("Ariel", Font.BOLD, 40));test.setText(""+(i-subNum));
			//System.out.println("i is: " + i + " and count is: "+ count+" and dayCount is: "+ dayCount
			//+" and the day is: "+ (i-17));
			dayCount++;if(dayCount==numOfDays[month])canSet=false;}
			else {test.setText("");}
			if(i>6 && i<14) test.setText(days[i-7]);
			if(i==5) {frame.add(monthSelecter);}
			else if(i==6) frame.add(yearSelect);
			else frame.add(test);
		}
	}
	public void actionPerformed(ActionEvent e)
	{
		//System.out.println(e.getSource());
		if(e.getSource()==monthSelecter || e.getSource()==yearSelect)
		{
   			int cmd = monthSelecter.getSelectedIndex();
   			dayCount=0;
   			count=0;
   			canSet=false;
   			month = cmd;
   			year = Integer.parseInt(yearSelect.getText())+2999;
   			if(year<0) year = 0;
   			//if((year+2999)%4==0) numOfDays[1] = 29;
   			//else numOfDays[1] = 28;
   			String temp = date[year][month][0];
   			//System.out.println("month is " + cmd + " and year is " + year);
   			startDay = Integer.parseInt(temp.substring(temp.indexOf('-')+1, temp.indexOf('<')));
   			lastDay = Integer.parseInt(temp.substring(temp.indexOf('>')+1));
   			//System.out.println("start is " + startDay + " and last is " + lastDay);
   			frame.getContentPane().removeAll();
   			frame.setLayout(new GridLayout(8,7));
   			setCalendar();
   			frame.revalidate();
			frame.repaint();	
		}
	}
}